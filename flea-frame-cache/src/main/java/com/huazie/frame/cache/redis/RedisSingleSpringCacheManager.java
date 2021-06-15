package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.AbstractSpringCacheManager;
import com.huazie.frame.cache.common.CacheModeEnum;
import com.huazie.frame.cache.redis.config.RedisSingleConfig;
import com.huazie.frame.cache.redis.impl.RedisSpringCache;

/**
 * Redis单机模式Spring缓存管理类，用于接入Spring框架管理Redis缓存。
 *
 * <p> 它的默认构造方法，用于初始化单机模式下默认连接池的Redis客户端,
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
 * @since 1.0.0
 */
public class RedisSingleSpringCacheManager extends AbstractSpringCacheManager {

    private RedisClient redisClient; // Redis客户端

    /**
     * <p> 默认构造方法，初始化单机模式下默认连接池的Redis客户端 </p>
     *
     * @since 1.0.0
     */
    public RedisSingleSpringCacheManager() {
        // 初始化默认连接池
        RedisSinglePool.getInstance().initialize();
        // 获取单机模式下默认连接池的Redis客户端
        redisClient = RedisClientFactory.getInstance();
    }

    @Override
    protected AbstractSpringCache newCache(String name, int expiry) {
        int nullCacheExpiry = RedisSingleConfig.getConfig().getNullCacheExpiry();
        return new RedisSpringCache(name, expiry, nullCacheExpiry, CacheModeEnum.SINGLE, redisClient);
    }

}
