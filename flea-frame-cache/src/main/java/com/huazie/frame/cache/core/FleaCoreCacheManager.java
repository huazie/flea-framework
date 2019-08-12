package com.huazie.frame.cache.core;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;

/**
 * <p> Flea Cache 管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCoreCacheManager extends AbstractFleaCacheManager {

    public FleaCoreCacheManager() {
    }

    @Override
    protected AbstractFleaCache newCache(String name, long expiry) {
        return null;
    }
}
