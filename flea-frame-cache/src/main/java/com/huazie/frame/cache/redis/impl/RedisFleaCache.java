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

    public RedisFleaCache(String name, int expire) {
        super(name, expire);
    }

    @Override
    protected Object getNativeValue(String key) throws Exception {
        return null;
    }

    @Override
    protected void putNativeValue(String key, Object value, int expire) throws Exception {

    }

    @Override
    protected void deleteNativeValue(String key) throws Exception {

    }

}
