package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.AbstractSpringCacheManager;
import com.huazie.frame.cache.redis.config.RedisConfig;
import com.huazie.frame.cache.redis.impl.RedisClientProxy;
import com.huazie.frame.cache.redis.impl.RedisSpringCache;

/**
 * Redis Spring缓存管理类, 用于接入Spring框架管理Redis缓存。
 *
 * <p> 它的默认构造方法，用于初始化Redis客户端, 这里需要先
 * 初始化Redis连接池，默认连接池名为default；然后通过Redis
 * 客户端代理类获取被代理的Redis客户端。
 *
 * <p> 方法【{@code newCache}】用于创建一个Redis Spring缓存，
 * 而它内部是由Redis Flea缓存实现具体的读【{@code get}】、写
 * 【{@code put}】、删除【{@code delete}】和清空【{@code clear}】
 * 缓存的基本操作。
 *
 * @author huazie
 * @version 1.0.0
 * @see RedisSpringCache
 * @since 1.0.0
 */
public class RedisSpringCacheManager extends AbstractSpringCacheManager {

    private RedisClient redisClient; // Redis客户端

    /**
     * <p> 默认构造方法，初始化Redis客户端 </p>
     *
     * @since 1.0.0
     */
    public RedisSpringCacheManager() {
        // 初始化默认连接池
        RedisPool.getInstance().initialize();
        redisClient = RedisClientProxy.getProxyInstance();
    }

    @Override
    protected AbstractSpringCache newCache(String name, int expiry) {
        int nullCacheExpiry = RedisConfig.getConfig().getNullCacheExpiry();
        return new RedisSpringCache(name, expiry, nullCacheExpiry, redisClient);
    }

}
