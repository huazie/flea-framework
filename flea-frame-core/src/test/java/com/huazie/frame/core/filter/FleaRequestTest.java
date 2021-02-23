package com.huazie.frame.core.filter;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.core.request.FleaRequestContext;
import com.huazie.frame.core.request.FleaRequestUtil;
import com.huazie.frame.core.request.FleaRequestXmlDigesterHelper;
import com.huazie.frame.core.request.config.FleaRequestConfig;
import org.junit.Test;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaRequestTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaRequestTest.class);

    @Test
    public void testFleaRequest() {
        FleaRequestXmlDigesterHelper.getInstance().getFleaRequest();
    }

    @Test
    public void testFleaRequestFilter() {
        FleaRequestXmlDigesterHelper.getInstance().getFleaRequestFilter();
    }

    @Test
    public void testFleaSession() {
        LOGGER.debug("FleaSession = {}", FleaRequestConfig.getFleaSession());
    }

    @Test
    public void testFleaUrl() {
        LOGGER.debug("FleaUrl = {}", FleaRequestConfig.getFleaUrl());
    }

    @Test
    public void testFilterTaskList() {
        LOGGER.debug("FilterTasks = {}", FleaRequestConfig.getFilterTasks());
    }

    @Test
    public void testDoFilterTask() {
        try {
            FleaRequestUtil.doFilterTask(new FleaRequestContext());
        } catch (CommonException e) {
            LOGGER.error("Exception =", e);
        }
    }

}
