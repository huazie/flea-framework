package com.huazie.frame.common.slf4j.impl;

import com.huazie.frame.common.proxy.FleaProxy;
import com.huazie.frame.common.slf4j.FleaLogger;

/**
 * <p> Flea Logger 代理 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaLoggerProxy extends FleaProxy<FleaLogger> {

    /**
     * <p> 获取RedisClient代理类 (默认)</p>
     *
     * @return RedisClient代理类
     * @since 1.0.0
     */
    public static FleaLogger getProxyInstance(Class<?> loggerClass) {
        FleaLogger fleaLogger = new FleaLocalLogger.Builder(loggerClass).build();
        return newProxyInstance(fleaLogger.getClass().getClassLoader(), fleaLogger.getClass().getInterfaces(),
                new FleaLoggerInvocationHandler(fleaLogger), FleaLogger.class);
    }
}
