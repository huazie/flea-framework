package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.common.interceptor.IFleaExceptionProxyInterceptor;
import com.huazie.frame.common.interceptor.IFleaProxyInterceptor;
import com.huazie.frame.common.interceptor.impl.FleaDebugProxyInterceptor;
import com.huazie.frame.common.interceptor.impl.FleaErrorProxyInterceptor;
import com.huazie.frame.common.proxy.FleaProxyHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis客户端调用处理类，用于实现代理的Redis客户端
 * 方法调用前后和调用出现异常时的处理逻辑
 *
 * <p> 构造方法传入指定的代理对象实例，静态初始化块
 * 初始化代理拦截器列表和异常拦截器。
 *
 * <p> 代理拦截器列表包含Redis客户端代理拦截器实现和
 * Flea Debug信息代理拦截器实现。
 *
 * @author huazie
 * @version 1.0.0
 * @see RedisClientProxyInterceptor
 * @see FleaDebugProxyInterceptor
 * @see FleaErrorProxyInterceptor
 * @since 1.0.0
 */
public class RedisClientInvocationHandler extends FleaProxyHandler {

    private static List<IFleaProxyInterceptor> proxyInterceptors;

    private static IFleaExceptionProxyInterceptor exceptionProxyInterceptor;

    static {
        proxyInterceptors = new ArrayList<>();
        proxyInterceptors.add(new RedisClientProxyInterceptor());
        proxyInterceptors.add(new FleaDebugProxyInterceptor());
        exceptionProxyInterceptor = new FleaErrorProxyInterceptor();
    }

    /**
     * <p> 带参数的构造方法 </p>
     *
     * @param proxyObject 代理对象实例
     * @since 1.0.0
     */
    public RedisClientInvocationHandler(Object proxyObject) {
        super(proxyObject, proxyInterceptors, exceptionProxyInterceptor);
    }
}
