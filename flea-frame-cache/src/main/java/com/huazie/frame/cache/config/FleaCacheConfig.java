package com.huazie.frame.cache.config;

import com.huazie.frame.common.util.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p> Flea Cache配置 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCacheConfig {

    private List<CacheItems> cacheItemsList = new ArrayList<CacheItems>(); // 缓存项集列表

    private List<CacheParams> cacheParamsList = new ArrayList<CacheParams>(); // 缓存参数集列表

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
     * <p> 获取缓存配置项集列表的Map，便于根据各缓存配置项集key查找 </p>
     *
     * @return 配缓存配置项集列表的Map
     * @since 1.0.0
     */
    public Map<String, CacheItems> toCacheItemsMap() {
        Map<String, CacheItems> cacheItemsMap = new HashMap<String, CacheItems>();
        Iterator<CacheItems> cacheItemsIt = cacheItemsList.iterator();
        while (cacheItemsIt.hasNext()) {
            CacheItems cacheItems = cacheItemsIt.next();
            cacheItemsMap.put(cacheItems.getKey(), cacheItems);
        }
        return cacheItemsMap;
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
     * <p> 获取缓存参数集列表 </p>
     *
     * @return 缓存参数集列表
     * @since 1.0.0
     */
    public List<CacheParams> getCacheParamsList() {
        return cacheParamsList;
    }

    /**
     * <p> 获取缓存参数集列表 </p>
     *
     * @return 缓存参数集列表
     * @since 1.0.0
     */
    public CacheParams[] toCacheParamsArray() {
        return cacheParamsList.toArray(new CacheParams[0]);
    }

    /**
     * <p> 添加一个缓存参数集 </p>
     *
     * @param cacheParams 缓存参数集
     * @since 1.0.0
     */
    public void addCacheParams(CacheParams cacheParams) {
        cacheParamsList.add(cacheParams);
    }

    /**
     * <p> 获取缓存参数集列表的Map，便于根据各缓存参数集key查找 </p>
     *
     * @return 缓存参数集列表的Map
     * @since 1.0.0
     */
    public Map<String, CacheParams> toCacheParamsMap() {
        Map<String, CacheParams> cacheParamsMap = new HashMap<String, CacheParams>();
        Iterator<CacheParams> cacheParamsIt = cacheParamsList.iterator();
        while (cacheParamsIt.hasNext()) {
            CacheParams cacheParams = cacheParamsIt.next();
            cacheParamsMap.put(cacheParams.getKey(), cacheParams);
        }
        return cacheParamsMap;
    }

    /**
     * <p> 根据指定key，获取某个指定的缓存参数集 </p>
     *
     * @param key 指定缓存参数集的key
     * @return 某个指定的缓存参数集
     * @since 1.0.0
     */
    public CacheParams getCacheParams(String key) {
        CacheParams cacheParams = null;
        Map<String, CacheParams> cacheParamsMap = toCacheParamsMap();
        if (MapUtils.isNotEmpty(cacheParamsMap)) {
            cacheParams = cacheParamsMap.get(key);
        }
        return cacheParams;
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
