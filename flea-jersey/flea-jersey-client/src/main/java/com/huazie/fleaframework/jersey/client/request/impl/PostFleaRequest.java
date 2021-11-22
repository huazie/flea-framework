package com.huazie.fleaframework.jersey.client.request.impl;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.jersey.client.request.RequestConfig;
import com.huazie.fleaframework.jersey.client.request.RequestModeEnum;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

/**
 * <p> POST 请求 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class PostFleaRequest extends FleaRequest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(PostFleaRequest.class);

    /**
     * <p> 不带参数的构造方法 </p>
     *
     * @since 1.0.0
     */
    public PostFleaRequest() {
    }

    /**
     * <p> 带参数的构造方法 </p>
     *
     * @param config 请求配置
     * @since 1.0.0
     */
    public PostFleaRequest(RequestConfig config) {
        super(config);
    }

    @Override
    protected void init() {
        modeEnum = RequestModeEnum.POST;
    }

    @Override
    protected FleaJerseyResponse request(WebTarget target, FleaJerseyRequest request) throws CommonException {

        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "POST Request, Start");
        }

        Entity<FleaJerseyRequest> entity = Entity.entity(request, toMediaType());

        FleaJerseyResponse response = target.request(toMediaType()).post(entity, FleaJerseyResponse.class);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "POST Request, FleaJerseyResponse = {}", response);
            LOGGER.debug1(obj, "POST Request, End");
        }
        return response;
    }
}
