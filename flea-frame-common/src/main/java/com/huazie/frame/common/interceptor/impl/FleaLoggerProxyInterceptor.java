package com.huazie.frame.common.interceptor.impl;

import com.huazie.frame.common.interceptor.IFleaProxyInterceptor;
import com.huazie.frame.common.slf4j.LoggerUtils;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;

import java.lang.reflect.Method;

/**
 * Flea日志 代理拦截器实现类
 *
 * <p> 方法 {@code beforeHandle} 用于添加当前日志打印的所在类的方法相关信息至日志上下文MDC中。
 *
 * <p> 方法 {@code afterHandle} 用于清理日志上下文数据，以便于下次重新使用。
 *
 * @author huazie
 * @version 1.0.0
 * @see IFleaProxyInterceptor
 * @since 1.0.0
 */
public class FleaLoggerProxyInterceptor implements IFleaProxyInterceptor {

    @Override
    public void beforeHandle(Object proxyObject, Method method, Object[] args) throws Exception {
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
    public void afterHandle(Object proxyObject, Method method, Object[] args, Object result, boolean hasException) throws Exception {
        // 清理日志上下文数据
        LoggerUtils.clearMethodMDC();
    }
}
