package com.huazie.fleaframework.jersey.server.filter.impl;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.jersey.common.FleaJerseyConstants.RequestPublicDataConstants;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequestData;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponseData;
import com.huazie.fleaframework.jersey.common.data.RequestBusinessData;
import com.huazie.fleaframework.jersey.common.data.RequestPublicData;
import com.huazie.fleaframework.jersey.common.data.ResponseBusinessData;
import com.huazie.fleaframework.jersey.common.data.ResponsePublicData;
import com.huazie.fleaframework.jersey.common.exception.FleaJerseyFilterException;
import com.huazie.fleaframework.jersey.server.filter.IFleaJerseyFilter;

/**
 * 数据预校验过滤器，它主要用于初始化响应报文 和 校验请求报文。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class DataPreCheckFilter implements IFleaJerseyFilter {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(DataPreCheckFilter.class);

    @Override
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws CommonException {

        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "Data PreCheck, Start");
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

        // 请求报文不能为空
        ObjectUtils.checkEmpty(request, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000003");

        FleaJerseyRequestData requestData = request.getRequestData();
        // 请求报文不能为空
        ObjectUtils.checkEmpty(requestData, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000003");

        RequestPublicData requestPublicData = requestData.getPublicData();
        // 请求公共报文不能为空
        ObjectUtils.checkEmpty(requestPublicData, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000004");

        String systemAccountId = requestPublicData.getSystemAccountId();
        // 请求公共报文入参【{0}】不能为空
        StringUtils.checkBlank(systemAccountId, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000006", RequestPublicDataConstants.SYSTEM_ACCOUNT_ID);

        String accountId = requestPublicData.getAccountId();
        // 请求公共报文入参【{0}】不能为空
        StringUtils.checkBlank(accountId, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000006", RequestPublicDataConstants.ACCOUNT_ID);

        String resourceCode = requestPublicData.getResourceCode();
        // 请求公共报文入参【{0}】不能为空
        StringUtils.checkBlank(resourceCode, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000006", RequestPublicDataConstants.RESOURCE_CODE);

        String serviceCode = requestPublicData.getServiceCode();
        // 请求公共报文入参【{0}】不能为空
        StringUtils.checkBlank(serviceCode, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000006", RequestPublicDataConstants.SERVICE_CODE);

        RequestBusinessData requestBusinessData = requestData.getBusinessData();
        // 请求业务报文不能为空
        ObjectUtils.checkEmpty(requestBusinessData, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000005");

        String input = requestBusinessData.getInput();
        // 请求业务报文不能为空
        StringUtils.checkBlank(input, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000005");

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "RequestPublicData = {}", requestPublicData);
            LOGGER.debug1(obj, "RequestBusinessData = {}", requestBusinessData);
            LOGGER.debug1(obj, "Data PreCheck, End");
        }
    }

}
