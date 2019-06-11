package com.huazie.frame.jersey.api.resource;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.jersey.api.data.FleaJerseyRequestData;
import com.huazie.frame.jersey.api.data.FleaJerseyResponseData;
import com.huazie.frame.jersey.api.data.RequestBusinessData;
import com.huazie.frame.jersey.api.data.RequestPublicData;

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

        FleaJerseyResponseData responseData = null;
        try {
            if (ObjectUtils.isEmpty(requestData)) {
                // 抛异常
            }

            // 前置事件

            responseData = doPost(requestData.getPublicData(), requestData.getBusinessData());

            // 后置事件
        } catch (Exception e) {

        }

        return responseData;
    }


    protected abstract FleaJerseyResponseData doPost(RequestPublicData publicData, RequestBusinessData businessData) throws Exception;


}
