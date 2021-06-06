package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.IFleaCache;
import com.huazie.frame.cache.redis.RedisClient;

/**
 * Redis Spring缓存类，继承了抽象Spring缓存父类的读【{@code get}】、
 * 写【{@code put}】、删除【{@code delete}】和清空【{@code clear}】
 * 缓存的基本操作方法，由Redis Spring缓存管理类初始化。
 *
 * <p> 它的构造方法中，必须传入一个具体Flea缓存实现类，这里我们使用
 * Redis Flea缓存【{@code RedisFleaCache}】。
 *
 * @author huazie
 * @version 1.0.0
 * @see RedisFleaCache
 * @since 1.0.0
 */
public class RedisSpringCache extends AbstractSpringCache {

    /**
     * <p> 带参数的构造方法，初始化Redis Spring缓存类 </p>
     *
     * @param name      缓存数据主关键字
     * @param fleaCache 具体Flea缓存实现
     * @since 1.0.0
     */
    public RedisSpringCache(String name, IFleaCache fleaCache) {
        super(name, fleaCache);
    }

    /**
     * <p> 带参数的构造方法，初始化Redis Spring缓存类 </p>
     *
     * @param name            缓存数据主关键字
     * @param expiry          缓存数据有效期（单位：s）
     * @param nullCacheExpiry 空缓存数据有效期（单位：s）
     * @param redisClient     Redis客户端
     * @since 1.0.0
     */
    public RedisSpringCache(String name, int expiry, int nullCacheExpiry, RedisClient redisClient) {
        this(name, new RedisFleaCache(name, expiry, nullCacheExpiry, redisClient));
    }

}
