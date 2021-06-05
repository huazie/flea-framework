package com.huazie.frame.common.interceptor.impl;

import com.huazie.frame.common.interceptor.IFleaProxyInterceptor;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Flea Debug信息代理拦截器实现类
 *
 * <p> 方法【{@code beforeHandle}】，用于以Debug模式打印代理的方法，参数信息
 *
 * <p> 方法【{@code afterHandle}】，用于以Debug模式打印代理的方法返回值的信息
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaDebugProxyInterceptor implements IFleaProxyInterceptor {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaDebugProxyInterceptor.class);

    @Override
    public void beforeHandle(Object proxyObject, Method method, Object[] args) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            Object object = new Object() {};
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
    }

    @Override
    public void afterHandle(Object proxyObject, Method method, Object[] args, Object result, boolean hasException) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            Object object = new Object() {};
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
    }
}
