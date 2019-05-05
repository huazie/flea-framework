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

    private static ConcurrentMap<String, AbstractSpringCache> cacheMap = new ConcurrentHashMap<String, AbstractSpringCache>();

    protected Map<String, Integer> configMap = new HashMap<String, Integer>();   // 各缓存的时间Map

    @Override
    protected Collection<? extends AbstractSpringCache> loadCaches() {
        Collection<AbstractSpringCache> values = cacheMap.values();
        return values;
    }

    @Override
    public AbstractSpringCache getCache(String name) {
        synchronized (cacheMap) {
            if (!cacheMap.containsKey(name)) {
                Integer expire = configMap.get(name);
                if (expire == null) {
                    expire = CommonConstants.NumeralConstants.ZERO; // 表示永久
                    configMap.put(name, expire);
                }
                cacheMap.put(name, newCache(name, expire));
            }
        }
        return cacheMap.get(name);
    }

    /**
     * <p> 新创建一个缓存对象 </p>
     *
     * @param name   缓存名
     * @param expire 失效时间（0：表示永久）
     * @return 新建的缓存对象
     * @since 1.0.0
     */
    protected abstract AbstractSpringCache newCache(String name, int expire);

    public void setConfigMap(Map<String, Integer> configMap) {
        this.configMap = configMap;
    }

}
