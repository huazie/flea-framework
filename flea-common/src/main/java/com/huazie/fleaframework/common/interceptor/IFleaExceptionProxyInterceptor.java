package com.huazie.fleaframework.common.interceptor;

import java.lang.reflect.Method;

/**
 * Flea 异常代理拦截器接口，定义了代理类的方法被调用出现
 * 异常时的处理方法。
 *
 * <p> 方法 {@code beforeHandle} ，即异常处理，由子类来
 * 实现代理类的方法被调用时出现异常的自定义处理逻辑。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaExceptionProxyInterceptor {

    /**
     * 异常处理
     *
     * @param proxyObject 实际代理的对象
     * @param method      与在代理实例上调用的接口方法相对应的Method实例
     * @param args        包含在代理实例的方法调用中传递的参数值的对象数组
     * @param throwable   捕获的异常实例
     * @since 1.0.0
     */
    void exceptionHandle(Object proxyObject, Method method, Object[] args, Throwable throwable) throws Exception;
}
