package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.redis.config.RedisConfig;
import com.huazie.frame.cache.redis.impl.RedisClientProxy;
import com.huazie.frame.cache.redis.impl.RedisFleaCache;

/**
 * Redis Flea缓存管理类，用于接入Flea框架管理Redis缓存。
 *
 * <p> 它的默认构造方法，用于初始化Redis客户端, 这里需要先
 * 初始化Redis连接池，默认连接池名为default；然后通过Redis
 * 客户端代理类获取被代理的Redis客户端。
 *
 * <p> 方法 {@code newCache} 用于创建一个Redis Flea缓存，
 * 它里面包含了读【{@code get}】、写【{@code put}】、删除
 * 【{@code delete}】和清空【{@code clear}】缓存的基本操作。
 *
 * @author huazie
 * @version 1.0.0
 * @see RedisFleaCache
 * @since 1.0.0
 */
public class RedisFleaCacheManager extends AbstractFleaCacheManager {

    private RedisClient redisClient; // Redis客户端

    /**
     * <p> 默认构造方法，初始化Redis Flea缓存管理类 </p>
     *
     * @since 1.0.0
     */
    public RedisFleaCacheManager() {
        // 初始化默认连接池
        RedisPool.getInstance().initialize();
        redisClient = RedisClientProxy.getProxyInstance();
    }

    @Override
    protected AbstractFleaCache newCache(String name, int expiry) {
        int nullCacheExpiry = RedisConfig.getConfig().getNullCacheExpiry();
        return new RedisFleaCache(name, expiry, nullCacheExpiry, redisClient);
    }

}
