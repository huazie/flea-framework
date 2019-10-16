package com.huazie.frame.cache.memcached;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.AbstractSpringCacheManager;
import com.huazie.frame.cache.memcached.impl.MemCachedSpringCache;
import com.whalin.MemCached.MemCachedClient;

/**
 * <p> Memcached的Spring缓存管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemCachedSpringCacheManager extends AbstractSpringCacheManager {

    private MemCachedClient memCachedClient;   // Memcached客户端类

    /**
     * <p> 新建一个MemCached Spring缓存管理类 </p>
     *
     * @since 1.0.0
     */
    public MemCachedSpringCacheManager() {
        memCachedClient = new MemCachedClient();
        initPool();
    }

    /**
     * <p> 新建一个MemCached Spring缓存管理类 </p>
     *
     * @param memCachedClient MemCached客户端
     */
    public MemCachedSpringCacheManager(MemCachedClient memCachedClient) {
        this.memCachedClient = memCachedClient;
        initPool();
    }

    /**
     * <p> 初始化MemCached连接池 </p>
     *
     * @since 1.0.0
     */
    public void initPool() {
        MemCachedPool.getInstance().initialize();
    }

    @Override
    protected AbstractSpringCache newCache(String name, long expiry) {
        return new MemCachedSpringCache(name, expiry, memCachedClient);
    }

}
