package com.huazie.frame.cache.memcached;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.memcached.impl.MemCachedFleaCache;
import com.whalin.MemCached.MemCachedClient;

/**
 * <p> MemCached Flea缓存管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemCachedFleaCacheManager extends AbstractFleaCacheManager {

    private MemCachedClient memCachedClient;   // Memcached客户端类

    /**
     * <p> 新建一个MemCached Flea缓存管理类 </p>
     *
     * @since 1.0.0
     */
    public MemCachedFleaCacheManager() {
        memCachedClient = new MemCachedClient();
        initPool();
    }

    /**
     * <p> 新建一个MemCached Flea缓存管理类 </p>
     *
     * @param memCachedClient MemCached客户端
     * @since 1.0.0
     */
    public MemCachedFleaCacheManager(MemCachedClient memCachedClient) {
        this.memCachedClient = memCachedClient;
        initPool();
    }

    /**
     * 初始化MemCached连接池
     *
     * @since 1.0.0
     */
    private void initPool() {
        MemCachedPool pool = MemCachedPool.getInstance();
        pool.initialize();
    }

    @Override
    protected AbstractFleaCache newCache(String name, long expiry) {
        return new MemCachedFleaCache(name, expiry, memCachedClient);
    }

}
