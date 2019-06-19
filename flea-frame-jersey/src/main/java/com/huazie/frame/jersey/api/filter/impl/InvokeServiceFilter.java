package com.huazie.frame.jersey.api.filter.impl;

import com.huazie.frame.jersey.api.data.FleaJerseyRequest;
import com.huazie.frame.jersey.api.data.FleaJerseyResponse;
import com.huazie.frame.jersey.api.filter.IFleaJerseyFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> 服务调用过滤器 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class InvokeServiceFilter implements IFleaJerseyFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(InvokeServiceFilter.class);

    @Override
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("InvokeServiceFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) Start");
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("InvokeServiceFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) End");
        }
    }

}
