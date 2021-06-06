package com.huazie.frame.cache.core.impl;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.common.CacheConfigUtils;
import com.huazie.frame.cache.common.CacheConstants.FleaCacheConfigConstants;
import com.huazie.frame.cache.common.FleaCacheFactory;
import com.huazie.frame.cache.config.CacheItem;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ObjectUtils;

/**
 * 核心Flea缓存类，该类继承{@code AbstractFleaCache}，实现读
 * {@code getNativeValue}、写{@code putNativeValue} 和 删除
 * {@code delete} 缓存的基本操作方法，用于整合各类缓存的接入。
 *
 * <p> 成员变量 {@code fleaCache}, 在构造方法中根据缓存主关
 * 键字指定具体的Flea缓存实现，在读、写 和删除缓存的基本操作
 * 方法中调用 {@code fleaCache} 的具体实现。
 *
 * @author huazie
 * @version 1.0.0
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
        // 根据缓存主关键字name获取指定Flea缓存对象
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
    public void putNativeValue(String key, Object value, int expiry) {
        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "CORE FLEA CACHE, KEY = {}", key);
            LOGGER.debug1(obj, "CORE FLEA CACHE, VALUE = {}", value);
            LOGGER.debug1(obj, "CORE FLEA CACHE, EXPIRY = {}s", expiry);
            LOGGER.debug1(obj, "CORE FLEA CACHE, NULL CACHE EXPIRY = {}s", getNullCacheExpiry());
        }
        fleaCache.putNativeValue(key, value, expiry);
    }

    @Override
    public void deleteNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "KEY = {}", key);
        }
        fleaCache.deleteNativeValue(key);
    }

    @Override
    public String getSystemName() {
        // 获取缓存初始化配置项集之缓存所属系统名配置项
        CacheItem cacheItem = CacheConfigUtils.getCacheItem(FleaCacheConfigConstants.FLEA_CACHE_INIT, FleaCacheConfigConstants.SYSTEM_NAME);
        if (ObjectUtils.isEmpty(cacheItem)) {
            throw new RuntimeException("无法获取缓存系统名，请检查flea-cache-config.xml配置【<cache-item key=" + FleaCacheConfigConstants.SYSTEM_NAME + " >】\"");
        }
        return cacheItem.getValue();
    }
}
