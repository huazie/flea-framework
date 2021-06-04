package com.huazie.frame.cache.common;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.IFleaCacheBuilder;
import com.huazie.frame.cache.config.Cache;
import com.huazie.frame.cache.config.CacheData;
import com.huazie.frame.cache.config.CacheGroup;
import com.huazie.frame.cache.config.CacheItem;
import com.huazie.frame.cache.config.CacheParams;
import com.huazie.frame.cache.config.CacheServer;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ReflectUtils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Flea Cache 工厂类，用于整合各类缓存接入时，创建具体的缓存实现类。
 *
 * <p> 同步集合类 {@code fleaCacheMap}，存储的键为缓存主关键字，
 * 对应缓存定义配置文件【flea-cache.xml】中的 {@code <cache
 * key="缓存主关键字"></cache>}；它的值为具体的缓存实现类。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCacheFactory {

    private static final ConcurrentMap<String, AbstractFleaCache> fleaCacheMap = new ConcurrentHashMap<>();

    private FleaCacheFactory() {}

    /**
     * <p> 根据缓存主关键字name获取指定Flea缓存对象 </p>
     *
     * @param name 缓存主关键字（对应 flea-cache.xml {@code <cache key="缓存主关键字"></cache>}）
     * @return Flea缓存对象
     * @since 1.0.0
     */
    public static AbstractFleaCache getFleaCache(String name) {
        if (!fleaCacheMap.containsKey(name)) {
            synchronized (fleaCacheMap) {
                if (!fleaCacheMap.containsKey(name)) {
                    fleaCacheMap.put(name, newFleaCache(name));
                }
            }
        }
        return fleaCacheMap.get(name);
    }

    /**
     * <p> 根据缓存主关键字name创建一个Flea缓存对象 </p>
     *
     * @param name 缓存主关键字（对应 flea-cache.xml {@code <cache key="缓存主关键字"></cache>}）
     * @return Flea缓存对象
     * @since 1.0.0
     */
    private static AbstractFleaCache newFleaCache(String name) {
        // 获取Flea缓存配置信息
        Cache cache = CacheConfigManager.getCache(name);
        if (ObjectUtils.isEmpty(cache)) {
            throw new RuntimeException("无法初始化Flea缓存，请检查flea-cache.xml配置【<cache key=" + name + " >】");
        }
        // 获取Flea缓存归属数据配置信息
        CacheData cacheData = CacheConfigManager.getCacheData(cache.getType());
        if (ObjectUtils.isEmpty(cacheData)) {
            throw new RuntimeException("无法初始化Flea缓存，请检查flea-cache-config.xml配置【<cache-data type=" + cache.getType() + " >】");
        }
        // 获取Flea缓存组
        CacheGroup cacheGroup = CacheConfigManager.getCacheGroup(cacheData.getGroup());
        if (ObjectUtils.isEmpty(cacheGroup)) {
            throw new RuntimeException("无法初始化Flea缓存，请检查flea-cache-config.xml配置【<cache-group group=" + cacheData.getGroup() + " >】");
        }
        // 获取缓存系统名
        String cacheSystem = cacheGroup.getCache();
        // 获取Flea缓存参数
        CacheParams cacheParams = CacheConfigManager.getCacheParams();
        if (ObjectUtils.isEmpty(cacheParams)) {
            throw new RuntimeException("无法初始化Flea缓存，请检查flea-cache-config.xml配置【<cache-params >】");
        }
        // 获取Flea缓存服务器
        List<CacheServer> cacheServerList = CacheConfigManager.getCacheServer(cacheGroup.getGroup());
        if (CollectionUtils.isEmpty(cacheServerList)) {
            throw new RuntimeException("无法初始化Flea缓存，请检查flea-cache-config.xml配置【<cache-server group=" + cacheGroup.getGroup() + " >】");
        }
        // 获取指定缓存系统名对应的Flea缓存建造者
        CacheItem cacheItem = CacheConfigManager.getCacheItem(CacheConstants.FleaCacheConfigConstants.FLEA_CACHE_BUILDER, cacheSystem);
        if (ObjectUtils.isEmpty(cacheItem)) {
            throw new RuntimeException("无法初始化Flea缓存，请检查flea-cache-config.xml配置【<cache-item key=" + cacheSystem + " >】");
        }
        // Flea缓存建造者
        String builder = cacheItem.getValue();
        if (ObjectUtils.isEmpty(builder)) {
            throw new RuntimeException("无法初始化Flea缓存，请检查flea-cache-config.xml配置【<cache-item key=" + cacheSystem + " ></cache-item>】配置项值不能为空");
        }
        AbstractFleaCache fleaCache;
        try {
            IFleaCacheBuilder fleaCacheBuilder = (IFleaCacheBuilder) ReflectUtils.newInstance(builder);
            fleaCache = fleaCacheBuilder.build(name, cacheServerList, cacheParams);
        } catch (Exception e) {
            throw new RuntimeException("构建Flea缓存出现异常：\n" + e);
        }
        return fleaCache;
    }

}
