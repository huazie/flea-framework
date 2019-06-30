package com.huazie.frame.jersey.server.filter.impl;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.jersey.common.FleaJerseyConstants;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyRequestData;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.common.data.FleaJerseyResponseData;
import com.huazie.frame.jersey.common.data.RequestBusinessData;
import com.huazie.frame.jersey.common.data.RequestPublicData;
import com.huazie.frame.jersey.common.data.ResponseBusinessData;
import com.huazie.frame.jersey.common.data.ResponsePublicData;
import com.huazie.frame.jersey.common.exception.FleaJerseyFilterException;
import com.huazie.frame.jersey.server.filter.IFleaJerseyFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> 数据预校验过滤器 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class DataPreCheckFilter implements IFleaJerseyFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(DataPreCheckFilter.class);

    @Override
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DataPreCheckFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) Start");
        }

        if (ObjectUtils.isEmpty(response)) {
            response = new FleaJerseyResponse();
        }

        FleaJerseyResponseData responseData = response.getResponseData();
        if (ObjectUtils.isEmpty(responseData)) {
            responseData = new FleaJerseyResponseData();
            response.setResponseData(responseData);
        }

        // 初始化响应公共报文
        ResponsePublicData responsePublicData = new ResponsePublicData();
        responseData.setPublicData(responsePublicData);

        // 初始化响应业务报文
        ResponseBusinessData responseBusinessData = new ResponseBusinessData();
        responseData.setBusinessData(responseBusinessData);

        if (ObjectUtils.isEmpty(request)) {
            // 请求报文不能为空
            throw new FleaJerseyFilterException("ERROR-JERSEY-FILTER0000000003");
        }

        FleaJerseyRequestData requestData = request.getRequestData();
        if (ObjectUtils.isEmpty(requestData)) {
            // 请求报文不能为空
            throw new FleaJerseyFilterException("ERROR-JERSEY-FILTER0000000003");
        }

        RequestPublicData requestPublicData = requestData.getPublicData();
        if (ObjectUtils.isEmpty(requestPublicData)) {
            // 请求公共报文不能为空
            throw new FleaJerseyFilterException("ERROR-JERSEY-FILTER0000000004");
        }

        String systemUserId = requestPublicData.getSystemUserId();
        if (StringUtils.isBlank(systemUserId)) {
            // 请求公共报文入参【{0}】不能为空
            throw new FleaJerseyFilterException("ERROR-JERSEY-FILTER0000000006", FleaJerseyConstants.RequestPublicDataConstants.SYSTEM_USER_ID);
        }

        String systemUserPwd = requestPublicData.getSystemUserPassword();
        if (StringUtils.isBlank(systemUserPwd)) {
            // 请求公共报文入参【{0}】不能为空
            throw new FleaJerseyFilterException("ERROR-JERSEY-FILTER0000000006", FleaJerseyConstants.RequestPublicDataConstants.SYSTEM_USER_PWD);
        }

        String resourceCode = requestPublicData.getResourceCode();
        if (StringUtils.isBlank(resourceCode)) {
            // 请求公共报文入参【{0}】不能为空
            throw new FleaJerseyFilterException("ERROR-JERSEY-FILTER0000000006", FleaJerseyConstants.RequestPublicDataConstants.RESOURCE_CODE);
        }

        String serviceCode = requestPublicData.getServiceCode();
        if (StringUtils.isBlank(serviceCode)) {
            // 请求公共报文入参【{0}】不能为空
            throw new FleaJerseyFilterException("ERROR-JERSEY-FILTER0000000006", FleaJerseyConstants.RequestPublicDataConstants.SERVICE_CODE);
        }

        RequestBusinessData requestBusinessData = requestData.getBusinessData();
        if (ObjectUtils.isEmpty(requestBusinessData)) {
            // 请求业务报文不能为空
            throw new FleaJerseyFilterException("ERROR-JERSEY-FILTER0000000005");
        }

        Object input = requestBusinessData.getInput();
        if (ObjectUtils.isEmpty(input)) {
            // 请求业务报文不能为空
            throw new FleaJerseyFilterException("ERROR-JERSEY-FILTER0000000005");
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DataPreCheckFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) end");
        }
    }

}
