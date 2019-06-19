package com.huazie.frame.jersey.api.filter.impl;

import com.huazie.frame.jersey.api.data.FleaJerseyRequest;
import com.huazie.frame.jersey.api.data.FleaJerseyResponse;
import com.huazie.frame.jersey.api.filter.IFleaJerseyFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> 系统授权校验过滤器 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SystemAuthCheckFilter implements IFleaJerseyFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(SystemAuthCheckFilter.class);

    @Override
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SystemAuthCheckFilter#doFilter(FleaJerseyRequest, FleaJerseyResponse) Start");
        }
        // 系统授权校验

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SystemAuthCheckFilter#doFilter(FleaJerseyRequest, FleaJerseyResponse) End");
        }
    }

}
