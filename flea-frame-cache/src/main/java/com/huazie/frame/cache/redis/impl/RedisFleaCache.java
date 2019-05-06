package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.AbstractFleaCache;

/**
 * <p> 自定义Redis缓存类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisFleaCache extends AbstractFleaCache {

    public RedisFleaCache(String name, long expiry) {
        super(name, expiry);
    }

    @Override
    protected Object getNativeValue(String key) {
        return null;
    }

    @Override
    protected void putNativeValue(String key, Object value, long expiry) {

    }

    @Override
    protected void deleteNativeValue(String key) {

    }

}
