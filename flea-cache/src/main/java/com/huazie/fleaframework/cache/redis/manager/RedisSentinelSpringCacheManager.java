package com.huazie.fleaframework.cache.redis.manager;

import com.huazie.fleaframework.cache.AbstractSpringCache;
import com.huazie.fleaframework.cache.AbstractSpringCacheManager;
import com.huazie.fleaframework.cache.common.CacheModeEnum;
import com.huazie.fleaframework.cache.common.EmptyFleaCache;
import com.huazie.fleaframework.cache.redis.RedisClient;
import com.huazie.fleaframework.cache.redis.RedisClientFactory;
import com.huazie.fleaframework.cache.redis.RedisSentinelPool;
import com.huazie.fleaframework.cache.redis.config.RedisSentinelConfig;
import com.huazie.fleaframework.cache.redis.impl.RedisSpringCache;

/**
 * Redis哨兵模式Spring缓存管理类，用于接入Spring框架管理Redis缓存。
 *
 * <p> 它的默认构造方法，用于初始化哨兵模式下默认连接池的Redis客户端,
 * 这里需要先初始化Redis连接池，默认连接池名为【default】；
 * 然后通过Redis客户端工厂类来获取Redis客户端。
 *
 * <p> 方法【{@code newCache}】用于创建一个Redis Spring缓存，
 * 而它内部是由Redis Flea缓存实现具体的 读、写、删除 和 清空
 * 缓存的基本操作。
 *
 * @author huazie
 * @version 2.0.0
 * @see RedisSpringCache
 * @since 2.0.0
 */
public class RedisSentinelSpringCacheManager extends AbstractSpringCacheManager {

    private RedisClient redisClient; // Redis客户端

    /**
     * 默认构造方法，初始化哨兵模式下默认连接池的Redis客户端
     *
     * @since 2.0.0
     */
    public RedisSentinelSpringCacheManager() {
        this(0);
    }

    /**
     * 初始化哨兵模式下默认连接池的Redis客户端，指定Redis数据库索引
     *
     * @since 2.0.0
     */
    public RedisSentinelSpringCacheManager(int database) {
        if (!RedisSentinelConfig.getConfig().isSwitchOpen()) return;
        // 初始化默认连接池
        RedisSentinelPool.getInstance().initialize(database);
        // 获取哨兵模式下默认连接池的Redis客户端
        redisClient = RedisClientFactory.getInstance(CacheModeEnum.SENTINEL);
    }

    @Override
    protected AbstractSpringCache newCache(String name, int expiry) {
        int nullCacheExpiry = RedisSentinelConfig.getConfig().getNullCacheExpiry();
        if (RedisSentinelConfig.getConfig().isSwitchOpen())
            return new RedisSpringCache(name, expiry, nullCacheExpiry, CacheModeEnum.SENTINEL, redisClient);
        else
            return new RedisSpringCache(name, new EmptyFleaCache(name, expiry, nullCacheExpiry));
    }

}
