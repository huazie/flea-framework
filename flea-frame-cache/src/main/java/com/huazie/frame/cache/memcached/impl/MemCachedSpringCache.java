package com.huazie.frame.cache.memcached.impl;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.IFleaCache;
import com.whalin.MemCached.MemCachedClient;

/**
 * <p> MemCached Spring Cache的实现(实现spring的Cache接口)</p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemCachedSpringCache extends AbstractSpringCache {

    /**
     * <p> 带参数的构造方法，初始化MemCached Spring缓存类 </p>
     *
     * @param name      缓存数据主关键字
     * @param fleaCache 具体缓存实现
     * @since 1.0.0
     */
    public MemCachedSpringCache(String name, IFleaCache fleaCache) {
        super(name, fleaCache);
    }

    /**
     * <p> 带参数的构造方法，初始化MemCached Spring缓存类 </p>
     *
     * @param name            缓存数据主关键字
     * @param expiry          缓存数据有效期（单位：s）
     * @param nullCacheExpiry 空缓存数据有效期（单位：s）
     * @param memCachedClient MemCached客户端
     * @since 1.0.0
     */
    public MemCachedSpringCache(String name, int expiry, int nullCacheExpiry, MemCachedClient memCachedClient) {
        this(name, new MemCachedFleaCache(name, expiry, nullCacheExpiry, memCachedClient));
    }

}
