package com.huazie.frame.cache.core;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.AbstractSpringCacheManager;
import com.huazie.frame.cache.core.impl.CoreSpringCache;

/**
 * <p> 核心Spring Cache管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CoreSpringCacheManager extends AbstractSpringCacheManager {

    @Override
    protected AbstractSpringCache newCache(String name, long expiry) {
        return new CoreSpringCache(name);
    }
}
