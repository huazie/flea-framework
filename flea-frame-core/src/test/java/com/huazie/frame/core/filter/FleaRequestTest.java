package com.huazie.frame.core.filter;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.core.filter.config.FleaRequestConfig;
import com.huazie.frame.core.filter.task.FleaFilterTaskChainManager;
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
            FleaFilterTaskChainManager.getManager().doFilterTask(null, null);
        } catch (CommonException e) {
            LOGGER.error("Exception =", e);
        }
    }

}
