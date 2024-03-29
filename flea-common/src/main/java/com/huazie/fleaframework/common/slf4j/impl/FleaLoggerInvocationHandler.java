package com.huazie.fleaframework.common.slf4j.impl;

import com.huazie.fleaframework.common.interceptor.IFleaProxyInterceptor;
import com.huazie.fleaframework.common.interceptor.impl.FleaLoggerProxyInterceptor;
import com.huazie.fleaframework.common.proxy.FleaProxyHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Flea日志调用处理类，用于实现代理的Flea日志对象
 * 方法调用前后和调用出现异常时的处理逻辑
 *
 * <p> 构造方法传入指定的代理对象实例，静态初始化块
 * 初始化代理拦截器列表。
 *
 * <p> 代理拦截器列表包含Flea日志代理拦截器实现类。
 *
 * @author huazie
 * @version 1.0.0
 * @see FleaLoggerProxyInterceptor
 * @since 1.0.0
 */
public class FleaLoggerInvocationHandler extends FleaProxyHandler {

    private static List<IFleaProxyInterceptor> proxyInterceptors;

    static {
        proxyInterceptors = new ArrayList<>();
        proxyInterceptors.add(new FleaLoggerProxyInterceptor());
    }

    /**
     * 带参数的构造方法
     *
     * @param proxyObject 代理对象
     * @since 1.0.0
     */
    public FleaLoggerInvocationHandler(Object proxyObject) {
        super(proxyObject, proxyInterceptors, null);
    }
}
