package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.cache.redis.RedisClient;
import com.huazie.frame.common.interceptor.IFleaProxyInterceptor;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ObjectUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.lang.reflect.Method;

/**
 * Redis客户端代理拦截器实现类
 *
 * <p> 方法 {@code beforeHandle} 用于在Redis客户端调用指定方法前，从分布式Jedis连接池中
 * 获取分布式Jedis对象，并将其初始化给Redis客户端类中的分布式Jedis对象。
 *
 * <p> 方法 {@code afterHandle} 用于在Redis客户端调用指定方法后，关闭分布式Jedis对象，
 * 并将它归还给分布式Jedis连接池。
 *
 * @author huazie
 * @version 1.0.0
 * @see IFleaProxyInterceptor
 * @since 1.0.0
 */
public class RedisClientProxyInterceptor implements IFleaProxyInterceptor {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(RedisClientProxyInterceptor.class);

    @Override
    public void beforeHandle(Object proxyObject, Method method, Object[] args) throws Exception {
        RedisClient redisClient = convertProxyObject(proxyObject);
        ShardedJedisPool jedisPool = redisClient.getJedisPool();
        if (ObjectUtils.isNotEmpty(jedisPool)) {
            // 从分布式Jedis连接池中获取分布式Jedis对象，并将其初始化给Redis客户端类中的分布式Jedis对象
            redisClient.setShardedJedis(jedisPool.getResource());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug1(new Object() {}, "Get ShardedJedis = {}", redisClient.getShardedJedis());
            }
        }
    }

    @Override
    public void afterHandle(Object proxyObject, Method method, Object[] args, Object result, boolean hasException) throws Exception {
        RedisClient redisClient = convertProxyObject(proxyObject);
        ShardedJedis shardedJedis = redisClient.getShardedJedis();
        if (ObjectUtils.isNotEmpty(shardedJedis)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug1(new Object() {}, "Close ShardedJedis");
            }
            // 使用后，关闭连接
            shardedJedis.close();
        }
    }

    /**
     * <p> 转换代理对象 </p>
     *
     * @param proxyObject 被代理的对象实例
     * @return Redis客户端
     */
    private RedisClient convertProxyObject(Object proxyObject) throws Exception {
        if (!(proxyObject instanceof RedisClient)) {
            throw new Exception("The proxyObject must implement RedisClient interface");
        }
        return (RedisClient) proxyObject;
    }
}
