package com.huazie.frame.cache.core.impl;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.common.CacheConfigManager;
import com.huazie.frame.cache.common.CacheConstants;
import com.huazie.frame.cache.common.FleaCacheFactory;
import com.huazie.frame.cache.config.CacheItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> 核心Flea缓存类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CoreFleaCache extends AbstractFleaCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoreFleaCache.class);

    private AbstractFleaCache fleaCache; // 指定Flea缓存实现

    /**
     * <p> 带参数构造方法，初始化核心Flea缓存类 </p>
     *
     * @param name 缓存主关键字
     * @since 1.0.0
     */
    public CoreFleaCache(String name) {
        super(name, CacheConfigManager.getExpiry(name));
        // 根据缓存主关键字name获取指定Flea缓存对象
        fleaCache = FleaCacheFactory.getFleaCache(name);
        // 取指定Flea缓存的缓存类型
        cache = fleaCache.getCache();
    }

    @Override
    public Object getNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("CoreFleaCache##getNativeValue(String) KEY = {}", key);
        }
        return fleaCache.getNativeValue(key);
    }

    @Override
    public void putNativeValue(String key, Object value, long expiry) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("CoreFleaCache##putNativeValue(String, Object, long) KEY = {}", key);
            LOGGER.debug("CoreFleaCache##putNativeValue(String, Object, long) VALUE = {}", value);
            LOGGER.debug("CoreFleaCache##putNativeValue(String, Object, long) EXPIRY = {}s", expiry);
        }
        fleaCache.putNativeValue(key, value, expiry);
    }

    @Override
    public void deleteNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("CoreFleaCache##deleteNativeValue(String) KEY = {}", key);
        }
        fleaCache.deleteNativeValue(key);
    }

    @Override
    public String getSystemName() {
        // 获取缓存初始化配置项集之缓存所属系统名配置项
        CacheItem cacheItem = CacheConfigManager.getCacheItem(CacheConstants.FleaCacheConfigConstants.FLEA_CACHE_INIT, CacheConstants.FleaCacheConfigConstants.SYSTEM_NAME);
        if (null == cacheItem) {
            throw new RuntimeException("无法获取缓存系统名，请检查flea-cache-config.xml配置【<cache-item key=" + CacheConstants.FleaCacheConfigConstants.SYSTEM_NAME + " >】\"");
        }
        return cacheItem.getValue();
    }
}
