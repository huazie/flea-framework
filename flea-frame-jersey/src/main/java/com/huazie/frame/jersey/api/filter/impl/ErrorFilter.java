package com.huazie.frame.jersey.api.filter.impl;

import com.huazie.frame.jersey.api.data.FleaJerseyResponse;
import com.huazie.frame.jersey.api.filter.IFleaJerseyErrorFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> 异常过滤器实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ErrorFilter implements IFleaJerseyErrorFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public void doFilter(FleaJerseyResponse response, Throwable throwable) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ErrorFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) Start");
            LOGGER.debug("ErrorFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) Exception : ", throwable);
        }



        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ErrorFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) End");
        }
    }
}