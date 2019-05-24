package com.huazie.frame.cache.redis.config;

import com.huazie.frame.cache.common.CacheConstants;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.PropertiesUtil;
import com.huazie.frame.common.util.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.util.Hashing;
import redis.clients.jedis.util.Sharded;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * <p> Redis缓存配置类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    private static volatile RedisConfig config;

    private static Properties prop;

    private List<JedisShardInfo> servers; // 服务器信息 （ host + port + password + weight + connectionTimeout + soTimeOut）

    private Hashing hashingAlg; // 分布式hash算法

    private JedisPoolConfig jedisPoolConfig; // Jedis连接配置信息

    static {
        String fileName = CacheConstants.RedisConfigConstants.REDIS_FILE_NAME;
        if (StringUtils.isNotBlank(System.getProperty("fleaframe.cache.redis.config.filename"))) {
            fileName = StringUtils.trim(System.getProperty("fleaframe.cache.redis.config.filename"));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("RedisConfig Use the specified redis.properties：{}", fileName);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RedisConfig Use the current redis.properties：{}", fileName);
        }
        // 获取配置文件
        prop = PropertiesUtil.getProperties(fileName);
    }

    /**
     * <p> 读取Redis缓存配置类实例 </p>
     *
     * @return Redis缓存配置类实例
     * @since 1.0.0
     */
    public static RedisConfig getConfig() {
        if (ObjectUtils.isEmpty(config)) {
            synchronized (RedisConfig.class) {
                if (ObjectUtils.isEmpty(config)) {
                    config = new RedisConfig();
                    try {
                        List<JedisShardInfo> jedisRedisInfos = null;
                        String servers = PropertiesUtil.getStringValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_SERVER);
                        String passwords = PropertiesUtil.getStringValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_PASSWORD);
                        String weights = PropertiesUtil.getStringValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_WEIGHT);
                        if (StringUtils.isNotBlank(servers)) {

                            // 取逗号分隔的服务器地址信息
                            String[] serverArr = StringUtils.split(servers, CommonConstants.SymbolConstants.COMMA);

                            // 取逗号分隔的服务器授权密码信息（可以不配置，即没有密码）
                            String[] passwordArr = null;
                            if (StringUtils.isNotBlank(passwords)) {
                                passwordArr = StringUtils.split(passwords, CommonConstants.SymbolConstants.COMMA);
                            }

                            // 取逗号分隔的服务器权重配置信息（可以不配置，即取默认权重 Sharded.DEFAULT_WEIGHT）
                            String[] weightArr = null;
                            if (StringUtils.isNotBlank(weights)) {
                                weightArr = StringUtils.split(weights, CommonConstants.SymbolConstants.COMMA);
                            }

                            if (ArrayUtils.isNotEmpty(serverArr)) {
                                jedisRedisInfos = new ArrayList<JedisShardInfo>();
                                for (int i = 0; i < serverArr.length; i++) {
                                    String ip = serverArr[i];

                                    if (StringUtils.isBlank(ip)) {
                                        throw new Exception("The IP Address of server is empty");
                                    }

                                    String host = Protocol.DEFAULT_HOST;    // 默认主机
                                    int port = Protocol.DEFAULT_PORT;       // 默认端口
                                    int weight = Sharded.DEFAULT_WEIGHT;    // 默认服务器权重
                                    int connectionTimeout = Protocol.DEFAULT_TIMEOUT; // 默认Redis客户端socket连接超时时间
                                    int soTimeout = Protocol.DEFAULT_TIMEOUT; // 默认Redis客户端socket连接超时时间
                                    if (StringUtils.isNotBlank(ip)) {
                                        String[] ipArr = StringUtils.split(ip, CommonConstants.SymbolConstants.COLON);
                                        if (ArrayUtils.isNotEmpty(ipArr) && CommonConstants.NumeralConstants.INT_TWO == ipArr.length) {
                                            host = ipArr[CommonConstants.NumeralConstants.INT_ZERO];
                                            port = Integer.parseInt(ipArr[CommonConstants.NumeralConstants.INT_ONE]);
                                        }
                                    }

                                    // 只有一一对应，才设置，否则取默认权重
                                    if (ArrayUtils.isSameLength(serverArr, passwordArr)) {
                                        weight = Integer.parseInt(weightArr[i]);
                                    }

                                    // 获取Redis客户端socket连接超时时间
                                    String connectionTimeoutStr = PropertiesUtil.getStringValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_CONNECTIONTIMEOUT);
                                    if (StringUtils.isNotBlank(connectionTimeoutStr)) {
                                        connectionTimeout = Integer.parseInt(connectionTimeoutStr);
                                    }

                                    // 设置Redis客户端socket读写超时时间
                                    String soTimeoutStr = PropertiesUtil.getStringValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_SOTIMEOUT);
                                    if (StringUtils.isNotBlank(soTimeoutStr)) {
                                        soTimeout = Integer.parseInt(soTimeoutStr);
                                    }

                                    JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port, connectionTimeout, soTimeout, weight);

                                    // 只有一一对应，才设置，否则认为没有设置密码
                                    if (ArrayUtils.isSameLength(serverArr, passwordArr)) {
                                        jedisShardInfo.setPassword(passwordArr[i]);
                                    }

                                    jedisRedisInfos.add(jedisShardInfo);
                                }
                            }
                        }
                        config.setServers(jedisRedisInfos);

                        // 获取Redis分布式hash算法
                        Integer alg = PropertiesUtil.getIntegerValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_HASHINGALG);
                        if (ObjectUtils.isEmpty(alg)) { // 未配置数据，默认取 MURMUR_HASH
                            alg = CacheConstants.RedisConfigConstants.REDIS_CONFIG_HASHINGALG_MURMUR_HASH;
                        }
                        if (CacheConstants.RedisConfigConstants.REDIS_CONFIG_HASHINGALG_MURMUR_HASH == alg) {
                            config.setHashingAlg(Hashing.MURMUR_HASH);
                        } else if (CacheConstants.RedisConfigConstants.REDIS_CONFIG_HASHINGALG_MD5 == alg) {
                            config.setHashingAlg(Hashing.MD5);
                        } else {
                            throw new Exception("The config of hashingAlg [ " + alg + "] is invalid, only 1 or 2");
                        }

                        // 获取客户端连接池配置信息
                        JedisPoolConfig poolConfig = new JedisPoolConfig();

                        // Redis客户端Jedis连接池最大连接数
                        Integer maxTotal = PropertiesUtil.getIntegerValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MAXTOTAL);
                        if(ObjectUtils.isNotEmpty(maxTotal)) {
                            poolConfig.setMaxTotal(maxTotal);
                        }

                        // Redis客户端Jedis连接池最大空闲连接数
                        Integer maxIdle = PropertiesUtil.getIntegerValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MAXIDLE);
                        if(ObjectUtils.isNotEmpty(maxIdle)) {
                            poolConfig.setMaxIdle(maxIdle);
                        }

                        // Redis客户端Jedis连接池最小空闲连接数
                        Integer minIdle = PropertiesUtil.getIntegerValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MINIDLE);
                        if(ObjectUtils.isNotEmpty(minIdle)) {
                            poolConfig.setMinIdle(minIdle);
                        }
                        // Redis客户端Jedis连接池获取连接时的最大等待毫秒数
                        Integer maxWaitMillis = PropertiesUtil.getIntegerValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MAXWAITMILLIS);
                        if(ObjectUtils.isNotEmpty(maxWaitMillis)) {
                            poolConfig.setMaxWaitMillis(maxWaitMillis);
                        }

                        config.setJedisPoolConfig(poolConfig);

                    } catch (Exception e) {
                        if (LOGGER.isErrorEnabled()) {
                            LOGGER.error("Please check the redis config :", e);
                        }
                    }
                }
            }
        }
        return config;
    }

    public List<JedisShardInfo> getServers() {
        return servers;
    }

    public void setServers(List<JedisShardInfo> servers) {
        this.servers = servers;
    }

    public Hashing getHashingAlg() {
        return hashingAlg;
    }

    public void setHashingAlg(Hashing hashingAlg) {
        this.hashingAlg = hashingAlg;
    }

    public JedisPoolConfig getJedisPoolConfig() {
        return jedisPoolConfig;
    }

    public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
        this.jedisPoolConfig = jedisPoolConfig;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
