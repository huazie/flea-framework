package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.AbstractSpringCacheManager;
import com.huazie.frame.cache.redis.impl.RedisSpringCache;

/**
 * <p> Redis Spring缓存管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisSpringCacheManager extends AbstractSpringCacheManager {

    @Override
    protected AbstractSpringCache newCache(String name, long expiry) {
        return new RedisSpringCache(name, expiry);
    }

}
