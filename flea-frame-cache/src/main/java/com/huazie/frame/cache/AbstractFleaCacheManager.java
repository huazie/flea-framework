package com.huazie.frame.cache;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.ObjectUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p> 自定义抽象缓存管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractFleaCacheManager {

    private static ConcurrentMap<String, AbstractFleaCache> cacheMap = new ConcurrentHashMap<String, AbstractFleaCache>();

    protected Map<String, Integer> configMap = new HashMap<String, Integer>();   // 各缓存的时间Map

    protected Collection<? extends AbstractFleaCache> loadCaches() {
        Collection<AbstractFleaCache> values = cacheMap.values();
        return values;
    }

    /**
     * <p> 根据指定缓存名获取缓存对象 </p>
     *
     * @param name 缓存名
     * @return 缓存对象
     * @since 1.0.0
     */
    public AbstractFleaCache getCache(String name) {
        synchronized (cacheMap) {
            if (!cacheMap.containsKey(name)) {
                Integer expire = configMap.get(name);
                if (ObjectUtils.isEmpty(expire)) {
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
    protected abstract AbstractFleaCache newCache(String name, int expire);

    public void setConfigMap(Map<String, Integer> configMap) {
        this.configMap = configMap;
    }

}
