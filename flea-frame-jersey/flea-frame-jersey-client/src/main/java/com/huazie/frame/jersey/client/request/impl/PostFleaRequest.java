package com.huazie.frame.jersey.client.request.impl;

import com.huazie.frame.jersey.client.request.RequestConfig;
import com.huazie.frame.jersey.client.request.RequestModeEnum;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final static Logger LOGGER = LoggerFactory.getLogger(PostFleaRequest.class);

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
    protected FleaJerseyResponse request(WebTarget target, FleaJerseyRequest request) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("PostFleaRequest##request(WebTarget, FleaJerseyRequest) Start");
        }

        Entity<FleaJerseyRequest> entity = Entity.entity(request, toMediaType());

        FleaJerseyResponse response = target.request(toMediaType()).post(entity, FleaJerseyResponse.class);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("PostFleaRequest##request(WebTarget, FleaJerseyRequest) FleaJerseyResponse = {}", response);
            LOGGER.debug("PostFleaRequest##request(WebTarget, FleaJerseyRequest) End");
        }
        return response;
    }
}
