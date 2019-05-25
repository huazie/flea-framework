package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.redis.RedisClient;
import com.huazie.frame.common.proxy.FleaProxy;

/**
 * <p> RedisClient代理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisClientProxy extends FleaProxy<RedisClient> {

    private static volatile RedisClient redisClient;

    private static Boolean isInit = Boolean.FALSE;

    public static RedisClient getProxyInstance() {
        if (isInit.equals(Boolean.FALSE)) {
            synchronized (isInit) {
                if (isInit.equals(Boolean.FALSE)) {
                    isInit = Boolean.TRUE;
                    // 新建一个Flea Redis客户端类， 用于被代理
                    RedisClient originRedisClient = new FleaRedisClient();
                    redisClient = newProxyInstance(originRedisClient.getClass().getClassLoader(), originRedisClient.getClass().getInterfaces(), new RedisClientInvocationHandler(originRedisClient));
                }
            }
        }
        return redisClient;
    }

}
