package com.huazie.frame.common.proxy;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.interceptor.IFleaExceptionProxyInterceptor;
import com.huazie.frame.common.interceptor.IFleaProxyInterceptor;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * <p> Flea调用处理父类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @see InvocationHandler
 * @since 1.0.0
 */
public class FleaProxyHandler implements InvocationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaProxyHandler.class);

    private Object proxyObject; // 实际代理的对象

    private List<IFleaProxyInterceptor> fleaProxyInterceptors; // 代理拦截器列表

    private IFleaExceptionProxyInterceptor fleaExceptionProxyInterceptor; // 异常代理拦截器

    public FleaProxyHandler(Object proxyObject, List<IFleaProxyInterceptor> fleaProxyInterceptors, IFleaExceptionProxyInterceptor fleaExceptionProxyInterceptor) {
        this.proxyObject = proxyObject;
        this.fleaProxyInterceptors = fleaProxyInterceptors;
        this.fleaExceptionProxyInterceptor = fleaExceptionProxyInterceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (ObjectUtils.isEmpty(proxyObject)) {
            throw new Exception("The proxyObject must be initialized");
        }

        if (CollectionUtils.isEmpty(fleaProxyInterceptors)) {
            return method.invoke(proxyObject, args);
        }

        // 前置处理
        for (IFleaProxyInterceptor fleaProxyInterceptor : fleaProxyInterceptors) {
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
            if (ObjectUtils.isNotEmpty(fleaExceptionProxyInterceptor)) {
                try {
                    fleaExceptionProxyInterceptor.exceptionHandle(proxyObject, method, args, e);
                } catch (CommonException ex) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error("异常处理【exceptionHandle】出现异常，异常代理拦截器【 fleaExceptionProxyInterceptor = " + fleaExceptionProxyInterceptor + "】，代理对象【proxyObject = " + proxyObject + "】", e);
                    }
                }
            }
            throw e;
        } finally {
            // 后置处理
            for (IFleaProxyInterceptor fleaProxyInterceptor : fleaProxyInterceptors) {
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
