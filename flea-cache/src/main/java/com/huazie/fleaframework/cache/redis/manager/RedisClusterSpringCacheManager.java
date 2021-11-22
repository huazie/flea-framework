package com.huazie.fleaframework.cache.redis.manager;

import com.huazie.fleaframework.cache.AbstractSpringCache;
import com.huazie.fleaframework.cache.AbstractSpringCacheManager;
import com.huazie.fleaframework.cache.common.CacheModeEnum;
import com.huazie.fleaframework.cache.redis.RedisClient;
import com.huazie.fleaframework.cache.redis.RedisClientFactory;
import com.huazie.fleaframework.cache.redis.RedisClusterPool;
import com.huazie.fleaframework.cache.redis.config.RedisClusterConfig;
import com.huazie.fleaframework.cache.redis.impl.RedisSpringCache;

/**
 * Redis集群模式下Spring缓存管理类，用于接入Spring框架管理Redis缓存。
 *
 * <p> 它的默认构造方法，用于初始化集群模式下默认连接池的Redis客户端,
 * 这里需要先初始化Redis连接池，默认连接池名为【default】；
 * 然后通过Redis客户端工厂类来获取Redis客户端。
 *
 * <p> 方法【{@code newCache}】用于创建一个Redis Spring缓存，
 * 而它内部是由Redis Flea缓存实现具体的 读、写、删除 和 清空
 * 缓存的基本操作。
 *
 * @author huazie
 * @version 1.1.0
 * @see RedisSpringCache
 * @since 1.1.0
 */
public class RedisClusterSpringCacheManager extends AbstractSpringCacheManager {

    private RedisClient redisClient; // Redis客户端

    /**
     * <p> 默认构造方法，初始化集群模式下默认连接池的Redis客户端 </p>
     *
     * @since 1.1.0
     */
    public RedisClusterSpringCacheManager() {
        // 初始化默认连接池
        RedisClusterPool.getInstance().initialize();
        // 获取集群模式下默认连接池的Redis客户端
        redisClient = RedisClientFactory.getInstance(CacheModeEnum.CLUSTER);
    }

    @Override
    protected AbstractSpringCache newCache(String name, int expiry) {
        int nullCacheExpiry = RedisClusterConfig.getConfig().getNullCacheExpiry();
        return new RedisSpringCache(name, expiry, nullCacheExpiry, CacheModeEnum.CLUSTER, redisClient);
    }

}
