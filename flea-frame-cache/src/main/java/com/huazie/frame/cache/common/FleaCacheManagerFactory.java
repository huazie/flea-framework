package com.huazie.frame.cache.common;

import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.core.CoreFleaCacheManager;
import com.huazie.frame.cache.memcached.MemCachedFleaCacheManager;
import com.huazie.frame.cache.redis.RedisClusterFleaCacheManager;
import com.huazie.frame.cache.redis.RedisShardedFleaCacheManager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Flea缓存管理者工厂类，不同缓存实现对应不同的Flea缓存管理者。
 *
 * <p> 同步集合类【{@code cacheMap}】，存储的键为缓存实现名，
 * 目前仅包含 MemCached、RedisSharded、RedisCluster 和 FleaCore；
 * 存储的值为Flea缓存管理者，目前包含MemCached缓存管理者、
 * Redis分片缓存管理者、Redis集群缓存管理者和Flea核心缓存管理者。
 *
 * @author huazie
 * @version 1.1.0
 * @see MemCachedFleaCacheManager
 * @see RedisShardedFleaCacheManager
 * @see RedisClusterFleaCacheManager
 * @see CoreFleaCacheManager
 * @since 1.0.0
 */
public class FleaCacheManagerFactory {

    private static final ConcurrentMap<String, AbstractFleaCacheManager> managerMap = new ConcurrentHashMap<>();

    private FleaCacheManagerFactory() {
    }

    /**
     * <p> 获取Flea Cache管理类对象实例 </p>
     *
     * @param name 缓存系统名称
     * @return Flea Cache管理类对象实例
     * @since 1.0.0
     */
    public static AbstractFleaCacheManager getFleaCacheManager(String name) throws Exception {
        if (!managerMap.containsKey(name)) {
            synchronized (managerMap) {
                if (!managerMap.containsKey(name)) {
                    AbstractFleaCacheManager manager;
                    if (CacheEnum.MemCached.getName().equals(name)) {
                        manager = new MemCachedFleaCacheManager();
                    } else if (CacheEnum.RedisSharded.getName().equals(name)) {
                        manager = new RedisShardedFleaCacheManager();
                    } else if (CacheEnum.RedisCluster.getName().equals(name)) {
                        manager = new RedisClusterFleaCacheManager();
                    } else if (CacheEnum.FleaCore.getName().equals(name)) {
                        manager = new CoreFleaCacheManager();
                    } else {
                        throw new Exception("'" + name + "' is invalid, it must be 'MemCached' or 'RedisSharded' or 'RedisCluster' or 'FleaCore' ");
                    }
                    managerMap.put(name, manager);
                }
            }
        }
        return managerMap.get(name);
    }

}
