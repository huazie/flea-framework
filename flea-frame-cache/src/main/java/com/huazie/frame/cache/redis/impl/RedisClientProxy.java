package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.common.CacheConstants;
import com.huazie.frame.cache.redis.RedisClient;
import com.huazie.frame.common.proxy.FleaProxy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> RedisClient代理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisClientProxy extends FleaProxy<RedisClient> {

    private final static Map<String, RedisClient> redisClients = new ConcurrentHashMap<String, RedisClient>();

    /**
     * <p> 获取RedisClient代理类 (默认)</p>
     *
     * @return RedisClient代理类
     * @since 1.0.0
     */
    public static RedisClient getProxyInstance() {
        return getProxyInstance(CacheConstants.FleaCacheConstants.DEFAUTL_POOL_NAME);
    }

    /**
     * <p> 获取RedisClient代理类 (指定连接池名)</p>
     *
     * @param poolName 连接池名
     * @return RedisClient代理类
     * @since 1.0.0
     */
    public static RedisClient getProxyInstance(String poolName) {
        if (!redisClients.containsKey(poolName)) {
            synchronized (redisClients) {
                if (!redisClients.containsKey(poolName)) {
                    // 新建一个Flea Redis客户端类， 用于被代理
                    RedisClient originRedisClient;
                    if(CacheConstants.FleaCacheConstants.DEFAUTL_POOL_NAME.equals(poolName)) {
                        originRedisClient = new FleaRedisClient();
                    } else {
                        originRedisClient = new FleaRedisClient(poolName);
                    }
                    RedisClient proxyRedisClient = newProxyInstance(originRedisClient.getClass().getClassLoader(), originRedisClient.getClass().getInterfaces(), new RedisClientInvocationHandler(originRedisClient));
                    redisClients.put(poolName, proxyRedisClient);
                }
            }
        }
        return redisClients.get(poolName);
    }

}
