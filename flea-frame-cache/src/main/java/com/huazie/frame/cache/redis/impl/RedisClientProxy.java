package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.redis.RedisClient;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.proxy.FleaProxy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p> RedisClient代理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisClientProxy extends FleaProxy {

    private static final ConcurrentMap<String, RedisClient> redisClients = new ConcurrentHashMap<>();

    /**
     * <p> 获取RedisClient代理类 (默认)</p>
     *
     * @return RedisClient代理类
     * @since 1.0.0
     */
    public static RedisClient getProxyInstance() {
        return getProxyInstance(CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME);
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
                    // 新建一个Flea Redis客户端类实例
                    RedisClient originRedisClient;
                    if (CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME.equals(poolName)) {
                        originRedisClient = new FleaRedisClient.Builder().build();
                    } else {
                        originRedisClient = new FleaRedisClient.Builder(poolName).build();
                    }
                    Class<?> clazz = originRedisClient.getClass();
                    // 构建 Flea Redis客户端类实例的代理类
                    RedisClient proxyRedisClient = newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),
                            new RedisClientInvocationHandler(originRedisClient), RedisClient.class);
                    redisClients.put(poolName, proxyRedisClient);
                }
            }
        }
        return redisClients.get(poolName);
    }

}
