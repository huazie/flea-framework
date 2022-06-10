package com.huazie.fleaframework.cache.common;

import com.huazie.fleaframework.cache.config.Cache;
import com.huazie.fleaframework.cache.config.CacheData;
import com.huazie.fleaframework.cache.config.CacheDatas;
import com.huazie.fleaframework.cache.config.CacheFiles;
import com.huazie.fleaframework.cache.config.CacheGroup;
import com.huazie.fleaframework.cache.config.CacheGroups;
import com.huazie.fleaframework.cache.config.CacheItem;
import com.huazie.fleaframework.cache.config.CacheItems;
import com.huazie.fleaframework.cache.config.CacheParam;
import com.huazie.fleaframework.cache.config.CacheParams;
import com.huazie.fleaframework.cache.config.CacheServer;
import com.huazie.fleaframework.cache.config.CacheServers;
import com.huazie.fleaframework.cache.config.Caches;
import com.huazie.fleaframework.cache.config.FleaCache;
import com.huazie.fleaframework.cache.exceptions.FleaCacheConfigException;
import com.huazie.fleaframework.cache.exceptions.FleaCacheException;
import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * 缓存配置工具类，用于获取缓存定义文件【flea-cache.xml】
 * 和缓存配置文件【flea-cache-config.xml】的相关配置数据。
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.0.0
 */
public class CacheConfigUtils {

    private CacheConfigUtils() {
    }

    /**
     * 根据指定的缓存数据主关键字，获取Flea缓存定义；
     * 相同的缓存Key，先取缓存定义文件靠前的配置。
     *
     * @param key 缓存数据主关键字
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
     * 根据指定的缓存数据主关键字，获取缓存数据有效期。
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
     * 根据指定的缓存配置项键，获取缓存配置项
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
     * 获取缓存所属系统名称, 对应【flea-cache.xml】中
     * 【{@code <cache-item key="systemName" desc="缓存所属系统名">FleaFrame</cache-item>}】
     *
     * @return 缓存所属系统名称
     * @since 1.1.0
     */
    public static String getSystemName() {
        // 获取缓存初始化配置项集之缓存所属系统名配置项
        CacheItem cacheItem = CacheConfigUtils.getCacheItem(CacheConstants.FleaCacheConfigConstants.FLEA_CACHE_INIT, CacheConstants.FleaCacheConfigConstants.SYSTEM_NAME);
        if (ObjectUtils.isEmpty(cacheItem)) {
            ExceptionUtils.throwFleaException(FleaCacheConfigException.class, "无法获取缓存系统名，请检查flea-cache-config.xml配置【<cache-item key=" + CacheConstants.FleaCacheConfigConstants.SYSTEM_NAME + " >】\"");
        }
        return cacheItem.getValue();
    }

    /**
     * 获取Redis集群客户端当前连接的名称
     *
     * @param systemName 缓存所属系统名
     * @return Redis集群客户端当前连接的名称
     * @since 1.1.0
     */
    public static String getClientName(String systemName) {
        String appCode = System.getProperty(CommonConstants.SystemConstants.APP_CODE);
        StringBuilder clientName = new StringBuilder(systemName);
        if (StringUtils.isNotBlank(appCode)) {
            clientName.append(CommonConstants.SymbolConstants.HYPHEN).append(appCode);
        }
        return clientName.toString();
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
     * 根据指定的缓存参数键，获取缓存参数。
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
     * 获取Redis客户端操作最大尝试次数【包含第一次操作】
     *
     * @return Redis客户端操作最大尝试次数【包含第一次操作】
     * @since 1.1.0
     */
    public static int getMaxAttempts() {
        // 默认5次
        int maxAttempts = CacheConstants.RedisConfigConstants.REDIS_MAXATTEMPTS_DEFAULT;
        CacheParam cacheParam = getCacheParam(CacheConstants.RedisConfigConstants.REDIS_MAXATTEMPTS);
        if (ObjectUtils.isNotEmpty(cacheParam)) {
            maxAttempts = Integer.parseInt(cacheParam.getValue());
        }
        return maxAttempts;
    }

    /**
     * 获取空缓存数据有效期
     *
     * @return 空缓存数据有效期
     * @since 1.1.0
     */
    public static int getNullCacheExpiry() {
        // 默认300s
        int expiry = CacheConstants.FleaCacheConfigConstants.DEFAULT_EXPIRY;
        CacheParam cacheParam = getCacheParam(CacheConstants.FleaCacheConfigConstants.NULL_CACHE_EXPIRY);
        if (ObjectUtils.isNotEmpty(cacheParam)) {
            expiry = Integer.parseInt(cacheParam.getValue());
        }
        return expiry;
    }

    /**
     * 获取Jedis连接池配置，对应【flea-cache.xml】中
     * <pre>
     * {@code <cache-param key="redis.pool.maxTotal" desc="Redis客户端Jedis连接池最大连接数">100</cache-param>}
     * {@code <cache-param key="redis.pool.maxIdle" desc="Redis客户端Jedis连接池最大空闲连接数">10</cache-param>}
     * {@code <cache-param key="redis.pool.minIdle" desc="Redis客户端Jedis连接池最小空闲连接数">0</cache-param>}
     * {@code <cache-param key="redis.pool.maxWaitMillis" desc="Redis客户端Jedis连接池获取连接时的最大等待时间（单位：ms）">2000</cache-param>} </pre>
     *
     * @return Jedis连接池配置
     * @since 1.1.0
     */
    public static JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // Redis客户端Jedis连接池最大连接数
        CacheParam poolMaxTotal = CacheConfigUtils.getCacheParam(CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MAXTOTAL);
        if (ObjectUtils.isEmpty(poolMaxTotal) || StringUtils.isBlank(poolMaxTotal.getValue())) {
            ExceptionUtils.throwFleaException(FleaCacheException.class, "请检查flea-cache-config.xml配置，【<cache-param key=" + CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MAXTOTAL + " ></cache-param>】未配置或配置值为空");
        }
        poolConfig.setMaxTotal(Integer.parseInt(poolMaxTotal.getValue()));
        // Redis客户端Jedis连接池最大空闲连接数
        CacheParam poolMaxIdle = CacheConfigUtils.getCacheParam(CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MAXIDLE);
        if (ObjectUtils.isEmpty(poolMaxIdle) || StringUtils.isBlank(poolMaxIdle.getValue())) {
            ExceptionUtils.throwFleaException(FleaCacheException.class, "请检查flea-cache-config.xml配置，【<cache-param key=" + CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MAXIDLE + " ></cache-param>】未配置或配置值为空");
        }
        poolConfig.setMaxIdle(Integer.parseInt(poolMaxIdle.getValue()));
        // Redis客户端Jedis连接池最小空闲连接数
        CacheParam poolMinIdle = CacheConfigUtils.getCacheParam(CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MINIDLE);
        if (ObjectUtils.isEmpty(poolMinIdle) || StringUtils.isBlank(poolMinIdle.getValue())) {
            ExceptionUtils.throwFleaException(FleaCacheException.class, "请检查flea-cache-config.xml配置，【<cache-param key=" + CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MINIDLE + " ></cache-param>】未配置或配置值为空");
        }
        poolConfig.setMinIdle(Integer.parseInt(poolMinIdle.getValue()));
        // Redis客户端Jedis连接池获取连接时的最大等待毫秒数
        CacheParam poolMaxWaitMillis = CacheConfigUtils.getCacheParam(CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MAXWAITMILLIS);
        if (ObjectUtils.isEmpty(poolMaxWaitMillis) || StringUtils.isBlank(poolMaxWaitMillis.getValue())) {
            ExceptionUtils.throwFleaException(FleaCacheException.class, "请检查flea-cache-config.xml配置，【<cache-param key=" + CacheConstants.RedisConfigConstants.REDIS_CONFIG_POOL_MAXWAITMILLIS + " ></cache-param>】未配置或配置值为空");
        }
        poolConfig.setMaxWaitMillis(Integer.parseInt(poolMaxWaitMillis.getValue()));
        return poolConfig;
    }

    /**
     * 根据指定的缓存数据类型，获取缓存数据。
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
     * 根据指定的缓存组名，获取缓存组定义。
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
     * 根据指定的缓存组名, 获取缓存服务器集。
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
