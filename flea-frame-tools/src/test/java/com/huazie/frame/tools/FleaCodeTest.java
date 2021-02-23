package com.huazie.frame.tools;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.IOUtils;
import org.junit.Test;

/**
 * <p></p>
 *
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
