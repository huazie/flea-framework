package com.huazie.frame.cache.config;

import com.huazie.frame.common.util.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Flea缓存配置，对应【flea-cache-config.xml】中
 * 【{@code <flea-cache-config> </flea-cache-config>}】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCacheConfig {

    private List<CacheItems> cacheItemsList = new ArrayList<>(); // 缓存项集列表

    private CacheParams cacheParams; // 缓存参数集

    private CacheDatas cacheDatas; // 缓存数据集

    private CacheGroups cacheGroups; // 缓存组集

    private CacheServers cacheServers; // 缓存服务器集

    /**
     * <p> 获取缓存配置项集列表 </p>
     *
     * @return 缓存配置项集列表
     * @since 1.0.0
     */
    public List<CacheItems> getCacheItemsList() {
        return cacheItemsList;
    }

    /**
     * <p> 获取缓存配置项集列表 </p>
     *
     * @return 缓存配置项集列表
     * @since 1.0.0
     */
    public CacheItems[] toCacheItemsArray() {
        return cacheItemsList.toArray(new CacheItems[0]);
    }

    /**
     * <p> 添加一个缓存配置项集 </p>
     *
     * @param cacheItems 缓存配置项集
     * @since 1.0.0
     */
    public void addCacheItems(CacheItems cacheItems) {
        cacheItemsList.add(cacheItems);
    }

    /**
     * <p> 根据指定key，获取某个指定的缓存配置项集 </p>
     *
     * @param key 指定缓存配置项集的key
     * @return 某个指定的缓存配置项集
     * @since 1.0.0
     */
    public CacheItems getCacheItems(String key) {
        CacheItems cacheItems = null;
        Map<String, CacheItems> cacheItemsMap = toCacheItemsMap();
        if (MapUtils.isNotEmpty(cacheItemsMap)) {
            cacheItems = cacheItemsMap.get(key);
        }
        return cacheItems;
    }

    /**
     * <p> 获取缓存配置项集列表的Map，便于根据各缓存配置项集key查找 </p>
     *
     * @return 配缓存配置项集列表的Map
     * @since 1.0.0
     */
    private Map<String, CacheItems> toCacheItemsMap() {
        Map<String, CacheItems> cacheItemsMap = new HashMap<>();
        for (CacheItems cacheItems : cacheItemsList) {
            cacheItemsMap.put(cacheItems.getKey(), cacheItems);
        }
        return cacheItemsMap;
    }

    /**
     * <p> 获取缓存参数集 </p>
     *
     * @return 缓存参数集
     * @since 1.0.0
     */
    public CacheParams getCacheParams() {
        return cacheParams;
    }

    /**
     * <p> 设置缓存参数集 </p>
     *
     * @param cacheParams 缓存参数集
     * @since 1.0.0
     */
    public void setCacheParams(CacheParams cacheParams) {
        this.cacheParams = cacheParams;
    }

    /**
     * <p> 获取缓存数据集 </p>
     *
     * @return 缓存数据集
     * @since 1.0.0
     */
    public CacheDatas getCacheDatas() {
        return cacheDatas;
    }

    /**
     * <p> 设置缓存数据集 </p>
     *
     * @param cacheDatas 缓存数据集
     * @since 1.0.0
     */
    public void setCacheDatas(CacheDatas cacheDatas) {
        this.cacheDatas = cacheDatas;
    }

    /**
     * <p> 获取缓存组集 </p>
     *
     * @return 缓存组集
     * @since 1.0.0
     */
    public CacheGroups getCacheGroups() {
        return cacheGroups;
    }

    /**
     * <p> 设置缓存组集 </p>
     *
     * @param cacheGroups 缓存组集
     * @since 1.0.0
     */
    public void setCacheGroups(CacheGroups cacheGroups) {
        this.cacheGroups = cacheGroups;
    }

    /**
     * <p> 获取缓存服务器集 </p>
     *
     * @return 缓存服务器集
     * @since 1.0.0
     */
    public CacheServers getCacheServers() {
        return cacheServers;
    }

    /**
     * <p> 设置缓存服务器集 </p>
     *
     * @param cacheServers 缓存服务器集
     * @since 1.0.0
     */
    public void setCacheServers(CacheServers cacheServers) {
        this.cacheServers = cacheServers;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
