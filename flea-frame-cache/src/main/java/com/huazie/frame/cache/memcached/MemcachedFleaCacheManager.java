package com.huazie.frame.cache.memcached;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.memcached.config.MemcachedConfig;
import com.huazie.frame.cache.memcached.impl.MemcachedFleaCache;
import com.whalin.MemCached.MemCachedClient;

/**
 * <p> Memcached的Flea缓存管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemcachedFleaCacheManager extends AbstractFleaCacheManager {

    private static MemcachedFleaCacheManager cacheManager;
    private MemCachedClient memcachedClient;   // memcached 客户端类

    private MemcachedFleaCacheManager(MemCachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    /**
     * <p> 获取Flea缓存管理类 </p>
     *
     * @return Memcached的Flea缓存管理类实例对象
     * @since 1.0.0
     */
    public static MemcachedFleaCacheManager getInstance() {
        if (cacheManager == null) {
            // 初始化连接池
            MemcachedConfig.initSockIOPool();
            cacheManager = new MemcachedFleaCacheManager(MemcachedConfig.getClient());
        }
        return cacheManager;
    }

    @Override
    protected AbstractFleaCache newCache(String name, int expire) {
        return new MemcachedFleaCache(name, expire, this.memcachedClient);
    }

    public void setMemcachedClient(MemCachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

}
