package com.huazie.frame.cache.redis.config;

import com.huazie.frame.cache.common.CacheConstants.RedisConfigConstants;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.PropertiesUtil;
import com.huazie.frame.common.util.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.util.Hashing;
import redis.clients.jedis.util.Sharded;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.huazie.frame.cache.common.CacheConstants.FleaCacheConfigConstants.DEFAULT_EXPIRY;

/**
 * Redis缓存配置类，用于单个缓存接入场景，相关配置项可查看
 * Redis缓存配置文件【redis.properties】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisConfig {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(RedisConfig.class);

    private static volatile RedisConfig config;

    private static Properties prop;

    private String systemName; // 缓存所属系统名

    private List<JedisShardInfo> serverInfos; // 服务器信息 （ host + port + password + weight + connectionTimeout + soTimeOut）

    private Hashing hashingAlg; // 分布式hash算法

    private JedisPoolConfig jedisPoolConfig; // Jedis连接配置信息

    private int nullCacheExpiry; // 空缓存数据有效期（单位：s）

    static {
        String fileName = RedisConfigConstants.REDIS_FILE_NAME;
        if (StringUtils.isNotBlank(System.getProperty(RedisConfigConstants.REDIS_CONFIG_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(RedisConfigConstants.REDIS_CONFIG_FILE_SYSTEM_KEY));
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
                        // 缓存归属系统
                        config.setSystemName();
                        // Jedis分布式服务器信息
                        config.setServerInfos();
                        // 分布式hash算法
                        config.setHashingAlg();
                        // Jedis连接配置信息
                        config.setJedisPoolConfig();
                        // 获取空缓存数据有效期
                        config.setNullCacheExpiry();
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

    public String getSystemName() {
        return systemName;
    }

    private void setSystemName() throws RuntimeException {
        // 获取缓存所属系统名
        String systemName = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_CONFIG_SYSTEM_NAME);
        if (StringUtils.isBlank(systemName)) {
            throw new RuntimeException("缓存归属系统名未配置，请检查");
        }
        this.systemName = systemName;
    }

    public List<JedisShardInfo> getServerInfos() {
        return serverInfos;
    }

    private void setServerInfos() throws RuntimeException{
        List<JedisShardInfo> jedisShardInfos = null;
        String servers = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_CONFIG_SERVER);
        String passwords = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_CONFIG_PASSWORD);
        String weights = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_CONFIG_WEIGHT);
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
                jedisShardInfos = new ArrayList<>();
                for (int i = 0; i < serverArr.length; i++) {
                    String ip = serverArr[i];

                    if (StringUtils.isBlank(ip)) {
                        throw new RuntimeException("服务器配置的IP地址为空，请检查");
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
                    if (ArrayUtils.isSameLength(serverArr, weightArr)) {
                        if (ObjectUtils.isNotEmpty(weightArr)) {
                            weight = Integer.parseInt(weightArr[i]);
                        }
                    }

                    // 获取Redis客户端socket连接超时时间
                    String connectionTimeoutStr = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_CONFIG_CONNECTIONTIMEOUT);
                    if (StringUtils.isNotBlank(connectionTimeoutStr)) {
                        connectionTimeout = Integer.parseInt(connectionTimeoutStr);
                    }

                    // 设置Redis客户端socket读写超时时间
                    String soTimeoutStr = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_CONFIG_SOTIMEOUT);
                    if (StringUtils.isNotBlank(soTimeoutStr)) {
                        soTimeout = Integer.parseInt(soTimeoutStr);
                    }

                    JedisShardInfo jedisShardInfo = new JedisShardInfo(host, port, connectionTimeout, soTimeout, weight);

                    // 只有一一对应，才设置，否则认为没有设置密码
                    if (ArrayUtils.isSameLength(serverArr, passwordArr)) {
                        if (ObjectUtils.isNotEmpty(passwordArr)) {
                            jedisShardInfo.setPassword(passwordArr[i]);
                        }
                    }

                    jedisShardInfos.add(jedisShardInfo);
                }
            }
        }
        serverInfos = jedisShardInfos;
    }

    public Hashing getHashingAlg() {
        return hashingAlg;
    }

    private void setHashingAlg() throws RuntimeException {
        // 获取Redis分布式hash算法
        Integer alg = PropertiesUtil.getIntegerValue(prop, RedisConfigConstants.REDIS_CONFIG_HASHINGALG);
        if (ObjectUtils.isEmpty(alg)) { // 未配置数据，默认取 MURMUR_HASH
            alg = RedisConfigConstants.REDIS_CONFIG_HASHINGALG_MURMUR_HASH;
        }
        if (RedisConfigConstants.REDIS_CONFIG_HASHINGALG_MURMUR_HASH == alg) {
            this.hashingAlg = Hashing.MURMUR_HASH;
        } else if (RedisConfigConstants.REDIS_CONFIG_HASHINGALG_MD5 == alg) {
            this.hashingAlg = Hashing.MD5;
        } else {
            throw new RuntimeException("配置的分布式hash算法【" + alg + "】非法, 仅允许1和2，请检查");
        }
    }

    public JedisPoolConfig getJedisPoolConfig() {
        return jedisPoolConfig;
    }

    private void setJedisPoolConfig() {
        // 获取客户端连接池配置信息
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // Redis客户端Jedis连接池最大连接数
        Integer maxTotal = PropertiesUtil.getIntegerValue(prop, RedisConfigConstants.REDIS_CONFIG_POOL_MAXTOTAL);
        if (ObjectUtils.isNotEmpty(maxTotal)) {
            poolConfig.setMaxTotal(maxTotal);
        }
        // Redis客户端Jedis连接池最大空闲连接数
        Integer maxIdle = PropertiesUtil.getIntegerValue(prop, RedisConfigConstants.REDIS_CONFIG_POOL_MAXIDLE);
        if (ObjectUtils.isNotEmpty(maxIdle)) {
            poolConfig.setMaxIdle(maxIdle);
        }
        // Redis客户端Jedis连接池最小空闲连接数
        Integer minIdle = PropertiesUtil.getIntegerValue(prop, RedisConfigConstants.REDIS_CONFIG_POOL_MINIDLE);
        if (ObjectUtils.isNotEmpty(minIdle)) {
            poolConfig.setMinIdle(minIdle);
        }
        // Redis客户端Jedis连接池获取连接时的最大等待毫秒数
        Integer maxWaitMillis = PropertiesUtil.getIntegerValue(prop, RedisConfigConstants.REDIS_CONFIG_POOL_MAXWAITMILLIS);
        if (ObjectUtils.isNotEmpty(maxWaitMillis)) {
            poolConfig.setMaxWaitMillis(maxWaitMillis);
        }
        this.jedisPoolConfig = poolConfig;
    }

    public int getNullCacheExpiry() {
        return nullCacheExpiry;
    }

    private void setNullCacheExpiry() {
        Integer nullCacheExpiry = PropertiesUtil.getIntegerValue(prop, RedisConfigConstants.REDIS_CONFIG_NULLCACHEEXPIRY);
        if (null != nullCacheExpiry) {
            this.nullCacheExpiry = nullCacheExpiry;
        } else {
            this.nullCacheExpiry = DEFAULT_EXPIRY; // 默认5分钟
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
