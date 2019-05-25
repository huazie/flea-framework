package com.huazie.frame.common.proxy;

import com.huazie.frame.common.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p> Flea调用处理父类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @see InvocationHandler
 * @since 1.0.0
 */
public class FleaInvocationHandler implements InvocationHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaInvocationHandler.class);

    protected Object proxyObject;

    public FleaInvocationHandler(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaInvocationHandler#invoke(Object, Method, Object[]) Method = {}, args = {}", method.getName(), args);
        }
        Object result = method.invoke(proxyObject, args);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaInvocationHandler#invoke(Object, Method, Object[]) Result = {}", result);
        }
        return result;
    }
}
