package com.huazie.frame.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * <p> Flea 代理父类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaProxy<T> {

    /**
     * <p> 生成代理对象实例 </p>
     *
     * @param loader     代理对象的ClassLoader
     * @param interfaces 代理对象的接口数组
     * @param handler
     * @param <T>
     * @return
     */
    public static <T> T newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler handler) {

        // 前置处理

        T t = (T) Proxy.newProxyInstance(loader, interfaces, handler);

        // 后置处理

        return t;
    }

}
