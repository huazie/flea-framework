package com.huazie.frame.cache.memcached;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.memcached.impl.MemCachedFleaCache;
import com.huazie.frame.common.util.ObjectUtils;
import com.whalin.MemCached.MemCachedClient;

/**
 * <p> Memcached Flea缓存管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemCachedFleaCacheManager extends AbstractFleaCacheManager {

    private static volatile MemCachedFleaCacheManager cacheManager;

    private MemCachedClient memcachedClient;   // Memcached客户端类

    private MemCachedFleaCacheManager(MemCachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    /**
     * <p> 获取Flea缓存管理类 </p>
     *
     * @return Memcached的Flea缓存管理类实例对象
     * @since 1.0.0
     */
    public static MemCachedFleaCacheManager getInstance() {
        if (ObjectUtils.isEmpty(cacheManager)) {
            synchronized (MemCachedFleaCacheManager.class) {
                if (ObjectUtils.isEmpty(cacheManager)) {
                    cacheManager = new MemCachedFleaCacheManager(new MemCachedClient());
                }
            }
        }
        return cacheManager;
    }

    /**
     * 初始化Memcached连接池
     *
     * @since 1.0.0
     */
    public void initPool() {
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
