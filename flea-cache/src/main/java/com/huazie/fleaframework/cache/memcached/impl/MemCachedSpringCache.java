package com.huazie.fleaframework.cache.memcached.impl;

import com.huazie.fleaframework.cache.AbstractSpringCache;
import com.huazie.fleaframework.cache.IFleaCache;
import com.whalin.MemCached.MemCachedClient;

/**
 * MemCached Spring缓存类，继承了抽象Spring缓存父类的
 * 读、写、删除 和 清空 缓存的基本操作方法，由MemCached Spring缓存管理类初始化。
 *
 * <p> 它的构造方法中，必须传入一个具体Flea缓存实现类，这里我们使用
 * MemCached Flea缓存【{@code MemCachedFleaCache}】。
 *
 * @author huazie
 * @version 1.0.0
 * @see MemCachedFleaCache
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
