package com.huazie.frame.cache.memcached.impl;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.common.CacheEnum;
import com.huazie.frame.cache.memcached.config.MemCachedConfig;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
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
     * @param name            缓存主关键字
     * @param expiry          失效时长
     * @param memCachedClient MemCached客户端
     * @since 1.0.0
     */
    public MemCachedFleaCache(String name, long expiry, MemCachedClient memCachedClient) {
        super(name, expiry);
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
    public void putNativeValue(String key, Object value, long expiry) {
        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "MEMCACHED FLEA CACHE, KEY = {}", key);
            LOGGER.debug1(obj, "MEMCACHED FLEA CACHE, VALUE = {}", value);
            LOGGER.debug1(obj, "MEMCACHED FLEA CACHE, EXPIRY = {}s", expiry);
        }
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
