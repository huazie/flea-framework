package com.huazie.frame.jersey.api.resource;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.jersey.api.data.FleaJerseyRequestData;
import com.huazie.frame.jersey.api.data.FleaJerseyResponseData;
import com.huazie.frame.jersey.api.data.RequestBusinessData;
import com.huazie.frame.jersey.api.data.RequestPublicData;
import com.huazie.frame.jersey.api.data.ResponseBusinessData;
import com.huazie.frame.jersey.api.data.ResponsePublicData;
import com.huazie.frame.jersey.common.exception.FleaResourceException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <p> Flea Jersey Post Resource </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyPostResource {

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public FleaJerseyResponseData doPostResource(FleaJerseyRequestData requestData) {

        FleaJerseyResponseData responseData = new FleaJerseyResponseData();
        ResponsePublicData responsePublicData = new ResponsePublicData();
        responseData.setPublicData(responsePublicData);
        try {
            if (ObjectUtils.isEmpty(requestData)) {
                // 请求报文不能为空
                throw new FleaResourceException("");
            }

            // 前置事件

            ResponseBusinessData responseBusinessData = doPost(requestData.getBusinessData());
            if (ObjectUtils.isNotEmpty(responseBusinessData)) {
                responseData.setBusinessData(responseBusinessData);
            }

            // 后置事件
        } catch (Exception e) {
            // 获取异常描述
            if (e instanceof CommonException) {
                CommonException exp = (CommonException) e;

            }
        }

        return responseData;
    }


    protected abstract ResponseBusinessData doPost(RequestBusinessData requestBusinessData) throws Exception;


}
