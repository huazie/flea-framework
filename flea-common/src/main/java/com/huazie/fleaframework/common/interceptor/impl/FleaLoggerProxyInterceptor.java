package com.huazie.fleaframework.common.interceptor.impl;

import com.huazie.fleaframework.common.interceptor.IFleaProxyInterceptor;
import com.huazie.fleaframework.common.slf4j.LoggerUtils;
import com.huazie.fleaframework.common.util.ArrayUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;

import java.lang.reflect.Method;

/**
 * Flea日志代理拦截器实现类
 *
 * <p> 方法 {@code beforeHandle} 用于添加当前日志打印的所在类的方法相关信息至日志上下文MDC中。
 *
 * <p> 方法 {@code afterHandle} 用于清理日志上下文数据，以便于下次重新使用。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaLoggerProxyInterceptor implements IFleaProxyInterceptor {

    @Override
    public void beforeHandle(Object proxyObject, Method method, Object[] args) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        int position = 4; // 这里存在代理，取第5个元素才能找到上层调用类堆栈元素

        if (ArrayUtils.isNotEmpty(args)) {
            Object arg1 = args[0]; // 获取第一个参数
            if (ObjectUtils.isNotEmpty(arg1)) {
                LoggerUtils.addMethodMDC(elements, position, arg1);
            }
        } else {
            LoggerUtils.addMethodMDC(elements, position);
        }
    }

    @Override
    public void afterHandle(Object proxyObject, Method method, Object[] args, Object result, boolean hasException) {
        // 清理日志上下文数据
        LoggerUtils.clearMethodMDC();
    }
}
