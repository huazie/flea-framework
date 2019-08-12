package com.huazie.frame.cache.memcached.impl;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.common.CacheEnum;
import com.whalin.MemCached.MemCachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * <p> 自定义Memcached缓存类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemCachedFleaCache extends AbstractFleaCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemCachedFleaCache.class);

    private final MemCachedClient memcachedClient;  // MemCached客户端类

    public MemCachedFleaCache(String name, long expiry, MemCachedClient memcachedClient) {
        super(name, expiry);
        this.memcachedClient = memcachedClient;
        cache = CacheEnum.MemCached;
    }

    @Override
    public Object getNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MemCachedFleaCache##getNativeValue(String) KEY = {}", key);
        }
        return memcachedClient.get(key);
    }

    @Override
    public void putNativeValue(String key, Object value, long expiry) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MemCachedFleaCache##putNativeValue(String, Object, long) KEY = {}", key);
            LOGGER.debug("MemCachedFleaCache##putNativeValue(String, Object, long) VALUE = {}", value);
            LOGGER.debug("MemCachedFleaCache##putNativeValue(String, Object, long) EXPIRY = {}s", expiry);
        }
        memcachedClient.set(key, value, new Date(expiry * 1000));
    }

    @Override
    public void deleteNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MemCachedFleaCache##deleteNativeValue(String) KEY = {}", key);
        }
        memcachedClient.delete(key);
    }

}
