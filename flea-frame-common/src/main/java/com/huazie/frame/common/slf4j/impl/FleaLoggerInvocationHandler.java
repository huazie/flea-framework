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

        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        int position = 3; // 这里存在代理，取第四个元素才能找到上层调用类堆栈元素

        if (ArrayUtils.isNotEmpty(args)) {
            Object arg1 = args[0]; // 获取第一个参数
            if (ObjectUtils.isNotEmpty(arg1)) {
                LoggerUtils.addMethodMDC(elements, position, arg1);
            }
        } else {
            LoggerUtils.addMethodMDC(elements, position);
        }

        try {
            return method.invoke(proxyObject, args);
        } finally {
            // 清理日志上下文数据
            LoggerUtils.clearMethodMDC();
        }
    }
}
