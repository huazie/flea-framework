package com.huazie.frame.jersey.api.filter.impl;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.jersey.api.data.FleaJerseyRequest;
import com.huazie.frame.jersey.api.data.FleaJerseyRequestData;
import com.huazie.frame.jersey.api.data.FleaJerseyResponse;
import com.huazie.frame.jersey.api.data.FleaJerseyResponseData;
import com.huazie.frame.jersey.api.data.RequestBusinessData;
import com.huazie.frame.jersey.api.data.RequestPublicData;
import com.huazie.frame.jersey.api.data.ResponsePublicData;
import com.huazie.frame.jersey.api.filter.IFleaJerseyFilter;
import com.huazie.frame.jersey.common.exception.FleaResourceException;

/**
 * <p> 数据预校验过滤器 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class DataPreCheckFilter implements IFleaJerseyFilter {

    @Override
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws Exception {
        if (ObjectUtils.isEmpty(response)) {
            response = new FleaJerseyResponse();
        }

        FleaJerseyResponseData responseData = response.getResponseData();
        if (ObjectUtils.isEmpty(responseData)) {
            responseData = new FleaJerseyResponseData();
        }

        ResponsePublicData responsePublicData = new ResponsePublicData();
        responseData.setPublicData(responsePublicData);

        if (ObjectUtils.isEmpty(request)) {
            // 请求报文不能为空
            throw new FleaResourceException("");
        }

        FleaJerseyRequestData requestData = request.getRequestData();
        if (ObjectUtils.isEmpty(requestData)) {
            // 请求报文不能为空
            throw new FleaResourceException("");
        }

        RequestPublicData requestPublicData = requestData.getPublicData();
        if (ObjectUtils.isEmpty(requestPublicData)) {
            // 请求公共报文不能为空
            throw new FleaResourceException("");
        }

        RequestBusinessData requestBusinessData = requestData.getBusinessData();
        if (ObjectUtils.isEmpty(requestBusinessData)) {
            // 请求业务报文不能为空
            throw new FleaResourceException("");
        }


    }

}
