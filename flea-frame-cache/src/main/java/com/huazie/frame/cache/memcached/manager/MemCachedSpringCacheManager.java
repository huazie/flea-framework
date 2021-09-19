package com.huazie.frame.cache.memcached.manager;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.AbstractSpringCacheManager;
import com.huazie.frame.cache.memcached.MemCachedPool;
import com.huazie.frame.cache.memcached.config.MemCachedConfig;
import com.huazie.frame.cache.memcached.impl.MemCachedSpringCache;
import com.whalin.MemCached.MemCachedClient;

/**
 * MemCached Spring缓存管理类，用于接入Spring框架管理 MemCached 缓存。
 *
 * <p> 它的默认构造方法，用于单个缓存接入场景，首先新建MemCached 客户端，
 * 然后初始化 MemCached 连接池。
 *
 * <p> 方法【{@code newCache}】用于创建一个MemCached Spring缓存，
 * 而它内部是由MemCached Flea缓存实现具体的 读、写、删除 和 清空
 * 缓存的基本操作。
 *
 * @author huazie
 * @version 1.0.0
 * @see MemCachedSpringCache
 * @since 1.0.0
 */
public class MemCachedSpringCacheManager extends AbstractSpringCacheManager {

    private MemCachedClient memCachedClient;   // MemCached客户端类

    /**
     * 用于新建MemCached客户端，并初始化MemCached连接池。
     *
     * @since 1.0.0
     */
    public MemCachedSpringCacheManager() {
        memCachedClient = new MemCachedClient();
        initPool();
    }

    /**
     * 以传入参数初始化MemCached客户端，并初始化MemCached连接池。
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
    private void initPool() {
        MemCachedPool.getInstance().initialize();
    }

    @Override
    protected AbstractSpringCache newCache(String name, int expiry) {
        int nullCacheExpiry = MemCachedConfig.getConfig().getNullCacheExpiry();
        return new MemCachedSpringCache(name, expiry, nullCacheExpiry, memCachedClient);
    }

}
