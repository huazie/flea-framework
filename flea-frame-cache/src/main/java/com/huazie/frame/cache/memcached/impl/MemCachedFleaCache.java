package com.huazie.frame.cache.memcached.impl;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.common.CacheEnum;
import com.whalin.MemCached.MemCachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * <p> MemCached Flea缓存类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemCachedFleaCache extends AbstractFleaCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemCachedFleaCache.class);

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
            LOGGER.debug("MemCachedFleaCache##getNativeValue(String) KEY = {}", key);
        }
        return memCachedClient.get(key);
    }

    @Override
    public void putNativeValue(String key, Object value, long expiry) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MemCachedFleaCache##putNativeValue(String, Object, long) KEY = {}", key);
            LOGGER.debug("MemCachedFleaCache##putNativeValue(String, Object, long) VALUE = {}", value);
            LOGGER.debug("MemCachedFleaCache##putNativeValue(String, Object, long) EXPIRY = {}s", expiry);
        }
        memCachedClient.set(key, value, new Date(expiry * 1000));
    }

    @Override
    public void deleteNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MemCachedFleaCache##deleteNativeValue(String) KEY = {}", key);
        }
        memCachedClient.delete(key);
    }

}
