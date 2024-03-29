package com.huazie.fleaframework.tools;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.IOUtils;
import org.junit.Test;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCodeTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaCodeTest.class);

    @Test
    public void testFleaCode() {
        String str = IOUtils.toNativeStringFromResource("flea/code/service/FleaSVImpl.code");
        LOGGER.debug("CODE : \n{}", str);
    }

}
