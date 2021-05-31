package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.redis.config.RedisConfig;
import com.huazie.frame.cache.redis.impl.RedisClientProxy;
import com.huazie.frame.cache.redis.impl.RedisFleaCache;

/**
 * <p> Redis Flea缓存管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisFleaCacheManager extends AbstractFleaCacheManager {

    private RedisClient redisClient;

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
