package com.huazie.fleaframework.cache.config;

import com.huazie.fleaframework.common.config.ConfigKey;
import com.huazie.fleaframework.common.util.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存配置项集，对应【flea-cache-config.xml】中
 * 【{@code <cache-items key="" desc=""> </cache-items>}】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheItems extends ConfigKey {

    private List<CacheItem> cacheItemList = new ArrayList<>(); // 缓存配置项集中的各个缓存配置项

    public List<CacheItem> getCacheItemList() {
        return cacheItemList;
    }

    /**
     * <p> 添加一个缓存配置项 </p>
     *
     * @param cacheItem 缓存配置项
     * @since 1.0.0
     */
    public void addCacheItem(CacheItem cacheItem) {
        cacheItemList.add(cacheItem);
    }

    /**
     * <p> 根据Key获取指定的缓存配置项 </p>
     *
     * @param key 配置项键
     * @return 缓存配置项
     * @since 1.0.0
     */
    public CacheItem getCacheItem(String key) {
        CacheItem cacheItem = null;
        Map<String, CacheItem> cacheItemMap = toCacheItemMap();
        if (MapUtils.isNotEmpty(cacheItemMap)) {
            cacheItem = cacheItemMap.get(key);
        }
        return cacheItem;
    }

    /**
     * <p> 获取指定配置项集中的配置项的Map，便于根据各配置项key查找 </p>
     *
     * @return 配置项的Map
     * @since 1.0.0
     */
    private Map<String, CacheItem> toCacheItemMap() {
        Map<String, CacheItem> cacheItemMap = new HashMap<>();
        for (CacheItem cacheItem : cacheItemList) {
            cacheItemMap.put(cacheItem.getKey(), cacheItem);
        }
        return cacheItemMap;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
