package com.huazie.fleaframework.cache.config;

import com.huazie.fleaframework.common.config.ConfigKeyMap;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存配置项集，对应【flea-cache-config.xml】中
 * 【{@code <cache-items key="" desc=""> </cache-items>}】
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class CacheItems extends ConfigKeyMap<CacheItem> {

    private List<CacheItem> cacheItemList = new ArrayList<>(); // 缓存配置项集中的各个缓存配置项

    public List<CacheItem> getCacheItemList() {
        return cacheItemList;
    }

    /**
     * 添加一个缓存配置项
     *
     * @param cacheItem 缓存配置项
     * @since 1.0.0
     */
    public void addCacheItem(CacheItem cacheItem) {
        cacheItemList.add(cacheItem);
    }

    @Override
    protected List<CacheItem> getConfigList() {
        return cacheItemList;
    }

    @Override
    protected String getConfigKey(CacheItem cacheItem) {
        return cacheItem.getKey();
    }

    /**
     * 根据Key获取指定的缓存配置项
     *
     * @param key 配置项键
     * @return 缓存配置项
     * @since 1.0.0
     */
    public CacheItem getCacheItem(String key) {
        return getConfig(key);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
