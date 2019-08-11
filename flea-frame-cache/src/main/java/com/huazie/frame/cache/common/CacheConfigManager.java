package com.huazie.frame.cache.common;

import com.huazie.frame.cache.config.Cache;
import com.huazie.frame.cache.config.CacheData;
import com.huazie.frame.cache.config.CacheDatas;
import com.huazie.frame.cache.config.CacheGroup;
import com.huazie.frame.cache.config.CacheGroups;
import com.huazie.frame.cache.config.CacheItem;
import com.huazie.frame.cache.config.CacheItems;
import com.huazie.frame.cache.config.CacheParam;
import com.huazie.frame.cache.config.CacheParams;
import com.huazie.frame.cache.config.CacheServer;
import com.huazie.frame.cache.config.CacheServers;
import com.huazie.frame.cache.config.Caches;
import com.huazie.frame.common.util.ObjectUtils;

import java.util.List;

/**
 * <p> 缓存配置管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheConfigManager {

    /**
     * <p> 根据指定的缓存键，获取Flea缓存定义 </p>
     *
     * @param key 缓存键
     * @return Flea缓存
     * @since 1.0.0
     */
    public static Cache getCache(String key) {
        Caches caches = CacheXmlDigesterHelper.getInstance().getFleaCache().getCaches();
        return caches.getFleaCache(key);
    }

    /**
     * <p> 根据指定的缓存配置项key，获取缓存配置项 </p>
     *
     * @param itemsKey 缓存配置项集Key
     * @param itemKey  缓存配置项Key
     * @return 缓存配置项
     * @since 1.0.0
     */
    public static CacheItem getCacheItem(String itemsKey, String itemKey) {
        CacheItem cacheItem = null;
        CacheItems cacheItems = CacheXmlDigesterHelper.getInstance().getFleaCacheConfig().getCacheItems(itemsKey);
        if (ObjectUtils.isNotEmpty(cacheItems)) {
            cacheItem = cacheItems.getCacheItem(itemKey);
        }
        return cacheItem;
    }

    /**
     * <p> 根据指定的缓存系统名cache, 获取缓存参数集 </p>
     *
     * @param cache 缓存系统名
     * @return 缓存参数集
     */
    public static CacheParams getCacheParams(String cache) {
        return CacheXmlDigesterHelper.getInstance().getFleaCacheConfig().getCacheParams(cache);
    }

    /**
     * <p> 根据指定的缓存系统名cache 和 缓存参数key，获取缓存参数 </p>
     *
     * @param cache 缓存系统名
     * @param key   缓存参数key
     * @return 缓存参数
     * @since 1.0.0
     */
    public static CacheParam getCacheParam(String cache, String key) {
        CacheParam cacheParam = null;
        CacheParams cacheParams = CacheXmlDigesterHelper.getInstance().getFleaCacheConfig().getCacheParams(cache);
        if (ObjectUtils.isNotEmpty(cacheParams)) {
            cacheParam = cacheParams.getCacheParam(key);
        }
        return cacheParam;
    }

    /**
     * <p> 根据指定的缓存数据类型type，获取缓存数据 </p>
     *
     * @param type 缓存数据类型
     * @return 缓存数据
     * @since 1.0.0
     */
    public static CacheData getCacheData(String type) {
        CacheData cacheData = null;
        CacheDatas cacheDatas = CacheXmlDigesterHelper.getInstance().getFleaCacheConfig().getCacheDatas();
        if (ObjectUtils.isNotEmpty(cacheDatas)) {
            cacheData = cacheDatas.getCacheData(type);
        }
        return cacheData;
    }

    /**
     * <p> 根据指定的缓存组名group，获取缓存组定义 </p>
     *
     * @param group 缓存组名
     * @return 缓存组
     * @since 1.0.0
     */
    public static CacheGroup getCacheGroup(String group) {
        CacheGroup cacheGroup = null;
        CacheGroups cacheGroups = CacheXmlDigesterHelper.getInstance().getFleaCacheConfig().getCacheGroups();
        if (ObjectUtils.isNotEmpty(cacheGroups)) {
            cacheGroup = cacheGroups.getCacheGroup(group);
        }
        return cacheGroup;
    }

    /**
     * <p> 根据指定的缓存组名group, 获取缓存服务器集 </p>
     *
     * @param group 缓存组名
     * @return 缓存服务器集
     * @since 1.0.0
     */
    public static List<CacheServer> getCacheServer(String group) {
        List<CacheServer> cacheServerList = null;
        CacheServers cacheServers = CacheXmlDigesterHelper.getInstance().getFleaCacheConfig().getCacheServers();
        if (ObjectUtils.isNotEmpty(cacheServers)) {
            cacheServerList = cacheServers.getCacheServersByGroup(group);
        }
        return cacheServerList;
    }
}
