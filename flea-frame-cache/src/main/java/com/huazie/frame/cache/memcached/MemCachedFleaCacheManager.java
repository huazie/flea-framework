package com.huazie.frame.cache.memcached;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.memcached.impl.MemCachedFleaCache;
import com.huazie.frame.common.util.ObjectUtils;
import com.whalin.MemCached.MemCachedClient;

/**
 * <p> MemCached Flea缓存管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemCachedFleaCacheManager extends AbstractFleaCacheManager {

    private static volatile MemCachedFleaCacheManager cacheManager;

    private MemCachedClient memcachedClient;   // Memcached客户端类

    public MemCachedFleaCacheManager(MemCachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
        initPool();
    }

    /**
     * 初始化Memcached连接池
     *
     * @since 1.0.0
     */
    private void initPool() {
        MemCachedPool pool = MemCachedPool.getInstance();
        pool.initialize();
    }

    @Override
    protected AbstractFleaCache newCache(String name, long expiry) {
        return new MemCachedFleaCache(name, expiry, memcachedClient);
    }

    public void setMemcachedClient(MemCachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

}
