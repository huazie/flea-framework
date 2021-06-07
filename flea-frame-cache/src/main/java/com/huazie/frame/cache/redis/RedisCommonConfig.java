package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.common.CacheConstants.RedisConfigConstants;
import com.huazie.frame.cache.exceptions.FleaCacheConfigException;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.PropertiesUtil;
import com.huazie.frame.common.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Properties;

import static com.huazie.frame.cache.common.CacheConstants.FleaCacheConfigConstants.DEFAULT_EXPIRY;

/**
 * Redis缓存通用配置类，用于单个缓存接入场景，相关配置项可查看
 * Redis缓存配置文件【redis.properties】和
 * Redis集群缓存配置文件【redis.cluster.properties】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisCommonConfig {

    private String systemName; // 缓存所属系统名

    private int nullCacheExpiry; // 空缓存数据有效期（单位：s）

    private JedisPoolConfig jedisPoolConfig; // Jedis连接配置信息

    public String getSystemName() {
        return systemName;
    }

    protected void setSystemName(Properties prop) throws FleaCacheConfigException {
        // 获取缓存所属系统名
        String systemName = PropertiesUtil.getStringValue(prop, RedisConfigConstants.REDIS_CONFIG_SYSTEM_NAME);
        if (StringUtils.isBlank(systemName)) {
            throw new FleaCacheConfigException("缓存归属系统名未配置，请检查");
        }
        this.systemName = systemName;
    }

    public int getNullCacheExpiry() {
        return nullCacheExpiry;
    }

    protected void setNullCacheExpiry(Properties prop) {
        Integer nullCacheExpiry = PropertiesUtil.getIntegerValue(prop, RedisConfigConstants.REDIS_CONFIG_NULLCACHEEXPIRY);
        if (null != nullCacheExpiry) {
            this.nullCacheExpiry = nullCacheExpiry;
        } else {
            this.nullCacheExpiry = DEFAULT_EXPIRY; // 默认5分钟
        }
    }

    public JedisPoolConfig getJedisPoolConfig() {
        return jedisPoolConfig;
    }

    protected void setJedisPoolConfig(Properties prop) throws FleaCacheConfigException {
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

}
