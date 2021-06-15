package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.common.CacheModeEnum;
import com.huazie.frame.cache.redis.config.RedisClusterConfig;
import com.huazie.frame.cache.redis.impl.RedisFleaCache;

/**
 * Redis集群模式Flea缓存管理类，用于接入Flea框架管理Redis缓存。
 *
 * <p> 它的默认构造方法，用于初始化集群模式下默认连接池的Redis客户端,
 * 这里需要先初始化Redis连接池，默认连接池名为【default】；
 * 然后通过Redis客户端工厂类来获取Redis客户端。
 *
 * <p> 方法 {@code newCache} 用于创建一个Redis Flea缓存，
 * 它里面包含了 读、写、删除 和 清空 缓存的基本操作。
 *
 * @author huazie
 * @version 1.1.0
 * @see RedisFleaCache
 * @since 1.1.0
 */
public class RedisClusterFleaCacheManager extends AbstractFleaCacheManager {

    private RedisClient redisClient; // Redis客户端

    /**
     * <p> 默认构造方法，初始化集群模式下默认连接池的Redis客户端 </p>
     *
     * @since 1.1.0
     */
    public RedisClusterFleaCacheManager() {
        // 初始化默认连接池
        RedisClusterPool.getInstance().initialize();
        // 获取集群模式下默认连接池的Redis客户端
        redisClient = RedisClientFactory.getInstance(CacheModeEnum.CLUSTER);
    }

    @Override
    protected AbstractFleaCache newCache(String name, int expiry) {
        int nullCacheExpiry = RedisClusterConfig.getConfig().getNullCacheExpiry();
        return new RedisFleaCache(name, expiry, nullCacheExpiry, CacheModeEnum.CLUSTER, redisClient);
    }
}
