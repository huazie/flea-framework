package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.AbstractSpringCacheManager;
import com.huazie.frame.cache.redis.impl.RedisFleaCache;
import org.springframework.cache.support.AbstractCacheManager;

/**
 * <p> Redis Flea缓存管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisFleaCacheManager extends AbstractFleaCacheManager {

    @Override
    protected AbstractFleaCache newCache(String name, long expiry) {
        return new RedisFleaCache(name, expiry);
    }

}
