package com.huazie.fleaframework.cache.redis.manager;

import com.huazie.fleaframework.cache.AbstractSpringCache;
import com.huazie.fleaframework.cache.AbstractSpringCacheManager;
import com.huazie.fleaframework.cache.common.CacheModeEnum;
import com.huazie.fleaframework.cache.redis.RedisClient;
import com.huazie.fleaframework.cache.redis.RedisClientFactory;
import com.huazie.fleaframework.cache.redis.RedisShardedPool;
import com.huazie.fleaframework.cache.redis.config.RedisShardedConfig;
import com.huazie.fleaframework.cache.redis.impl.RedisSpringCache;

/**
 * Redis分片模式Spring缓存管理类，用于接入Spring框架管理Redis缓存。
 *
 * <p> 它的默认构造方法，用于初始化分片模式下默认连接池的Redis客户端,
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
public class RedisShardedSpringCacheManager extends AbstractSpringCacheManager {

    private RedisClient redisClient; // Redis客户端

    /**
     * 默认构造方法，初始化分片模式下默认连接池的Redis客户端
     *
     * @since 1.0.0
     */
    public RedisShardedSpringCacheManager() {
        // 初始化默认连接池
        RedisShardedPool.getInstance().initialize();
        // 获取分片模式下默认连接池的Redis客户端
        redisClient = RedisClientFactory.getInstance();
    }

    @Override
    protected AbstractSpringCache newCache(String name, int expiry) {
        int nullCacheExpiry = RedisShardedConfig.getConfig().getNullCacheExpiry();
        return new RedisSpringCache(name, expiry, nullCacheExpiry, CacheModeEnum.SHARDED, redisClient);
    }

}
