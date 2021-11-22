package com.huazie.fleaframework.common.interceptor.impl;

import com.huazie.fleaframework.common.interceptor.IFleaExceptionProxyInterceptor;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Flea异常代理拦截器实现类
 *
 * <p> 方法 {@code exceptionHandle} 用于实现代理类方法被调用出现异常的自定义处理逻辑。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaErrorProxyInterceptor implements IFleaExceptionProxyInterceptor {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaErrorProxyInterceptor.class);

    @Override
    public void exceptionHandle(Object proxyObject, Method method, Object[] args, Throwable throwable) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error1(new Object() {}, "动态代理出现异常，代理对象【proxyObject = " + proxyObject + "】, 调用方法【method = " + method + "】，方法参数【args = " + Arrays.toString(args) + "】", throwable);
        }
    }
}
