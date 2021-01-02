package com.huazie.frame.jersey.server.filter.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.server.filter.IFleaJerseyFilter;

/**
 * <p> Jersey 日志记录过滤器 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class JerseyLoggerFilter implements IFleaJerseyFilter {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(JerseyLoggerFilter.class);

    @Override
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws CommonException {
        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "Jersey Logger, Start");
        }

        // 记录日志

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "Jersey Logger, End");
        }
    }

}
