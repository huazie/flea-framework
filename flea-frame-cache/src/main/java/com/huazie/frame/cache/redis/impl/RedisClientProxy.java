package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.redis.RedisClient;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.proxy.FleaProxy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Redis客户端代理，用于获取代理的Redis客户端。
 *
 * <p> 它有两种方式获取代理的Redis客户端：<br/>
 * 一种是获取默认连接池的代理的Redis客户端，应用在单个缓存接入场景；<br/>
 * 一种是获取指定连接池的代理的Redis客户端，应用在整合缓存接入场景。
 *
 * <p> 同步集合类【{@code redisClients}】，存储的键为连接池名，
 * 值为代理的Redis客户端。针对单个缓存接入场景，存储的键为【default】；
 * 针对整合缓存接入场景，存储的键为缓存组名【group】。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisClientProxy {

    private static final ConcurrentMap<String, RedisClient> redisClients = new ConcurrentHashMap<>();

    /**
     * <p> 获取代理的Redis客户端 (默认default)</p>
     *
     * @return 代理的Redis客户端
     * @since 1.0.0
     */
    public static RedisClient getProxyInstance() {
        return getProxyInstance(CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME);
    }

    /**
     * <p> 获取代理的Redis客户端 (指定连接池名)</p>
     *
     * @param poolName 连接池名
     * @return 代理的Redis客户端
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
                    RedisClient proxyRedisClient = FleaProxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),
                            new RedisClientInvocationHandler(originRedisClient), RedisClient.class);
                    redisClients.put(poolName, proxyRedisClient);
                }
            }
        }
        return redisClients.get(poolName);
    }

}
