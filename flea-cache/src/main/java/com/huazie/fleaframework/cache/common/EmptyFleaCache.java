package com.huazie.fleaframework.cache.common;

import com.huazie.fleaframework.cache.AbstractFleaCache;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

/**
 * 空 Flea 缓存实现，如果缓存配置开关是关闭状态，则创建空缓存用于使用
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class EmptyFleaCache extends AbstractFleaCache {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(EmptyFleaCache.class);

    public EmptyFleaCache(String name, int expiry, int nullCacheExpiry) {
        super(name, expiry, nullCacheExpiry);
        this.cache = CacheEnum.EmptyCache;
    }

    @Override
    public Object getNativeValue(String key) {
        LOGGER.debug1(new Object() {}, "空缓存getNativeValue实现");
        return null;
    }

    @Override
    public Object putNativeValue(String key, Object value, int expiry) {
        LOGGER.debug1(new Object() {}, "空缓存putNativeValue实现");
        return null;
    }

    @Override
    public Object deleteNativeValue(String key) {
        LOGGER.debug1(new Object() {}, "空缓存deleteNativeValue实现");
        return null;
    }

    @Override
    public String getSystemName() {
        return "NULL";
    }
}
