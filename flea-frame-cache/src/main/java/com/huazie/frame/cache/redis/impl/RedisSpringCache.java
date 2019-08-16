package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.IFleaCache;
import com.huazie.frame.cache.redis.RedisClient;

/**
 * <p> Redis Spring缓存类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisSpringCache extends AbstractSpringCache {

    /**
     * <p> 带参数的构造方法，初始化MemCached Spring缓存类 </p>
     *
     * @param name      缓存主关键字
     * @param fleaCache 具体缓存实现
     * @since 1.0.0
     */
    public RedisSpringCache(String name, IFleaCache fleaCache) {
        super(name, fleaCache);
    }

    /**
     * <p> 带参数的构造方法，初始化MemCached Spring缓存类 </p>
     *
     * @param name        缓存主关键字
     * @param expiry      失效时长
     * @param redisClient Redis客户端
     * @since 1.0.0
     */
    public RedisSpringCache(String name, long expiry, RedisClient redisClient) {
        this(name, new RedisFleaCache(name, expiry, redisClient));
    }

}
