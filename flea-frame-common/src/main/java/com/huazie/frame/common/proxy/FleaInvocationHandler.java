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
        Object object = null;
        if (LOGGER.isDebugEnabled()) {
            object = new Object() {};
            LOGGER.debug1(object, "Method = {}", method.getName());

            Class<?>[] types = method.getParameterTypes();
            if (ArrayUtils.isNotEmpty(types)) {
                List<String> paramTypeList = new ArrayList<>();
                for (Class<?> type : types) {
                    paramTypeList.add(type.getSimpleName());
                }
                LOGGER.debug1(object, "Types = {}", paramTypeList);
            }

            if (ArrayUtils.isNotEmpty(args)) {
                LOGGER.debug1(object, "OriginArgs = {}", args);
                List<Object> mArgs = new ArrayList<>();
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
                LOGGER.debug1(object, "Args = {}", mArgs);
            }
        }
        Object result = method.invoke(proxyObject, args);
        if (LOGGER.isDebugEnabled()) {
            if (result instanceof byte[]) {
                LOGGER.debug1(object, "OriginResult = {}", result);
                // 字节数组可能是对象序列化的
                Object mResult = ObjectUtils.deserialize((byte[]) result);
                if (ObjectUtils.isEmpty(mResult)) {
                    // 字节数组可能是String##getBytes()获取的
                    mResult = new String((byte[]) result);
                }
                LOGGER.debug1(object, "Result = {}", mResult);
            } else {
                LOGGER.debug1(object, "Result = {}", result);
            }
        }
        return result;
    }
}
