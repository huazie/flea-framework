package com.huazie.frame.core.filter.task.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.core.filter.task.IFleaFilterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p> URL校验过滤器任务 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class UrlCheckFilterTask implements IFleaFilterTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlCheckFilterTask.class);

    @Override
    public void doFilterTask(ServletRequest servletRequest, ServletResponse servletResponse) throws CommonException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("UrlCheckFilterTask##doFilterTask(ServletRequest, ServletResponse) Start");
        }


        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("UrlCheckFilterTask##doFilterTask(ServletRequest, ServletResponse) End");
        }
    }
}
