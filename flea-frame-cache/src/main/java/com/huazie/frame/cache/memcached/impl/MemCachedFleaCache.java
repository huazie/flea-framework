package com.huazie.frame.cache.memcached.impl;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.NullCache;
import com.huazie.frame.cache.common.CacheEnum;
import com.huazie.frame.cache.memcached.config.MemCachedConfig;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ObjectUtils;
import com.whalin.MemCached.MemCachedClient;

import java.util.Date;

/**
 * <p> MemCached Flea缓存类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemCachedFleaCache extends AbstractFleaCache {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(MemCachedFleaCache.class);

    private final MemCachedClient memCachedClient;  // MemCached客户端

    /**
     * <p> 带参数的构造方法，初始化MemCached Flea缓存类 </p>
     *
     * @param name            缓存数据主关键字
     * @param expiry          缓存数据有效期（单位：s）
     * @param nullCacheExpiry 空缓存数据有效期（单位：s）
     * @param memCachedClient MemCached客户端
     * @since 1.0.0
     */
    public MemCachedFleaCache(String name, int expiry, int nullCacheExpiry, MemCachedClient memCachedClient) {
        super(name, expiry, nullCacheExpiry);
        this.memCachedClient = memCachedClient;
        cache = CacheEnum.MemCached;
    }

    @Override
    public Object getNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "KEY = {}", key);
        }
        return memCachedClient.get(key);
    }

    @Override
    public void putNativeValue(String key, Object value, int expiry) {
        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "MEMCACHED FLEA CACHE, KEY = {}", key);
            LOGGER.debug1(obj, "MEMCACHED FLEA CACHE, VALUE = {}", value);
            LOGGER.debug1(obj, "MEMCACHED FLEA CACHE, EXPIRY = {}s", expiry);
            LOGGER.debug1(obj, "MEMCACHED FLEA CACHE, NULL CACHE EXPIRY = {}s", getNullCacheExpiry());
        }
        if (ObjectUtils.isEmpty(value))
            memCachedClient.set(key, new NullCache(key), new Date(getNullCacheExpiry() * 1000));
        else
            memCachedClient.set(key, value, new Date(expiry * 1000));
    }

    @Override
    public void deleteNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "KEY = {}", key);
        }
        memCachedClient.delete(key);
    }

    @Override
    public String getSystemName() {
        return MemCachedConfig.getConfig().getSystemName();
    }
}
