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
 * Redis Flea缓存类，实现了以Flea框架操作Redis缓存的基本操作方法，
 * 包含读【{@code getNativeValue}】、写【{@code putNativeValue}】
 * 和删除【{@code deleteNativeValue}】。
 *
 * <p> 在上述基本操作方法中，实际使用Redis客户端【{@code} redisClient】
 * 读、写和删除Redis缓存。其中写缓存方法【{@code putNativeValue}】在
 * 添加的数据值为【{@code null}】时，默认添加空缓存数据【{@code NullCache}】
 * 到Redis中，有效期取初始化参数【{@code nullCacheExpiry}】。
 *
 * <p> 单个缓存接入场景，有效期配置可查看【redis.properties】中的配置
 * 参数【redis.nullCacheExpiry】
 *
 * <p> 整合缓存接入场景，有效期配置可查看【flea-cache-config.xml】
 * 中的缓存参数【{@code <cache-param key="fleacore.nullCacheExpiry"
 * desc="空缓存数据有效期（单位：s）">300</cache-param>}】
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
