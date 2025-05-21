package com.huazie.fleaframework.jersey.client.request.impl;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.jersey.client.request.RequestConfig;
import com.huazie.fleaframework.jersey.client.request.RequestModeEnum;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

/**
 * PUT 请求，对外提供了执行 PUT 请求的能力。
 * <p> 注：服务端提供的资源入口方法需包含 PUT 注解。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class PutFleaRequest extends FleaRequest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(PutFleaRequest.class);

    /**
     * 默认的构造方法
     *
     * @since 1.0.0
     */
    public PutFleaRequest() {
    }

    /**
     * 带请求配置参数的构造方法
     *
     * @param config 请求配置
     * @since 1.0.0
     */
    public PutFleaRequest(RequestConfig config) {
        super(config);
    }

    @Override
    protected void init() {
        modeEnum = RequestModeEnum.PUT;
    }

    @Override
    protected FleaJerseyResponse request(WebTarget target, FleaJerseyRequest request) throws CommonException {

        Object obj = new Object() {};
        LOGGER.debug1(obj, "PUT Request, Start");

        Entity<FleaJerseyRequest> entity = Entity.entity(request, toMediaType());

        FleaJerseyResponse response = target.request(toMediaType()).put(entity, FleaJerseyResponse.class);

        LOGGER.debug1(obj, "PUT Request, FleaJerseyResponse = {}", response);
        LOGGER.debug1(obj, "PUT Request, End");

        return response;
    }
}
