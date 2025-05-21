package com.huazie.fleaframework.cache.redis;

import com.huazie.fleaframework.cache.exceptions.FleaCacheMaxAttemptsException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.util.Pool;

import java.io.Closeable;
import java.io.IOException;

/**
 * Redis客户端命令行，封装了使用Jedis操作Redis缓存的公共逻辑，
 * 如果出现异常可以重试{@code maxAttempts} 次。
 *
 * <p> 抽象方法 {@code execute}，由子类或匿名类实现。在实际调用前，
 * 需要从Jedis连接池中获取Jedis对象；调用结束后，
 * 关闭Jedis对象，归还给Jedis连接池。
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.1.0
 */
public abstract class RedisClientCommand<T, P extends Pool<M>, M> {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(RedisClientCommand.class);

    private final P pool; // Jedis连接池

    private final int maxAttempts; // Redis客户端操作最大尝试次数【包含第一次操作】

    public RedisClientCommand(P pool, int maxAttempts) {
        this.pool = pool;
        this.maxAttempts = maxAttempts;
    }

    public abstract T execute(M connection);

    /**
     * 执行分布式Jedis操作
     *
     * @return 分布式Jedis对象操作的结果
     * @since 1.0.0
     */
    public T run() {
        return runWithRetries(this.maxAttempts);
    }

    /**
     * 执行分布式Jedis操作，如果出现异常，包含第一次操作，可最多尝试maxAttempts次。
     *
     * @param attempts 重试次数
     * @return 分布式Jedis对象操作的结果
     * @since 1.0.0
     */
    private T runWithRetries(int attempts) {
        if (attempts <= 0) {
            ExceptionUtils.throwFleaException(FleaCacheMaxAttemptsException.class, "No more attempts left.");
        }
        M connection = null;
        Object obj = new Object() {};
        try {
            connection = pool.getResource();
            LOGGER.debug1(obj, "Get Jedis = {}", connection);
            T result = execute(connection);
            LOGGER.debug1(obj, "Result = {}", result);
            return result;
        } catch (JedisConnectionException e) {
            // 在开始下一次尝试前，释放当前分布式Jedis的连接，将分布式Jedis对象归还给分布式Jedis连接池
            releaseConnection(connection);
            connection = null; // 这里置空是为了最后finally不重复操作
            LOGGER.error1(obj, "Redis连接异常：", e);
            int currAttempts = this.maxAttempts - attempts + 1;
            LOGGER.error1(obj, "第 {} 次尝试失败，开始第 {} 次尝试...", currAttempts, currAttempts + 1);
            return runWithRetries(attempts - 1);
        } finally {
            releaseConnection(connection);
        }
    }

    /**
     * 释放指定Jedis的连接，将Jedis对象归还给Jedis连接池
     *
     * @param connection Jedis实例
     * @since 1.0.0
     */
    private void releaseConnection(M connection) {
        if (ObjectUtils.isNotEmpty(connection)) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "Close Jedis");
            try {
                ((Closeable)connection).close();
            } catch (IOException e) {
                LOGGER.error1(obj, "Jedis close occurs Exception:", e);
            }
        }
    }
}
