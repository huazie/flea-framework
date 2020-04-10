package com.huazie.frame.core.filter.task.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.core.filter.config.FleaRequestConfig;
import com.huazie.frame.core.filter.task.IFleaFilterTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p> SESSION信息校验过滤器任务 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SessionCheckFilterTask implements IFleaFilterTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionCheckFilterTask.class);

    @Override
    public void doFilterTask(ServletRequest servletRequest, ServletResponse servletResponse) throws CommonException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SessionCheckFilterTask##doFilterTask(ServletRequest, ServletResponse) Start");
        }


        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SessionCheckFilterTask##doFilterTask(ServletRequest, ServletResponse) End");
        }
    }

}
