package com.huazie.frame.common.interceptor;

import java.lang.reflect.Method;

/**
 * Flea代理拦截器接口
 *
 * <p> {@code IFleaProxyInterceptor} 定义了代理类的方法被调用前后自定义的处理方法。
 *
 * <p> {@code beforeHandle} 前置处理，由子类来实现代理类的方法被调用前的自定义处理逻辑。
 *
 * <p> {@code afterHandle} 后置处理，由子类来实现代理类的方法被调用后的自定义处理逻辑；
 * 如果在出现异常时，想要不执行这段逻辑，可以在子类中添加判断hasException。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaProxyInterceptor {

    /**
     * <p> 前置处理 </p>
     *
     * @param proxyObject 实际代理的对象
     * @param method      与在代理实例上调用的接口方法相对应的Method实例
     * @param args        包含在代理实例的方法调用中传递的参数值的对象数组
     * @since 1.0.0
     */
    void beforeHandle(Object proxyObject, Method method, Object[] args) throws Exception;

    /**
     * <p> 后置处理 </p>
     *
     * @param proxyObject  实际代理的对象
     * @param method       与在代理实例上调用的接口方法相对应的Method实例
     * @param args         包含在代理实例的方法调用中传递的参数值的对象数组
     * @param result       代理方法返回结果
     * @param hasException 是否存在异常【true：存在 false：不存在】
     * @since 1.0.0
     */
    void afterHandle(Object proxyObject, Method method, Object[] args, Object result, boolean hasException) throws Exception;

}
