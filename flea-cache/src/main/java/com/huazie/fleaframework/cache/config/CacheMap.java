package com.huazie.fleaframework.cache.config;

import com.huazie.fleaframework.common.config.ConfigMap;

/**
 * 缓存定义 Map
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public abstract class CacheMap extends ConfigMap<Cache> {

    @Override
    protected String getConfigKey(Cache cache) {
        return cache.getKey();
    }

    /**
     * 根据缓存数据键Key获取指定的Flea缓存
     *
     * @param key 缓存数据键
     * @return Flea缓存
     * @since 2.0.0
     */
    public Cache getFleaCache(String key) {
        return getConfig(key);
    }
}
