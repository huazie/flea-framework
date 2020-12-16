package com.huazie.frame.jersey.server.filter.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.server.filter.IFleaJerseyFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> Jersey 日志记录过滤器 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class JerseyLoggerFilter implements IFleaJerseyFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JerseyLoggerFilter.class);

    @Override
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws CommonException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Start");
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("JerseyLoggerFilter#doFilter(FleaJerseyRequest, FleaJerseyResponse) End");
        }
    }

}
