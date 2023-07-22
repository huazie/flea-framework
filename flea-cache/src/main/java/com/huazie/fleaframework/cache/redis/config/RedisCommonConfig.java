package com.huazie.fleaframework.cache.redis.config;

import com.huazie.fleaframework.cache.common.CacheConstants;
import com.huazie.fleaframework.cache.exceptions.FleaCacheConfigException;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.PropertiesUtil;
import com.huazie.fleaframework.common.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

/**
 * Redis缓存通用配置类，封装了一些公共的缓存配置获取方式。
 * 它主要应用于单个缓存接入场景，相关配置项可查看
 * Redis缓存配置文件【redis.properties】和
 * Redis集群缓存配置文件【redis.cluster.properties】
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.0.0
 */
public class RedisCommonConfig {

    private int switchFlag = 0; // Redis （分片或集群）配置开关（1：开启 0：关闭）

    private String systemName; // 缓存所属系统名

    private int maxAttempts; // Redis客户端操作最大尝试次数【包含第一次操作】

    private int nullCacheExpiry; // 空缓存数据有效期（单位：s）

    private JedisPoolConfig jedisPoolConfig; // Jedis连接配置信息

    /**
     * 判断 Redis（分片或集群）配置开关是否开启
     *
     * @return true：开启 false：关闭
     * @since 2.0.0
     */
    public boolean isSwitchOpen() {
        return switchFlag == 1;
    }

    /**
     * 设置 Redis（分片或集群）配置开关
     *
     * @param prop Redis（分片或集群）配置
     * @param key  开关的配置键
     * @since 2.0.0
     */
    void setSwitchFlag(Properties prop, String key) {
        // 获取配置开关（1：开启 0：关闭）
        Integer switchFlag = PropertiesUtil.getIntegerValue(prop, key);
        if (ObjectUtils.isEmpty(switchFlag)) {
            switchFlag = 1; // 如果不配置，也默认开启
        }
        this.switchFlag = switchFlag;
    }

    /**
     * 获取缓存归属系统名
     *
     * @return 缓存归属系统名
     * @since 1.0.0
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * 设置缓存归属系统名
     *
     * @param prop 配置信息
     * @throws FleaCacheConfigException Flea缓存配置异常
     * @since 1.0.0
     */
    void setSystemName(Properties prop) throws FleaCacheConfigException {
        // 获取缓存所属系统名
        String systemName = PropertiesUtil.getStringValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_SYSTEM_NAME);
        if (StringUtils.isBlank(systemName)) {
            ExceptionUtils.throwFleaException(FleaCacheConfigException.class, "缓存归属系统名未配置，请检查");
        }
        this.systemName = systemName;
    }

    /**
     * 获取空缓存数据有效期（单位：s）
     *
     * @return 空缓存数据有效期（单位：s）
     * @since 1.0.0
     */
    public int getNullCacheExpiry() {
        return nullCacheExpiry;
    }

    /**
     * 设置空缓存数据有效器（单位：s）
     *
     * @param prop 配置信息
     * @since 1.0.0
     */
    void setNullCacheExpiry(Properties prop) {
        Integer nullCacheExpiry = PropertiesUtil.getIntegerValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_NULLCACHEEXPIRY);
        if (null != nullCacheExpiry) {
            this.nullCacheExpiry = nullCacheExpiry;
        } else {
            this.nullCacheExpiry = CacheConstants.FleaCacheConfigConstants.DEFAULT_EXPIRY; // 默认5分钟
        }
    }

    /**
     * 获取Redis客户端操作最大尝试次数【包含第一次操作】
     *
     * @return Redis客户端操作最大尝试次数【包含第一次操作】
     * @since 1.1.0
     */
    public int getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * 设置Redis集群客户端socket读写超时时间（单位：ms）
     *
     * @param prop 配置信息
     * @since 1.1.0
     */
    void setMaxAttempts(Properties prop) {
        int maxAttempts = CacheConstants.RedisConfigConstants.REDIS_MAXATTEMPTS_DEFAULT;
        String maxAttemptStr = PropertiesUtil.getStringValue(prop, CacheConstants.RedisConfigConstants.REDIS_MAXATTEMPTS);
        if (StringUtils.isNotBlank(maxAttemptStr)) {
            maxAttempts = Integer.parseInt(maxAttemptStr);
        }
        this.maxAttempts = maxAttempts;
    }

    /**
     * 获取Jedis连接池配置信息
     *
     * @return Jedis连接池配置信息
     * @since 1.1.0
     */
    public JedisPoolConfig getJedisPoolConfig() {
        return jedisPoolConfig;
    }

    /**
     * 设置Jedis连接池配置信息
     *
     * @param prop 配置信息
     * @since 1.1.0
     */
    void setJedisPoolConfig(Properties prop) throws FleaCacheConfigException {
        // 获取客户端连接池配置信息
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // Redis客户端Jedis连接池最大连接数
        Integer maxTotal = PropertiesUtil.getIntegerValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MAXTOTAL);
        if (ObjectUtils.isNotEmpty(maxTotal)) {
            poolConfig.setMaxTotal(maxTotal);
        }
        // Redis客户端Jedis连接池最大空闲连接数
        Integer maxIdle = PropertiesUtil.getIntegerValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MAXIDLE);
        if (ObjectUtils.isNotEmpty(maxIdle)) {
            poolConfig.setMaxIdle(maxIdle);
        }
        // Redis客户端Jedis连接池最小空闲连接数
        Integer minIdle = PropertiesUtil.getIntegerValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MINIDLE);
        if (ObjectUtils.isNotEmpty(minIdle)) {
            poolConfig.setMinIdle(minIdle);
        }
        // Redis客户端Jedis连接池获取连接时的最大等待毫秒数
        Integer maxWaitMillis = PropertiesUtil.getIntegerValue(prop, CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MAXWAITMILLIS);
        if (ObjectUtils.isNotEmpty(maxWaitMillis)) {
            poolConfig.setMaxWaitMillis(maxWaitMillis);
        }
        this.jedisPoolConfig = poolConfig;
    }

}
