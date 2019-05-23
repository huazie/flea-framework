package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.redis.config.RedisConfig;
import redis.clients.jedis.ShardedJedisPool;

/**
 * <p>  Flea Redis 连接池 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisPool {

    private static volatile RedisPool redisPool;

    private static Boolean isInit = Boolean.FALSE;

    private RedisConfig redisConfig;

    private ShardedJedisPool jedisPool;

    private RedisPool() {
    }

    /**
     * <p> 获取Redis连接池实例 </p>
     *
     * @return Redis连接池实例对象
     * @since 1.0.0
     */
    public static RedisPool getInstance() {
        if (isInit.equals(Boolean.FALSE)) {
            synchronized (isInit) {
                if (isInit.equals(Boolean.FALSE)) {
                    isInit = Boolean.TRUE;
                    redisPool = new RedisPool();
                    redisPool.redisConfig = RedisConfig.getConfig();
                    redisPool.jedisPool = new ShardedJedisPool(redisPool.redisConfig.getJedisPoolConfig(), redisPool.redisConfig.getServers(), redisPool.redisConfig.getHashingAlg());
                }
            }
        }
        return redisPool;
    }

    public RedisConfig getRedisConfig() {
        return redisConfig;
    }

    public ShardedJedisPool getJedisPool() {
        return jedisPool;
    }
}
