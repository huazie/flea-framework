package com.huazie.frame.cache.core;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;

/**
 * <p> 核心Flea Cache 管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CoreFleaCacheManager extends AbstractFleaCacheManager {

    public CoreFleaCacheManager() {
    }

    @Override
    protected AbstractFleaCache newCache(String name, long expiry) {
        return null;
    }
}
