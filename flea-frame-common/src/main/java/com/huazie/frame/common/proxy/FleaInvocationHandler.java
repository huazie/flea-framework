package com.huazie.frame.common.proxy;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> Flea调用处理父类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @see InvocationHandler
 * @since 1.0.0
 */
public class FleaInvocationHandler implements InvocationHandler {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaInvocationHandler.class);

    protected Object proxyObject;

    /**
     * <p> 带参数的构造方法 </p>
     *
     * @param proxyObject 代理对象
     * @since 1.0.0
     */
    public FleaInvocationHandler(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "Method = {}", method.getName());

            Class<?>[] types = method.getParameterTypes();
            if (ArrayUtils.isNotEmpty(types)) {
                List<String> paramTypeList = new ArrayList<String>();
                for (Class<?> type : types) {
                    paramTypeList.add(type.getSimpleName());
                }
                LOGGER.debug("Types = {}", paramTypeList);
            }

            if (ArrayUtils.isNotEmpty(args)) {
                LOGGER.debug("OriginArgs = {}", args);
                List<Object> mArgs = new ArrayList<Object>();
                for (Object arg : args) {
                    if (arg instanceof byte[]) {
                        // 字节数组可能是对象序列化的
                        Object obj = ObjectUtils.deserialize((byte[]) arg);
                        if (ObjectUtils.isEmpty(obj)) {
                            // 字节数组可能是String##getBytes()获取的
                            obj = new String((byte[]) arg);
                        }
                        mArgs.add(obj);
                    } else {
                        mArgs.add(arg);
                    }
                }
                LOGGER.debug("Args = {}", mArgs);
            }
        }
        Object result = method.invoke(proxyObject, args);
        if (LOGGER.isDebugEnabled()) {
            if (result instanceof byte[]) {
                LOGGER.debug("OriginResult = {}", result);
                // 字节数组可能是对象序列化的
                Object mResult = ObjectUtils.deserialize((byte[]) result);
                if (ObjectUtils.isEmpty(mResult)) {
                    // 字节数组可能是String##getBytes()获取的
                    mResult = new String((byte[]) result);
                }
                LOGGER.debug("Result = {}", mResult);
            } else {
                LOGGER.debug("Result = {}", result);
            }
        }
        return result;
    }
}
