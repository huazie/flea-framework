package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.redis.RedisClient;
import com.huazie.frame.common.proxy.FleaInvocationHandler;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ObjectUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.lang.reflect.Method;

/**
 * <p> Redis客户端调用处理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisClientInvocationHandler extends FleaInvocationHandler {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(RedisClientInvocationHandler.class);

    /**
     * <p> 带参数的构造方法 </p>
     *
     * @param proxyObject 代理对象
     * @since 1.0.0
     */
    RedisClientInvocationHandler(Object proxyObject) {
        super(proxyObject);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (ObjectUtils.isEmpty(proxyObject)) {
            throw new Exception("The proxyObject must be initialized");
        }

        if (!(proxyObject instanceof RedisClient)) {
            throw new Exception("The proxyObject must implement RedisClient interface");
        }

        RedisClient redisClient = (RedisClient) proxyObject;
        ShardedJedisPool jedisPool = redisClient.getJedisPool();
        if (ObjectUtils.isNotEmpty(jedisPool)) {
            // 从分布式Jedis连接池中获取分布式Jedis对象，并将其初始化给Redis客户端类中的分布式Jedis对象
            redisClient.setShardedJedis(jedisPool.getResource());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug1(new Object() {}, "Get ShardedJedis = {}", redisClient.getShardedJedis());
            }
        }
        try {
            return super.invoke(proxy, method, args);
        } finally {
            ShardedJedis shardedJedis = redisClient.getShardedJedis();
            if (ObjectUtils.isNotEmpty(shardedJedis)) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug1(new Object() {}, "Close ShardedJedis");
                }
                // 使用后，关闭连接
                shardedJedis.close();
            }
        }
    }
}
