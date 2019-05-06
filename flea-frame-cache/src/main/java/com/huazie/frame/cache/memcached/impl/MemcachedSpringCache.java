package com.huazie.frame.cache.memcached.impl;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.IFleaCache;
import com.whalin.MemCached.MemCachedClient;

/**
 * <p> 封装MemcachedCache的实现，主要实现spring的cache接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemcachedSpringCache extends AbstractSpringCache {

    public MemcachedSpringCache(String name, IFleaCache fleaCache) {
        super(name, fleaCache);
    }

    public MemcachedSpringCache(String name, long expiry, MemCachedClient memcachedClient) {
        super(name, new MemcachedFleaCache(name, expiry, memcachedClient));
    }

}
