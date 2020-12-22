package com.huazie.frame.common.slf4j.impl;

import com.huazie.frame.common.slf4j.LoggerUtils;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p> Flea Logger 调用处理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaLoggerInvocationHandler implements InvocationHandler {

    protected Object proxyObject;

    /**
     * <p> 带参数的构造方法 </p>
     *
     * @param proxyObject 代理对象
     * @since 1.0.0
     */
    public FleaLoggerInvocationHandler(Object proxyObject) {
        this.proxyObject = proxyObject;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (ArrayUtils.isNotEmpty(args)) {
            Object arg1 = args[0]; // 获取第一个参数
            if (ObjectUtils.isNotEmpty(arg1)) {
                // 处理调用方基础类的立即封闭方法的对象，获取该方法相关信息，并设置到日志上下文MDC中
                LoggerUtils.addMethodMDC(arg1);
            }
        }
        return method.invoke(proxyObject, args);
    }
}
