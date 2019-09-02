package com.huazie.frame.cache;

import com.huazie.frame.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.Set;
import java.util.concurrent.Callable;

/**
 * <p> 抽象Spring缓存类，实现Spring的Cache 和 自定义的 IFleaCache接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractSpringCache implements Cache, IFleaCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSpringCache.class);

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
        ValueWrapper wrapper = null;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("AbstractSpringCache##get(Object) KEY = {}", key);
        }
        Object cacheValue = fleaCache.get(key.toString());
        if (ObjectUtils.isNotEmpty(cacheValue)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("AbstractSpringCache##get(Object) VALUE = {}", cacheValue);
            }
            wrapper = new SimpleValueWrapper(cacheValue);
        }
        return wrapper;
    }

    @Override
    public void put(Object key, Object value) {
        fleaCache.put(key.toString(), value);
    }

    @Override
    public void evict(Object key) {
        fleaCache.delete(key.toString());
    }

    @Override
    public void clear() {
        fleaCache.clear();
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public <T> T get(Object key, Class<T> type) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("AbstractSpringCache##get(Object) KEY = {}", key);
        }
        Object cacheValue = fleaCache.get(key.toString());
        if (ObjectUtils.isNotEmpty(type) && !type.isInstance(cacheValue)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("AbstractSpringCache##get(Object, Class<T>) Cached value is not of required type [{}]: {}", type.getName(), cacheValue);
            }
            return null;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("AbstractSpringCache##get(Object, Class<T>) VALUE = {}", cacheValue);
        }
        return (T) cacheValue;
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        ValueWrapper wrapper = null;
        Object cacheValue = fleaCache.get(key.toString());
        if (ObjectUtils.isEmpty(cacheValue)) {
            fleaCache.put(key.toString(), value);
        } else {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("AbstractSpringCache##putIfAbsent(Object, Object) VALUE = {}", cacheValue);
            }
            wrapper = new SimpleValueWrapper(cacheValue);
        }
        return wrapper;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public Object get(String key) {
        return fleaCache.get(key);
    }

    @Override
    public void put(String key, Object value) {
        fleaCache.put(key, value);
    }

    @Override
    public void delete(String key) {
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
