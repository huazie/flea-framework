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

    public RedisSpringCache(String name, IFleaCache fleaCache) {
        super(name, fleaCache);
    }

    public RedisSpringCache(String name, long expiry, RedisClient redisClient) {
        this(name, new RedisFleaCache(name, expiry, redisClient));
    }

}
