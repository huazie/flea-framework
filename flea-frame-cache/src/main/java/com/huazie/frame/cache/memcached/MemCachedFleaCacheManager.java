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

    private static volatile MemCachedFleaCacheManager cacheManager;

    private MemCachedClient memCachedClient;   // Memcached客户端类

    /**
     * <p> 新建一个MemCached客户端 </p>
     *
     * @since 1.0.0
     */
    public MemCachedFleaCacheManager() {
        memCachedClient = new MemCachedClient();
        initPool();
    }

    /**
     * <p> 新建一个MemCached客户端 </p>
     *
     * @param memcachedClient MemCached客户端
     * @since 1.0.0
     */
    public MemCachedFleaCacheManager(MemCachedClient memcachedClient) {
        this.memCachedClient = memcachedClient;
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
        return new MemCachedFleaCache(name, expiry, memCachedClient);
    }

    public void setMemCachedClient(MemCachedClient memCachedClient) {
        this.memCachedClient = memCachedClient;
    }

}
