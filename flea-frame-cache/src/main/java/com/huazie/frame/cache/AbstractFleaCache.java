package com.huazie.frame.cache;

import com.huazie.frame.cache.common.CacheEnum;
import com.huazie.frame.common.CommonConstants;
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

    private Set<String> keySet = new HashSet<String>();
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
                LOGGER.debug("AbstractFleaCache##get(String) KEY={}", key);
            }
            value = getNativeValue(getNativeKey(key));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("AbstractFleaCache##get(String) VALUE={}", value);
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
            keySet.add(key);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("The action of adding [" + cache.getName() + "] cache occurs exception ：", e);
            }
        }
    }

    @Override
    public void clear() {
        for (String key : keySet) {
            delete(key);
        }
    }

    @Override
    public void delete(String key) {
        try {
            deleteNativeValue(getNativeKey(key));
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("The action of deleting [" + cache.getName() + "] cache occurs exception ：", e);
            }
        }
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
