package com.huazie.fleaframework.jersey.client.request.impl;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.jersey.client.request.RequestConfig;
import com.huazie.fleaframework.jersey.client.request.RequestModeEnum;
import com.huazie.fleaframework.jersey.common.FleaJerseyConstants;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;

import javax.ws.rs.client.WebTarget;

/**
 * GET 请求，对外提供了执行 GET 请求的能力。
 * <p> 注：服务端提供的资源入口方法需包含 GET 注解。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class GetFleaRequest extends FleaRequest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(GetFleaRequest.class);

    /**
     * 默认的构造方法
     *
     * @since 1.0.0
     */
    public GetFleaRequest() {
    }

    /**
     * 带请求配置参数的构造方法
     *
     * @param config 请求配置
     * @since 1.0.0
     */
    public GetFleaRequest(RequestConfig config) {
        super(config);
    }

    @Override
    protected void init() {
        modeEnum = RequestModeEnum.GET;
    }

    @Override
    protected FleaJerseyResponse request(WebTarget target, FleaJerseyRequest request) throws CommonException {

        // 将请求报文转换成请求数据字符串
        String requestData = toRequestData(request);

        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "GET Request, Start");
            LOGGER.debug1(obj, "GET Request, RequestData = {}", requestData);
        }

        // GET请求发送
        FleaJerseyResponse response = target
                .queryParam(FleaJerseyConstants.FormDataConstants.FORM_DATA_KEY_REQUEST, requestData)
                .request(toMediaType())
                .get(FleaJerseyResponse.class);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "GET Request, FleaJerseyResponse = {}", response);
            LOGGER.debug1(obj, "GET Request, End");
        }

        return response;
    }
}
