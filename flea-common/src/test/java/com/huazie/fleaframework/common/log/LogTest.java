package com.huazie.fleaframework.common.log;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class LogTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(LogTest.class);

    @Test
    public void test() {
        Object obj = new Object() {};
        // 测试从方法内部获取当前方法的相关信息
        Method method = obj.getClass().getEnclosingMethod();
        LOGGER.debug1(obj,"method = {}", method);
        LOGGER.debug("hello world");
    }
}
