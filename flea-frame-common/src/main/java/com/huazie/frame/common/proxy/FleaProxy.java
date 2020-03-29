package com.huazie.frame.common.proxy;

import com.huazie.frame.common.util.ObjectUtils;

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
     * @param handler    代理处理接口
     * @param <T>        代理对象的类型
     * @return 代理对象实例
     * @since 1.0.0
     */
    protected static <T> T newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler handler, Class<T> proxyClass) {

        T t = null;

        Object proxyInstance = Proxy.newProxyInstance(loader, interfaces, handler);

        if (ObjectUtils.isNotEmpty(proxyClass) && proxyClass.isInstance(proxyInstance)) {
            t = proxyClass.cast(proxyInstance);
        }

        return t;
    }

}
