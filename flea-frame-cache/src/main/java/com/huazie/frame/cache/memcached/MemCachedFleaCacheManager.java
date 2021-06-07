package com.huazie.frame.cache.memcached;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.memcached.config.MemCachedConfig;
import com.huazie.frame.cache.memcached.impl.MemCachedFleaCache;
import com.whalin.MemCached.MemCachedClient;

/**
 * MemCached Flea缓存管理类，用于接入Flea框架管理MemCached 缓存。
 *
 * <p> 它的默认构造方法，用于单个缓存接入场景，首先新建MemCached客户端，
 * 然后初始化 MemCached 连接池。
 *
 * <p> 方法 {@code newCache}，用于创建一个MemCached Flea缓存，
 * 它里面包含了 读、写、删除 和 清空 缓存的基本操作。
 *
 * @author huazie
 * @version 1.0.0
 * @see MemCachedFleaCache
 * @since 1.0.0
 */
public class MemCachedFleaCacheManager extends AbstractFleaCacheManager {

    private MemCachedClient memCachedClient;   // MemCached客户端类

    /**
     * 用于新建MemCached客户端，并初始化MemCached连接池。
     *
     * @since 1.0.0
     */
    public MemCachedFleaCacheManager() {
        memCachedClient = new MemCachedClient();
        initPool();
    }

    /**
     * 以传入参数初始化MemCached客户端，并初始化MemCached连接池。
     *
     * @param memCachedClient MemCached客户端
     * @since 1.0.0
     */
    public MemCachedFleaCacheManager(MemCachedClient memCachedClient) {
        this.memCachedClient = memCachedClient;
        initPool();
    }

    /**
     * <p> 初始化MemCached连接池 </p>
     *
     * @since 1.0.0
     */
    private void initPool() {
        MemCachedPool.getInstance().initialize();
    }

    @Override
    protected AbstractFleaCache newCache(String name, int expiry) {
        int nullCacheExpiry = MemCachedConfig.getConfig().getNullCacheExpiry();
        return new MemCachedFleaCache(name, expiry, nullCacheExpiry, memCachedClient);
    }

}
