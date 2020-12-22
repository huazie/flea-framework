package com.huazie.frame.common.log;

import com.huazie.frame.common.slf4j.LoggerUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class LogTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test() {
        Object obj = new Object() {};
        LoggerUtils.addMethodMDC(obj);
        // 测试从方法内部获取当前方法的相关信息
        Method method = obj.getClass().getEnclosingMethod();
        LOGGER.debug("method = {}", method);
    }
}
