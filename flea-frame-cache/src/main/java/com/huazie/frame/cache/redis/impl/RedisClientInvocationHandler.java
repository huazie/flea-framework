package com.huazie.frame.cache.redis.impl;

import com.huazie.frame.common.interceptor.IFleaExceptionProxyInterceptor;
import com.huazie.frame.common.interceptor.IFleaProxyInterceptor;
import com.huazie.frame.common.interceptor.impl.FleaDebugProxyInterceptor;
import com.huazie.frame.common.interceptor.impl.FleaErrorProxyInterceptor;
import com.huazie.frame.common.proxy.FleaProxyHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> Redis客户端调用处理类 </p>
 *
 * @author huazie
 * @version 1.0.0
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
     * @param proxyObject 被代理对象实例
     * @since 1.0.0
     */
    public RedisClientInvocationHandler(Object proxyObject) {
        super(proxyObject, proxyInterceptors, exceptionProxyInterceptor);
    }
}
