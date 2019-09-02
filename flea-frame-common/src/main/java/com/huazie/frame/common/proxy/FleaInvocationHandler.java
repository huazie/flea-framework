package com.huazie.frame.common.proxy;

import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaInvocationHandler.class);

    protected Object proxyObject;

    public FleaInvocationHandler(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaInvocationHandler##invoke(Object, Method, Object[]) Method = {}", method.getName());

            Class<?>[] types = method.getParameterTypes();
            if (ArrayUtils.isNotEmpty(types)) {
                List<String> paramTypeList = new ArrayList<String>();
                for (Class<?> type : types) {
                    paramTypeList.add(type.getSimpleName());
                }
                LOGGER.debug("FleaInvocationHandler##invoke(Object, Method, Object[]) Types = {}", paramTypeList);
            }

            if (ArrayUtils.isNotEmpty(args)) {
                LOGGER.debug("FleaInvocationHandler##invoke(Object, Method, Object[]) OriginArgs = {}", args);
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
                LOGGER.debug("FleaInvocationHandler##invoke(Object, Method, Object[]) Args = {}", mArgs);
            }
        }
        Object result = method.invoke(proxyObject, args);
        if (LOGGER.isDebugEnabled()) {
            if (result instanceof byte[]) {
                LOGGER.debug("FleaInvocationHandler##invoke(Object, Method, Object[]) OriginResult = {}", result);
                // 字节数组可能是对象序列化的
                Object mResult = ObjectUtils.deserialize((byte[]) result);
                if (ObjectUtils.isEmpty(mResult)) {
                    // 字节数组可能是String##getBytes()获取的
                    mResult = new String((byte[]) result);
                }
                LOGGER.debug("FleaInvocationHandler##invoke(Object, Method, Object[]) Result = {}", mResult);
            } else {
                LOGGER.debug("FleaInvocationHandler##invoke(Object, Method, Object[]) Result = {}", result);
            }
        }
        return result;
    }
}
