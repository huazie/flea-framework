package com.huazie.fleaframework.cache.core.impl;

import com.huazie.fleaframework.cache.AbstractFleaCache;
import com.huazie.fleaframework.cache.common.CacheConfigUtils;
import com.huazie.fleaframework.cache.common.FleaCacheFactory;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

/**
 * 核心Flea缓存类，实现读、写和删除缓存的基本操作方法，用于整合各类缓存的接入。
 *
 * <p> 成员变量【{@code fleaCache}】，在构造方法中根据缓存数据主关键字
 * 指定具体的Flea缓存实现，在 读、写 和 删除缓存的基本操作方法中调用
 * 【{@code fleaCache}】的具体实现。
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.0.0
 */
public class CoreFleaCache extends AbstractFleaCache {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(CoreFleaCache.class);

    private AbstractFleaCache fleaCache; // 指定Flea缓存实现

    /**
     * 初始化核心Flea缓存
     *
     * @param name 缓存数据主关键字
     * @since 1.0.0
     */
    public CoreFleaCache(String name) {
        super(name, CacheConfigUtils.getExpiry(name), CacheConfigUtils.getNullCacheExpiry());
        // 根据缓存数据主关键字name获取指定Flea缓存对象
        fleaCache = FleaCacheFactory.getFleaCache(name);
        // 取指定Flea缓存的缓存类型
        cache = fleaCache.getCache();
    }

    @Override
    public Object getNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "KEY = {}", key);
        }
        return fleaCache.getNativeValue(key);
    }

    @Override
    public Object putNativeValue(String key, Object value, int expiry) {
        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "CORE FLEA CACHE, KEY = {}", key);
            LOGGER.debug1(obj, "CORE FLEA CACHE, VALUE = {}", value);
            LOGGER.debug1(obj, "CORE FLEA CACHE, EXPIRY = {}s", expiry);
            LOGGER.debug1(obj, "CORE FLEA CACHE, NULL CACHE EXPIRY = {}s", getNullCacheExpiry());
        }
        return fleaCache.putNativeValue(key, value, expiry);
    }

    @Override
    public Object deleteNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "KEY = {}", key);
        }
        return fleaCache.deleteNativeValue(key);
    }

    @Override
    public String getSystemName() {
        return CacheConfigUtils.getSystemName();
    }
}
