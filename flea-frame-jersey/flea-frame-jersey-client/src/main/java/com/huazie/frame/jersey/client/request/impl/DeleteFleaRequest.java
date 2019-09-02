package com.huazie.frame.jersey.client.request.impl;

import com.huazie.frame.jersey.client.request.RequestConfig;
import com.huazie.frame.jersey.client.request.RequestModeEnum;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.WebTarget;

/**
 * <p> DELETE请求 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class DeleteFleaRequest extends FleaRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteFleaRequest.class);

    /**
     * <p> 不带参数的构造方法 </p>
     *
     * @since 1.0.0
     */
    public DeleteFleaRequest() {
    }

    /**
     * <p> 带参数的构造方法 </p>
     *
     * @param config 请求配置
     * @since 1.0.0
     */
    public DeleteFleaRequest(RequestConfig config) {
        super(config);
    }

    @Override
    protected void init() {
        modeEnum = RequestModeEnum.DELETE;
    }

    @Override
    protected FleaJerseyResponse request(WebTarget target, FleaJerseyRequest request) throws Exception {

        // 将请求报文转换成xml
        String requestXml = toRequestXml(request);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DeleteFleaRequest##request(WebTarget, FleaJerseyRequest) Start");
            LOGGER.debug("DeleteFleaRequest##request(WebTarget, FleaJerseyRequest) RequestXml = {}", requestXml);
        }

        FleaJerseyResponse response = target.queryParam("REQUEST", requestXml).request(toMediaType()).delete(FleaJerseyResponse.class);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DeleteFleaRequest##request(WebTarget, FleaJerseyRequest) FleaJerseyResponse = {}", response);
            LOGGER.debug("DeleteFleaRequest##request(WebTarget, FleaJerseyRequest) End");
        }

        return response;
    }
}
