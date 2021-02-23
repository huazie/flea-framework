package com.huazie.frame.cache.common;

import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.core.CoreFleaCacheManager;
import com.huazie.frame.cache.memcached.MemCachedFleaCacheManager;
import com.huazie.frame.cache.redis.RedisFleaCacheManager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p> Flea Cache Manager工厂类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCacheManagerFactory {

    private static final ConcurrentMap<String, AbstractFleaCacheManager> managerMap = new ConcurrentHashMap<>();

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
                    } else if (CacheEnum.FleaCore.getName().equals(name)) {
                        manager = new CoreFleaCacheManager();
                    } else {
                        throw new Exception("'" + name + "' is invalid, it must be 'MemCached' or 'Redis' or 'FleaCore' ");
                    }
                    managerMap.put(name, manager);
                }
            }
        }
        return managerMap.get(name);
    }

}
