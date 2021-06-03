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
 * 抽象 Spring 缓存类，实现Spring的Cache 和 自定义的 IFleaCache接口，
 * 由具体的Spring缓存管理类初始化。
 *
 * <p> 它实现的读 {@code get}写 {@code put}、删除 {@code delete}
 * 和 清空 {@code clear}缓存的基本操作方法，由具体Flea缓存实现类{@code fleaCache}
 * 调用对应的方法实现。
 *
 * @author huazie
 * @version 1.0.0
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
        if (ObjectUtils.isEmpty(key))
            return null;
        Object cacheValue = get(key.toString());
        if (ObjectUtils.isNotEmpty(type) && !type.isInstance(cacheValue)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug1(new Object() {}, "Cached value is not of required type [{}]: {}", type.getName(), cacheValue);
            }
            return null;
        }
        return (T) cacheValue;
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
