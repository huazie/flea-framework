package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.AbstractSpringCacheManager;

/**
 * <p> MemcachedCacheManager管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisFleaCacheManager extends AbstractSpringCacheManager {

    @Override
    protected AbstractSpringCache newCache(String name, int expire) {
        return null;
    }

}
