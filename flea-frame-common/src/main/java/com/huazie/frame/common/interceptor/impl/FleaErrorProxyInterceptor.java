package com.huazie.frame.common.interceptor.impl;

import com.huazie.frame.common.interceptor.IFleaExceptionProxyInterceptor;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Flea Debug信息 代理拦截器实现类
 *
 * <p> {@code beforeHandle} 方法用于以Debug模式打印代理的方法，参数信息
 *
 * <p> {@code afterHandle} 方法用于以Debug模式打印代理的方法返回值的信息
 *
 * <p> {@code exceptionHandle} 方法用于代理的方法被调用出现异常时，处理异常逻辑。
 *
 * @author huazie
 * @version 1.0.0
 * @see IFleaExceptionProxyInterceptor
 * @since 1.0.0
 */
public class FleaErrorProxyInterceptor implements IFleaExceptionProxyInterceptor {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(IFleaExceptionProxyInterceptor.class);

    @Override
    public void exceptionHandle(Object proxyObject, Method method, Object[] args, Throwable throwable) throws Exception {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error1(new Object() {}, "动态代理出现异常，代理对象【proxyObject = " + proxyObject + "】, 调用方法【method = " + method + "】，方法参数【args = " + Arrays.toString(args) + "】", throwable);
        }
    }
}
