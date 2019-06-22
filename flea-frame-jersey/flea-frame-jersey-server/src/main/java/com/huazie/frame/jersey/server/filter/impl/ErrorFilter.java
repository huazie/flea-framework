package com.huazie.frame.jersey.server.filter.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.common.data.ResponsePublicData;
import com.huazie.frame.jersey.server.filter.IFleaJerseyErrorFilter;
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
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response, Throwable throwable) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ErrorFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) Start");
            LOGGER.debug("ErrorFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) Exception : ", throwable);
        }

        ResponsePublicData publicData = response.getResponseData().getPublicData();

        if (throwable instanceof CommonException) { // 自定义异常
            CommonException exception = (CommonException) throwable;
            // 异常国际码
            String key = exception.getKey();


            // 异常信息
            String errMsg = throwable.getMessage();
            publicData.setResultMess(errMsg);
        } else { // 其他异常

        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ErrorFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) End");
        }
    }
}