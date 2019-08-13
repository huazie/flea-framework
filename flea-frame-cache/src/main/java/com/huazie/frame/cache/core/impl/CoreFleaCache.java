package com.huazie.frame.cache.core.impl;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.common.CacheConfigManager;
import com.huazie.frame.cache.common.FleaCacheFactory;

/**
 * <p> 核心Flea缓存类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CoreFleaCache extends AbstractFleaCache {

    private AbstractFleaCache fleaCache; // 指定Flea缓存实现

    public CoreFleaCache(String name) {
        super(name, CacheConfigManager.getExpiry(name));
        // 根据缓存主关键字name获取指定Flea缓存对象
        fleaCache = FleaCacheFactory.getFleaCache(name);
    }

    @Override
    public Object getNativeValue(String key) {
        return fleaCache.getNativeValue(key);
    }

    @Override
    public void putNativeValue(String key, Object value, long expiry) {
        fleaCache.putNativeValue(key, value, expiry);
    }

    @Override
    public void deleteNativeValue(String key) {
        fleaCache.deleteNativeValue(key);
    }
}
