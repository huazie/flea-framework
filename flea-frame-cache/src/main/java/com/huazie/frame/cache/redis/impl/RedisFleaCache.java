package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.NullCache;
import com.huazie.frame.cache.common.CacheEnum;
import com.huazie.frame.cache.redis.RedisClient;
import com.huazie.frame.cache.redis.config.RedisConfig;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ObjectUtils;

/**
 * <p> Redis Flea缓存类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisFleaCache extends AbstractFleaCache {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(RedisFleaCache.class);

    private RedisClient redisClient; // Redis客户端

    /**
     * <p> 带参数的构造方法，初始化Redis Flea缓存类 </p>
     *
     * @param name            缓存数据主关键字
     * @param expiry          缓存数据有效期（单位：s）
     * @param nullCacheExpiry 空缓存数据有效期（单位：s）
     * @param redisClient     Redis客户端
     * @since 1.0.0
     */
    public RedisFleaCache(String name, int expiry, int nullCacheExpiry, RedisClient redisClient) {
        super(name, expiry, nullCacheExpiry);
        this.redisClient = redisClient;
        cache = CacheEnum.Redis;
    }

    @Override
    public Object getNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "KEY = {}", key);
        }
        return redisClient.get(key);
    }

    @Override
    public void putNativeValue(String key, Object value, int expiry) {
        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "REDIS FLEA CACHE, KEY = {}", key);
            LOGGER.debug1(obj, "REDIS FLEA CACHE, VALUE = {}", value);
            LOGGER.debug1(obj, "REDIS FLEA CACHE, EXPIRY = {}s", expiry);
            LOGGER.debug1(obj, "REDIS FLEA CACHE, NULL CACHE EXPIRY = {}s", getNullCacheExpiry());
        }
        if (ObjectUtils.isEmpty(value)) {
            redisClient.set(key, new NullCache(key), getNullCacheExpiry());
        } else {
            if (expiry == CommonConstants.NumeralConstants.INT_ZERO) {
                redisClient.set(key, value);
            } else {
                redisClient.set(key, value, expiry);
            }
        }
    }

    @Override
    public void deleteNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "KEY = {}", key);
        }
        redisClient.del(key);
    }

    @Override
    public String getSystemName() {
        return RedisConfig.getConfig().getSystemName();
    }
}
