package com.huazie.frame.common.proxy;

import com.huazie.frame.common.util.ObjectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Flea代理，封装了通用的代理类获取逻辑。
 *
 * <p>由于【{@code Proxy.newProxyInstance}】返回的是【{@code Object}】
 * 类型的代理类的实例，所以【{@code FleaProxy.newProxyInstance}】提供
 * 指定代理对象类型【{@code proxyClass}】进行转化。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaProxy {

    /**
     * 返回指定接口的代理类的实例，该接口将方法调用分派到指定的调用处理程序
     *
     * @param loader     代理类的类加载器接口
     * @param interfaces 代理类要实现的接口列表
     * @param handler    将方法调用分派到的调用处理程序
     * @param <T>        代理对象的类型
     * @return 指定接口的代理类的实例
     * @since 1.0.0
     */
    public static <T> T newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler handler, Class<T> proxyClass) {

        T t = null;

        Object proxyInstance = Proxy.newProxyInstance(loader, interfaces, handler);

        if (ObjectUtils.isNotEmpty(proxyClass) && proxyClass.isInstance(proxyInstance)) {
            t = proxyClass.cast(proxyInstance);
        }

        return t;
    }

}
