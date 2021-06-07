package com.huazie.frame.cache.memcached.impl;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.NullCache;
import com.huazie.frame.cache.common.CacheEnum;
import com.huazie.frame.cache.memcached.config.MemCachedConfig;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ObjectUtils;
import com.whalin.MemCached.MemCachedClient;

import java.util.Date;

/**
 * MemCached Flea缓存类，实现了以Flea框架操作MemCached缓存的基本操作方法。
 *
 * <p> 在上述基本操作方法中，实际使用MemCached客户端【{@code} memCachedClient】
 * 读、写和删除MemCached缓存。其中写缓存方法【{@code putNativeValue}】在
 * 添加的数据值为【{@code null}】时，默认添加空缓存数据【{@code NullCache}】
 * 到MemCached中，有效期取初始化参数【{@code nullCacheExpiry}】。
 *
 * <p> 单个缓存接入场景，有效期配置可查看【memcached.properties】中的配置
 * 参数【memcached.nullCacheExpiry】
 *
 * <p> 整合缓存接入场景，有效期配置可查看【flea-cache-config.xml】
 * 中的缓存参数【{@code <cache-param key="fleacore.nullCacheExpiry"
 * desc="空缓存数据有效期（单位：s）">300</cache-param>}】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemCachedFleaCache extends AbstractFleaCache {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(MemCachedFleaCache.class);

    private final MemCachedClient memCachedClient;  // MemCached客户端

    /**
     * <p> 初始化MemCached Flea缓存类 </p>
     *
     * @param name            缓存数据主关键字
     * @param expiry          缓存数据有效期（单位：s）
     * @param nullCacheExpiry 空缓存数据有效期（单位：s）
     * @param memCachedClient MemCached客户端
     * @since 1.0.0
     */
    public MemCachedFleaCache(String name, int expiry, int nullCacheExpiry, MemCachedClient memCachedClient) {
        super(name, expiry, nullCacheExpiry);
        this.memCachedClient = memCachedClient;
        cache = CacheEnum.MemCached;
    }

    @Override
    public Object getNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "KEY = {}", key);
        }
        return memCachedClient.get(key);
    }

    @Override
    public void putNativeValue(String key, Object value, int expiry) {
        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "MEMCACHED FLEA CACHE, KEY = {}", key);
            LOGGER.debug1(obj, "MEMCACHED FLEA CACHE, VALUE = {}", value);
            LOGGER.debug1(obj, "MEMCACHED FLEA CACHE, EXPIRY = {}s", expiry);
            LOGGER.debug1(obj, "MEMCACHED FLEA CACHE, NULL CACHE EXPIRY = {}s", getNullCacheExpiry());
        }
        if (ObjectUtils.isEmpty(value))
            memCachedClient.set(key, new NullCache(key), new Date(getNullCacheExpiry() * 1000));
        else
            memCachedClient.set(key, value, new Date(expiry * 1000));
    }

    @Override
    public void deleteNativeValue(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "KEY = {}", key);
        }
        memCachedClient.delete(key);
    }

    @Override
    public String getSystemName() {
        return MemCachedConfig.getConfig().getSystemName();
    }
}
