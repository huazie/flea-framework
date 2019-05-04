package com.huazie.frame.cache;

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

    protected ConcurrentMap<String, AbstractSpringCache> cacheMap = new ConcurrentHashMap<String, AbstractSpringCache>();

    protected Map<String, Integer> configMap = new HashMap<String, Integer>();   // 各缓存的时间Map

    @Override
    protected Collection<? extends AbstractSpringCache> loadCaches() {
        Collection<AbstractSpringCache> values = cacheMap.values();
        return values;
    }

    @Override
    public AbstractSpringCache getCache(String name) {
        AbstractSpringCache cache = cacheMap.get(name);
        if (cache == null) {
            Integer expire = configMap.get(name);
            if (expire == null) {
                expire = 0;//表示永久
                configMap.put(name, expire);
            }
            cache = newCache(name, expire);
            cacheMap.put(name, cache);
        }
        return cache;
    }

    protected abstract AbstractSpringCache newCache(String name, int expire);

    public void setConfigMap(Map<String, Integer> configMap) {
        this.configMap = configMap;
    }

}
