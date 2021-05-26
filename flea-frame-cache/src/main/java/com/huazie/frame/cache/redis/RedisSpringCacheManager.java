package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.AbstractSpringCacheManager;
import com.huazie.frame.cache.redis.impl.RedisClientProxy;
import com.huazie.frame.cache.redis.impl.RedisSpringCache;

/**
 * <p> Redis Spring缓存管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisSpringCacheManager extends AbstractSpringCacheManager {

    private RedisClient redisClient;

    /**
     * <p> 默认构造方法，初始化Redis Spring缓存管理类 </p>
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
        return new RedisSpringCache(name, expiry, redisClient);
    }

}
