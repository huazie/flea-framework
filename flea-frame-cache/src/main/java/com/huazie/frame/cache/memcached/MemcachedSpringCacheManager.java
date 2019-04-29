package com.huazie.frame.cache.memcached;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.AbstractSpringCacheManager;
import com.huazie.frame.cache.memcached.impl.MemcachedSpringCache;
import com.whalin.MemCached.MemCachedClient;

/**
 * <p> Memcached的Spring缓存管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemcachedSpringCacheManager extends AbstractSpringCacheManager {

    private MemCachedClient memcachedClient;   // memcached客户端类

    @Override
    protected AbstractSpringCache newCache(String name, int expire) {
        return new MemcachedSpringCache(name, expire, memcachedClient);
    }

    public void setMemcachedClient(MemCachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

}
