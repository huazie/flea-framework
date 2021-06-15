package com.huazie.frame.cache.redis.config;

import com.huazie.frame.cache.common.CacheConstants.RedisConfigConstants;
import com.huazie.frame.cache.exceptions.FleaCacheConfigException;
import com.huazie.frame.cache.exceptions.FleaCacheException;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.PropertiesUtil;
import com.huazie.frame.common.util.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.util.Hashing;
import redis.clients.jedis.util.Sharded;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Redis单机模式缓存配置类，用于单个缓存接入场景，相关配置项可查看
 * Redis缓存配置文件【redis.properties】
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.0.0
 */
public class RedisSingleConfig extends RedisCommonConfig {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(RedisSingleConfig.class);

    private static volatile RedisSingleConfig config;

    private static Properties prop;

    private List<JedisShardInfo> serverInfos; // Reids服务器分片信息

    private Hashing hashingAlg; // 分布式hash算法

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

    private RedisSingleConfig() {
        try {
            // 缓存归属系统
            setSystemName(prop);
            // Jedis分布式服务器信息
            setServerInfos();
            // 分布式hash算法
            setHashingAlg();
            // Redis客户端操作最大尝试次数【包含第一次操作】
            setMaxAttempts(prop);
            // 获取空缓存数据有效期
            setNullCacheExpiry(prop);
            // Jedis连接配置信息
            setJedisPoolConfig(prop);
        } catch (FleaCacheException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Please check the redis config :", e);
            }
        }
    }

    /**
     * 获取Redis缓存配置类实例
     *
     * @return Redis缓存配置类实例
     * @since 1.0.0
     */
    public static RedisSingleConfig getConfig() {
        if (ObjectUtils.isEmpty(config)) {
            synchronized (RedisSingleConfig.class) {
                if (ObjectUtils.isEmpty(config)) {
                    config = new RedisSingleConfig();
                }
            }
        }
        return config;
    }

    /**
     * 获取Reids服务器分片信息集合
     *
     * @return Reids服务器分片信息集合
     * @since 1.0.0
     */
    public List<JedisShardInfo> getServerInfos() {
        return serverInfos;
    }

    /**
     * 设置Reids服务器分片信息
     *
     * @throws FleaCacheConfigException Flea缓存配置异常类
     * @since 1.0.0
     */
    private void setServerInfos() throws FleaCacheConfigException {
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
                        throw new FleaCacheConfigException("服务器配置的IP地址为空，请检查");
                    }

                    String host = Protocol.DEFAULT_HOST;    // 默认主机
                    int port = Protocol.DEFAULT_PORT;       // 默认端口
                    int weight = Sharded.DEFAULT_WEIGHT;    // 默认服务器权重
                    int connectionTimeout = Protocol.DEFAULT_TIMEOUT; // 默认Redis客户端socket连接超时时间
                    int soTimeout = Protocol.DEFAULT_TIMEOUT; // 默认Redis客户端socket读写超时时间
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

    /**
     * 获取Redis分布式hash算法
     *
     * @return Redis分布式hash算法
     * @since 1.0.0
     */
    public Hashing getHashingAlg() {
        return hashingAlg;
    }

    /**
     * 设置Redis分布式hash算法
     *
     * @throws FleaCacheConfigException Flea缓存配置异常类
     * @since 1.0.0
     */
    private void setHashingAlg() throws FleaCacheConfigException {
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
            throw new FleaCacheConfigException("配置的分布式hash算法【" + alg + "】非法, 仅允许1和2，请检查");
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
