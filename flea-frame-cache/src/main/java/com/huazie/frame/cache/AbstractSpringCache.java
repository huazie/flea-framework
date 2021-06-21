package com.huazie.frame.cache;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.Set;
import java.util.concurrent.Callable;

/**
 * 抽象 Spring 缓存类，实现Spring的Cache接口 和 Flea
 * 的IFleaCache接口，由具体的Spring缓存管理类初始化。
 *
 * <p> 它实现了读、写、删除和清空缓存的基本操作方法，
 * 内部由具体Flea缓存实现类【{@code fleaCache}】
 * 调用对应的 读、写、删除 和 清空 缓存的基本操作方法。
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.0.0
 */
public abstract class AbstractSpringCache implements Cache, IFleaCache {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(AbstractSpringCache.class);

    private final String name;  // 缓存主要关键字（用于区分）

    private final IFleaCache fleaCache; // 具体Flea缓存实现

    public AbstractSpringCache(String name, IFleaCache fleaCache) {
        this.name = name;
        this.fleaCache = fleaCache;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IFleaCache getNativeCache() {
        return fleaCache;
    }

    @Override
    public ValueWrapper get(Object key) {
        if (ObjectUtils.isEmpty(key))
            return null;
        ValueWrapper wrapper = null;
        Object cacheValue = get(key.toString());
        if (ObjectUtils.isNotEmpty(cacheValue)) {
            wrapper = new SimpleValueWrapper(cacheValue);
        }
        return wrapper;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public <T> T get(Object key, Class<T> type) {
        if (ObjectUtils.isEmpty(key) || ObjectUtils.isEmpty(type))
            return null;
        Object cacheValue = get(key.toString());
        if (!type.isInstance(cacheValue)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug1(new Object() {}, "Cached value is not of required type [{}] : {}", type.getName(), cacheValue);
            }
            return null;
        }
        return type.cast(cacheValue);
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public Object get(String key) {
        if (StringUtils.isBlank(key))
            return null;
        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "KEY = {}", key);
        }
        Object cacheValue = fleaCache.get(key);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "VALUE = {}", cacheValue);
        }
        return cacheValue;
    }

    @Override
    public void put(Object key, Object value) {
        if (ObjectUtils.isEmpty(key))
            return;
        put(key.toString(), value);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        if (ObjectUtils.isEmpty(key))
            return null;
        ValueWrapper wrapper = null;
        Object cacheValue = get(key.toString());
        if (ObjectUtils.isEmpty(cacheValue)) {
            put(key.toString(), value);
        } else {
            wrapper = new SimpleValueWrapper(cacheValue);
        }
        return wrapper;
    }

    @Override
    public void put(String key, Object value) {
        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "KEY = {}", key);
            LOGGER.debug1(obj, "VALUE = {}", value);
        }
        fleaCache.put(key, value);
    }

    @Override
    public void evict(Object key) {
        if (ObjectUtils.isEmpty(key))
            return;
        delete(key.toString());
    }

    @Override
    public void clear() {
        fleaCache.clear();
    }

    @Override
    public void delete(String key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "KEY = {}", key);
        }
        fleaCache.delete(key);
    }

    @Override
    public Set<String> getCacheKey() {
        return fleaCache.getCacheKey();
    }

    @Override
    public String getSystemName() {
        return fleaCache.getSystemName();
    }
}
