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
public class MemcachedFleaCache extends AbstractFleaCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemcachedFleaCache.class);

    private final MemCachedClient memcachedClient;  // Memcached客户端类

    public MemcachedFleaCache(String name, long expire, MemCachedClient memcachedClient) {
        super(name, expire);
        this.memcachedClient = memcachedClient;
        cache = CacheEnum.Memcached;
    }

    @Override
    protected Object getNativeValue(String key){
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MemcachedFleaCache##getNativeValue(String) KEY={}", key);
        }
        return memcachedClient.get(key);
    }

    @Override
    protected void putNativeValue(String key, Object value, long expiry){
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MemcachedFleaCache##putNativeValue(String, Object, long) KEY={}", key);
            LOGGER.debug("MemcachedFleaCache##putNativeValue(String, Object, long) VALUE={}", value);
            LOGGER.debug("MemcachedFleaCache##putNativeValue(String, Object, long) EXPIRY={}s", expiry);

        }
        memcachedClient.set(key, value, new Date(expiry * 1000));
    }

    @Override
    protected void deleteNativeValue(String key){
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MemcachedFleaCache##deleteNativeValue(String) KEY=" + key);
        }
        memcachedClient.delete(key);
    }

}
