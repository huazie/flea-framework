package com.huazie.frame.common.slf4j.impl;

import com.huazie.frame.common.interceptor.IFleaProxyInterceptor;
import com.huazie.frame.common.interceptor.impl.FleaDebugProxyInterceptor;
import com.huazie.frame.common.interceptor.impl.FleaLoggerProxyInterceptor;
import com.huazie.frame.common.proxy.FleaProxyHandler;
import com.huazie.frame.common.slf4j.LoggerUtils;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> Flea Logger 调用处理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaLoggerInvocationHandler extends FleaProxyHandler {

    private static List<IFleaProxyInterceptor> proxyInterceptors;

    static {
        proxyInterceptors = new ArrayList<>();
        proxyInterceptors.add(new FleaLoggerProxyInterceptor());
    }

    /**
     * <p> 带参数的构造方法 </p>
     *
     * @param proxyObject 代理对象
     * @since 1.0.0
     */
    public FleaLoggerInvocationHandler(Object proxyObject) {
        super(proxyObject, proxyInterceptors, null);
    }
}
