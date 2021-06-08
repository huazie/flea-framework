package com.huazie.frame.cache.redis.cluster;

import com.huazie.frame.common.CommonConstants;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Redis集群连接池
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisClusterPool {

    private static final ConcurrentMap<String, RedisClusterPool> redisClusterPools = new ConcurrentHashMap<>();

    private String poolName; // 连接池名

    private RedisClusterPool(String poolName) {
        this.poolName = poolName;
    }

    /**
     * <p> 获取Redis集群连接池实例 (默认连接池) </p>
     *
     * @return Redis集群连接池实例对象
     * @since 1.0.0
     */
    public static RedisClusterPool getInstance() {
        return getInstance(CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME);
    }

    /**
     * <p> 获取Redis集群连接池实例 (指定连接池名)</p>
     *
     * @param poolName 连接池名
     * @return Redis集群连接池实例
     * @since 1.0.0
     */
    public static RedisClusterPool getInstance(String poolName) {
        if (!redisClusterPools.containsKey(poolName)) {
            synchronized (redisClusterPools) {
                if (!redisClusterPools.containsKey(poolName)) {
                    RedisClusterPool redisClusterPool = new RedisClusterPool(poolName);
                    redisClusterPools.putIfAbsent(poolName, redisClusterPool);
                }
            }
        }
        return redisClusterPools.get(poolName);
    }


}
