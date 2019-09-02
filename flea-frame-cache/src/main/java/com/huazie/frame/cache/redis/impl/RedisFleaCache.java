package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.common.CacheEnum;
import com.huazie.frame.cache.redis.RedisClient;
import com.huazie.frame.cache.redis.config.RedisConfig;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> Redis Flea缓存类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisFleaCache extends AbstractFleaCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisFleaCache.class);

    private RedisClient redisClient; // Redis客户端

    /**
     * <p> 带参数的构造方法，初始化Redis Flea缓存类 </p>
     *
     * @param name        缓存主关键字
     * @param expiry      失效时长
     * @param redisClient Redis客户端
     */
    public RedisFleaCache(String name, long expiry, RedisClient redisClient) {
        super(name, expiry);
        this.redisClient = redisClient;
        cache = CacheEnum.Redis;
    }

    @Override
    public Object getNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RedisFleaCache##getNativeValue(String) KEY = {}", key);
        }
        // 反序列化
        return ObjectUtils.deserialize(redisClient.get(key.getBytes()));
    }

    @Override
    public void putNativeValue(String key, Object value, long expiry) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RedisFleaCache##putNativeValue(String, Object, long) KEY = {}", key);
            LOGGER.debug("RedisFleaCache##putNativeValue(String, Object, long) VALUE = {}", value);
            LOGGER.debug("RedisFleaCache##putNativeValue(String, Object, long) EXPIRY = {}s", expiry);
        }
        // 序列化
        if (ObjectUtils.isNotEmpty(value)) {
            byte[] valueBytes = ObjectUtils.serialize(value);
            if (expiry == CommonConstants.NumeralConstants.ZERO) {
                redisClient.set(key.getBytes(), valueBytes);
            } else {
                redisClient.set(key.getBytes(), valueBytes, (int) expiry);
            }
        }

    }

    @Override
    public void deleteNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RedisFleaCache##deleteNativeValue(String) KEY = {}", key);
        }
        redisClient.del(key);
    }

    @Override
    public String getSystemName() {
        return RedisConfig.getConfig().getSystemName();
    }
}
