package com.huazie.fleaframework.common.slf4j.impl;

import com.huazie.fleaframework.common.proxy.FleaProxy;
import com.huazie.fleaframework.common.slf4j.FleaLogger;

/**
 * Flea日志代理，用于获取代理的Flea日志类
 *
 * @author huazie
 * @version 1.0.0
 * @see FleaLoggerInvocationHandler
 * @since 1.0.0
 */
public class FleaLoggerProxy {

    /**
     * 获取代理的Flea日志类
     *
     * @return 代理的Flea日志类
     * @since 1.0.0
     */
    public static FleaLogger getProxyInstance(Class<?> loggerClass) {
        FleaLogger fleaLogger = new FleaLocalLogger.Builder(loggerClass).build();
        return FleaProxy.newProxyInstance(fleaLogger.getClass().getClassLoader(), fleaLogger.getClass().getInterfaces(),
                new FleaLoggerInvocationHandler(fleaLogger), FleaLogger.class);
    }
}
