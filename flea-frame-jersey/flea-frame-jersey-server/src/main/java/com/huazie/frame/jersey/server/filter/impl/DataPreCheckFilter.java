package com.huazie.frame.jersey.server.filter.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.jersey.common.FleaJerseyConstants.RequestPublicDataConstants;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(DataPreCheckFilter.class);

    @Override
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws CommonException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Start");
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

        String systemAcctId = requestPublicData.getSystemAccountId();
        // 请求公共报文入参【{0}】不能为空
        StringUtils.checkBlank(systemAcctId, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000006", RequestPublicDataConstants.SYSTEM_ACCT_ID);

        String systemAcctPwd = requestPublicData.getSystemAccountPassword();
        // 请求公共报文入参【{0}】不能为空
        StringUtils.checkBlank(systemAcctPwd, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000006", RequestPublicDataConstants.SYSTEM_ACCT_PWD);

        String acctId = requestPublicData.getAccountId();
        // 请求公共报文入参【{0}】不能为空
        StringUtils.checkBlank(acctId, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000006", RequestPublicDataConstants.ACCT_ID);

        String resourceCode = requestPublicData.getResourceCode();
        // 请求公共报文入参【{0}】不能为空
        StringUtils.checkBlank(resourceCode, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000006", RequestPublicDataConstants.RESOURCE_CODE);

        String serviceCode = requestPublicData.getServiceCode();
        // 请求公共报文入参【{0}】不能为空
        StringUtils.checkBlank(serviceCode, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000006", RequestPublicDataConstants.SERVICE_CODE);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RequestPublicData = {}", requestPublicData);
        }

        RequestBusinessData requestBusinessData = requestData.getBusinessData();
        // 请求业务报文不能为空
        ObjectUtils.checkEmpty(requestBusinessData, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000005");

        String input = requestBusinessData.getInput();
        // 请求业务报文不能为空
        StringUtils.checkBlank(input, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000005");

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RequestBusinessData = {}", requestBusinessData);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("End");
        }
    }

}
