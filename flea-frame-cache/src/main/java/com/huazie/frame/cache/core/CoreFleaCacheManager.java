package com.huazie.frame.cache.core;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.core.impl.CoreFleaCache;

/**
 * <p> 核心Flea缓存管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CoreFleaCacheManager extends AbstractFleaCacheManager {

    @Override
    protected AbstractFleaCache newCache(String name, long expiry) {
        return new CoreFleaCache(name);
    }
}
