package com.huazie.frame.cache;

import com.huazie.frame.cache.common.CacheEnum;
import com.huazie.frame.cache.memcached.MemCachedFleaCacheManager;
import com.huazie.frame.cache.redis.RedisFleaCacheManager;
import com.whalin.MemCached.MemCachedClient;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> Flea Cache Manager </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCacheManagerFactory {

    private static Map<String, AbstractFleaCacheManager> managerMap = new HashMap<String, AbstractFleaCacheManager>();

    /**
     * <p> 获取Flea Cache管理类对象实例 </p>
     *
     * @param name 缓存系统名称
     * @return Flea Cache管理类对象实例
     */
    public static AbstractFleaCacheManager getFleaCacheManager(String name) throws Exception {
        if (!managerMap.containsKey(name)) {
            synchronized (managerMap) {
                if (!managerMap.containsKey(name)) {
                    AbstractFleaCacheManager manager;
                    if (CacheEnum.MemCached.getName().equals(name)) {
                        manager = new MemCachedFleaCacheManager();
                    } else if (CacheEnum.Redis.getName().equals(name)) {
                        manager = new RedisFleaCacheManager();
                    } else {
                        throw new Exception("'" + name + "' is invalid, it must be 'memcached' or 'redis' ");
                    }
                    managerMap.put(name, manager);
                }
            }
        }
        return managerMap.get(name);
    }

}
