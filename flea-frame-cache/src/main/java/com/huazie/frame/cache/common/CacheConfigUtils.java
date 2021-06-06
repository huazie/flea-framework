package com.huazie.frame.cache.common;

import com.huazie.frame.cache.config.Cache;
import com.huazie.frame.cache.config.CacheData;
import com.huazie.frame.cache.config.CacheDatas;
import com.huazie.frame.cache.config.CacheFiles;
import com.huazie.frame.cache.config.CacheGroup;
import com.huazie.frame.cache.config.CacheGroups;
import com.huazie.frame.cache.config.CacheItem;
import com.huazie.frame.cache.config.CacheItems;
import com.huazie.frame.cache.config.CacheParam;
import com.huazie.frame.cache.config.CacheParams;
import com.huazie.frame.cache.config.CacheServer;
import com.huazie.frame.cache.config.CacheServers;
import com.huazie.frame.cache.config.Caches;
import com.huazie.frame.cache.config.FleaCache;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;

import java.util.List;

import static com.huazie.frame.cache.common.CacheConstants.FleaCacheConfigConstants.DEFAULT_EXPIRY;

/**
 * 缓存配置工具类，用于获取缓存定义文件【flea-cache.xml】
 * 和缓存配置文件【flea-cache-config.xml】的相关配置数据。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheConfigUtils {

    /**
     * 根据指定的缓存主关键字，获取Flea缓存定义；
     * 相同的缓存Key，先取缓存定义文件靠前的配置。
     *
     * @param key 缓存主关键字
     * @return Flea缓存
     * @since 1.0.0
     */
    public static Cache getCache(String key) {
        Cache cache = null;
        FleaCache fleaCache = CacheXmlDigesterHelper.getInstance().getFleaCache();
        if (ObjectUtils.isNotEmpty(fleaCache)) {
            Caches caches = fleaCache.getCaches();
            if (ObjectUtils.isNotEmpty(caches)) {
                // 从主缓存文件中获取
                cache = caches.getFleaCache(key);
                if (ObjectUtils.isEmpty(cache)) {
                    // 从其他引入的缓存文件中获取
                    CacheFiles cacheFiles = fleaCache.getCacheFiles();
                    if (ObjectUtils.isNotEmpty(cacheFiles)) {
                        cache = cacheFiles.getFleaCache(key);
                    }
                }
            }
        }
        return cache;
    }

    /**
     * 根据指定的缓存主关键字，获取缓存数据有效期。
     *
     * @param key 缓存数据主关键字
     * @return 缓存数据有效期
     * @since 1.0.0
     */
    public static int getExpiry(String key) {
        int expiry = CommonConstants.NumeralConstants.INT_ZERO;
        Cache cache = getCache(key);
        if (ObjectUtils.isNotEmpty(cache) && StringUtils.isNotBlank(cache.getExpiry())) {
            expiry = Integer.parseInt(cache.getExpiry());
        }
        return expiry;
    }

    /**
     * 获取空缓存数据有效期
     *
     * @return 空缓存数据有效期
     * @since 1.0.0
     */
    public static int getNullCacheExpiry() {
        // 默认300s
        int expiry = DEFAULT_EXPIRY;
        CacheParam cacheParam = getCacheParam(CacheConstants.FleaCacheConfigConstants.NULL_CACHE_EXPIRY);
        if (ObjectUtils.isNotEmpty(cacheParam)) {
            expiry = Integer.parseInt(cacheParam.getValue());
        }
        return expiry;
    }

    /**
     * 根据指定的缓存配置项key，获取缓存配置项
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
     * 获取缓存参数集
     *
     * @return 缓存参数集
     * @since 1.0.0
     */
    public static CacheParams getCacheParams() {
        return CacheXmlDigesterHelper.getInstance().getFleaCacheConfig().getCacheParams();
    }

    /**
     * 根据指定的缓存参数【{@code key}】，获取缓存参数。
     *
     * @param key 缓存参数键
     * @return 缓存参数
     * @since 1.0.0
     */
    public static CacheParam getCacheParam(String key) {
        CacheParam cacheParam = null;
        CacheParams cacheParams = getCacheParams();
        if (ObjectUtils.isNotEmpty(cacheParams)) {
            cacheParam = cacheParams.getCacheParam(key);
        }
        return cacheParam;
    }

    /**
     * 根据指定的缓存数据类型【{@code type}】，获取缓存数据。
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
     * 根据指定的缓存组名【{@code group}】，获取缓存组定义。
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
     * 根据指定的缓存组名【{@code group}】, 获取缓存服务器集。
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
