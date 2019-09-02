package com.huazie.frame.cache;

import com.huazie.frame.common.CommonConstants;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p> 抽象Spring缓存管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractSpringCacheManager extends AbstractTransactionSupportingCacheManager {

    private static final ConcurrentMap<String, AbstractSpringCache> cacheMap = new ConcurrentHashMap<String, AbstractSpringCache>();

    private Map<String, Long> configMap = new HashMap<String, Long>();   // 各缓存的时间Map

    @Override
    protected Collection<? extends AbstractSpringCache> loadCaches() {
        return cacheMap.values();
    }

    @Override
    public AbstractSpringCache getCache(String name) {
        if(!cacheMap.containsKey(name)) {
            synchronized (cacheMap) {
                if (!cacheMap.containsKey(name)) {
                    Long expiry = configMap.get(name);
                    if (expiry == null) {
                        expiry = CommonConstants.NumeralConstants.ZERO; // 表示永久
                        configMap.put(name, expiry);
                    }
                    cacheMap.put(name, newCache(name, expiry));
                }
            }
        }
        return cacheMap.get(name);
    }

    /**
     * <p> 新创建一个缓存对象 </p>
     *
     * @param name   缓存名
     * @param expiry 失效时间（单位：秒  其中0：表示永久）
     * @return 新建的缓存对象
     * @since 1.0.0
     */
    protected abstract AbstractSpringCache newCache(String name, long expiry);

    /**
     * <p> 设置各缓存失效时间配置Map </p>
     *
     * @param configMap 失效时间配置Map
     * @since 1.0.0
     */
    public void setConfigMap(Map<String, Long> configMap) {
        this.configMap = configMap;
    }

}
