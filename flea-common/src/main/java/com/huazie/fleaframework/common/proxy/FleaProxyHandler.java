package com.huazie.fleaframework.common.proxy;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.interceptor.IFleaExceptionProxyInterceptor;
import com.huazie.fleaframework.common.interceptor.IFleaProxyInterceptor;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Flea调用处理父类，定义了代理类方法调用前后和调用出现异常时的处理逻辑。
 *
 * <p> 成员变量 {@code proxyObject}，即实际被代理的对象实例，在代理拦
 * 截器和异常代理拦截器中均使用到。
 *
 * <p> 成员变量 {@code proxyInterceptors}，即代理拦截器列表，其中定义
 * 了代理类方法调用前后的自定义处理方法。
 *
 * <p> 成员变量 {@code exceptionProxyInterceptor}，即异常代理拦截器，
 * 其中定义了代理类方法调用出现异常的自定义处理方法。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaProxyHandler implements InvocationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaProxyHandler.class);

    private Object proxyObject; // 实际被代理的对象实例

    private List<IFleaProxyInterceptor> proxyInterceptors; // 代理拦截器列表

    private IFleaExceptionProxyInterceptor exceptionProxyInterceptor; // 异常代理拦截器

    public FleaProxyHandler(Object proxyObject, List<IFleaProxyInterceptor> fleaProxyInterceptors, IFleaExceptionProxyInterceptor fleaExceptionProxyInterceptor) {
        this.proxyObject = proxyObject;
        this.proxyInterceptors = fleaProxyInterceptors;
        this.exceptionProxyInterceptor = fleaExceptionProxyInterceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (ObjectUtils.isEmpty(proxyObject)) {
            throw new Exception("The proxyObject must be initialized");
        }

        if (CollectionUtils.isEmpty(proxyInterceptors)) {
            return method.invoke(proxyObject, args);
        }

        // 前置处理
        for (IFleaProxyInterceptor fleaProxyInterceptor : proxyInterceptors) {
            try {
                fleaProxyInterceptor.beforeHandle(proxyObject, method, args);
            } catch (CommonException e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("前置处理【beforeHandle】出现异常，代理拦截器【 fleaProxyInterceptor = " + fleaProxyInterceptor + "】，代理对象【proxyObject = " + proxyObject + "】", e);
                }
            }
        }

        boolean hasException = false;
        Object result = null;
        try {
            result = method.invoke(proxyObject, args);
        } catch (Exception e) {
            hasException = true;
            // 异常处理
            if (ObjectUtils.isNotEmpty(exceptionProxyInterceptor)) {
                try {
                    exceptionProxyInterceptor.exceptionHandle(proxyObject, method, args, e);
                } catch (CommonException ex) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error("异常处理【exceptionHandle】出现异常，异常代理拦截器【 exceptionProxyInterceptor = " + exceptionProxyInterceptor + "】，代理对象【proxyObject = " + proxyObject + "】", e);
                    }
                }
            }
            throw e;
        } finally {
            // 后置处理
            for (IFleaProxyInterceptor fleaProxyInterceptor : proxyInterceptors) {
                try {
                    fleaProxyInterceptor.afterHandle(proxyObject, method, args, result, hasException);
                } catch (CommonException e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error("后置处理【afterHandle】出现异常，代理拦截器【 fleaProxyInterceptor = " + fleaProxyInterceptor + "】，代理对象【proxyObject = " + proxyObject + "】", e);
                    }
                }
            }
        }

        return result;
    }
}
