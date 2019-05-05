package com.huazie.frame.cache.memcached.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.common.CacheEnum;
import com.whalin.MemCached.MemCachedClient;

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

    public MemcachedFleaCache(String name, int expire, MemCachedClient memcachedClient) {
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
    protected void putNativeValue(String key, Object value, int expire){
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MemcachedFleaCache##putNativeValue(String, Object, int) KEY={}", key);
            LOGGER.debug("MemcachedFleaCache##putNativeValue(String, Object, int) VALUE={}", value);
        }
        memcachedClient.set(key, value, expire);
    }

    @Override
    protected void deleteNativeValue(String key){
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MemcachedFleaCache##deleteNativeValue(String) KEY=" + key);
        }
        memcachedClient.delete(key);
    }

}
