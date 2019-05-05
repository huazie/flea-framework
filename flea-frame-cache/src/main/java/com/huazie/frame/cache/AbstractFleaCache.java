package com.huazie.frame.cache;

import com.huazie.frame.cache.common.CacheEnum;
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
    private final int expire;   // 失效时间（毫秒单位）

    protected CacheEnum cache;  // 缓存类型

    public AbstractFleaCache(String name, int expire) {
        this.name = name;
        this.expire = expire;
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
            putNativeValue(getNativeKey(key), value, expire);
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

    protected abstract Object getNativeValue(String key);

    protected abstract void putNativeValue(String key, Object value, int expire);

    protected abstract void deleteNativeValue(String key);

    protected String getNativeKey(String key) {
        return name + "_" + key;  // 可以自己定义
    }

    public String getName() {
        return name;
    }

    public int getExpire() {
        return expire;
    }

    public String getCacheName() {
        return cache.getName();
    }

    public String getCacheDesc() {
        return cache.getDesc();
    }

}
