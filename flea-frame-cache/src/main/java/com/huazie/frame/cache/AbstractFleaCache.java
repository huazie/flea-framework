package com.huazie.frame.cache;

import com.huazie.frame.cache.common.CacheEnum;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * <p> 抽象Flea Cache类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractFleaCache implements IFleaCache {

    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractFleaCache.class);

    private final String name;  // 缓存主要关键字（用于区分）

    private final long expiry;  // 有效期(单位：秒)

    protected CacheEnum cache;  // 缓存类型

    public AbstractFleaCache(String name, long expiry) {
        this.name = name;
        this.expiry = expiry;
    }

    @Override
    public Object get(String key) {
        Object value = null;
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("AbstractFleaCache##get(String) KEY = {}", key);
            }
            value = getNativeValue(getNativeKey(key));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("AbstractFleaCache##get(String) VALUE = {}", value);
            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("The action of getting [" + cache.getName() + "] cache occurs exception ：", e);
            }
        }
        return value;
    }

    @Override
    public void put(String key, Object value) {
        if (ObjectUtils.isEmpty(value))
            return;
        try {
            putNativeValue(getNativeKey(key), value, expiry);
            // 将指定Cache的key添加到Set集合，并存于缓存中
            addCacheKey(key);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("The action of adding [" + cache.getName() + "] cache occurs exception ：", e);
            }
        }
    }

    @Override
    public void clear() {
        Set<String> keySet = getCacheKey();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("AbstractFleaCache##clear() KEYS = {}", keySet);
        }
        if (CollectionUtils.isNotEmpty(keySet)) {
            for (String key : keySet) {
                deleteNativeValue(getNativeKey(key));
            }
            // 删除 记录当前Cache所有数据键关键字 的缓存
            deleteCacheAllKey();
        }
    }

    @Override
    public void delete(String key) {
        try {

            deleteNativeValue(getNativeKey(key));
            // 从 记录当前Cache所有数据键关键字 的缓存中 删除指定数据键关键字key
            deleteCacheKey(key);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("The action of deleting [" + cache.getName() + "] cache occurs exception ：", e);
            }
        }
    }

    /**
     * <p> 将指定数据键关键字{@code key}记录到当前Cache所有数据键关键字的缓存中 </p>
     *
     * @param key 指定Cache的数据键关键字
     * @since 1.0.0
     */
    private void addCacheKey(String key) {
        Set<String> keySet = getCacheKey();
        if (CollectionUtils.isEmpty(keySet)) {
            keySet = new HashSet<>();
        }
        keySet.add(key);
        putNativeValue(name, keySet, CommonConstants.NumeralConstants.ZERO);
    }

    /**
     * <p> 从 记录当前Cache所有数据键关键字 的缓存中 删除指定数据键关键字{@code key} </p>
     *
     * @param key 指定Cache的数据键关键字
     * @since 1.0.0
     */
    private void deleteCacheKey(String key) {
        Set<String> keySet = getCacheKey();
        if (CollectionUtils.isNotEmpty(keySet)) {
            // 存在待删除的数据键关键字
            if (keySet.contains(key)) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("AbstractFleaCache##deleteCacheKey() Delete cache of recording all key, KEY = {}", key);
                }
                if (CommonConstants.NumeralConstants.INT_ONE == keySet.size()) {
                    deleteCacheAllKey(); // 直接将记录当前Cache所有数据键关键字的缓存从Redis中清空
                } else {
                    // 将数据键关键字从Set集合中删除
                    keySet.remove(key);
                    // 重新覆盖当前Cache所有数据键关键字的缓存信息
                    putNativeValue(name, keySet, CommonConstants.NumeralConstants.ZERO);
                }
            } else {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("AbstractFleaCache##deleteCacheKey() The CacheKey of [{}] is not exist", key);
                }
            }
        }
    }

    /**
     * <p> 删除 记录当前Cache所有数据键关键字 的缓存 </p>
     *
     * @since 1.0.0
     */
    private void deleteCacheAllKey() {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("AbstractFleaCache##deleteCacheAllKey() Delete cache of recording all key");
            }
            deleteNativeValue(name);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("The action of deleting [" + cache.getName() + "] cache occurs exception ：", e);
            }
        }
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public Set<String> getCacheKey() {
        Set<String> keySet = null;
        Object keySetObj = getNativeValue(name);
        if (ObjectUtils.isNotEmpty(keySetObj) && keySetObj instanceof Set) {
            keySet = (Set<String>) keySetObj;
        }
        return keySet;
    }

    /**
     * <p> 获取缓存值 </p>
     *
     * @param key 缓存关键字
     * @return 缓存值
     * @since 1.0.0
     */
    protected abstract Object getNativeValue(String key);

    /**
     * <p> 添加缓存数据 </p>
     *
     * @param key    缓存关键字
     * @param value  缓存值
     * @param expiry 失效时间（单位：秒）
     * @since 1.0.0
     */
    protected abstract void putNativeValue(String key, Object value, long expiry);

    /**
     * <p> 删除指定缓存数据 </p>
     *
     * @param key 缓存关键字
     * @since 1.0.0
     */
    protected abstract void deleteNativeValue(String key);

    /**
     * <p> 获取实际存储的缓存键（缓存名 + 缓存关键字） </p>
     *
     * @param key 缓存关键字
     * @return 实际存储的缓存键
     * @since 1.0.0
     */
    protected String getNativeKey(String key) {
        return name + CommonConstants.SymbolConstants.UNDERLINE + key;
    }

    public String getName() {
        return name;
    }

    public long getExpiry() {
        return expiry;
    }

    public String getCacheName() {
        return cache.getName();
    }

    public String getCacheDesc() {
        return cache.getDesc();
    }

}
