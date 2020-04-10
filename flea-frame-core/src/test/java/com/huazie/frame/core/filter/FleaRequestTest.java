package com.huazie.frame.core.filter;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaRequestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaRequestTest.class);

    @Test
    public void testFleaRequestConfig() {
        FleaRequestXmlDigesterHelper.getInstance().getFleaRequest();
    }

}
