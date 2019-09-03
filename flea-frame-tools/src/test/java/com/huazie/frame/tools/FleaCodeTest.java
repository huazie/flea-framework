package com.huazie.frame.tools;

import com.huazie.frame.common.util.IOUtils;
import com.huazie.frame.tools.code.FleaCodePanel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p></p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCodeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaCodeTest.class);

    @Test
    public void testFleaCode() {
        String str = IOUtils.toNativeStringFromResource("flea/code/service/FleaSVImpl.code");
        LOGGER.debug("CODE : \n{}", str);
    }

    @Test
    public void testFleaCodeMain() {
        FleaCodePanel codeMain = new FleaCodePanel();
        codeMain.code();
    }

    @Test
    public void testFleaCodeDelete() {
        FleaCodePanel codeMain = new FleaCodePanel();
        codeMain.clean();
    }

}
