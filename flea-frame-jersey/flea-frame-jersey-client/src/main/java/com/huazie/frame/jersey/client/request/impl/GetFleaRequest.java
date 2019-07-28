package com.huazie.frame.jersey.client.request.impl;

import com.huazie.frame.jersey.client.request.RequestConfig;
import com.huazie.frame.jersey.client.request.RequestModeEnum;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.WebTarget;

/**
 * <p> GET请求 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class GetFleaRequest extends FleaRequest {

    private final static Logger LOGGER = LoggerFactory.getLogger(GetFleaRequest.class);

    /**
     * <p> 不带参数的构造方法 </p>
     *
     * @since 1.0.0
     */
    public GetFleaRequest() {
        super();
    }

    /**
     * <p> 带参数的构造方法 </p>
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
    protected FleaJerseyResponse request(WebTarget target, FleaJerseyRequest request) throws Exception {

        // 将请求报文转换成xml
        String requestXml = toRequestXml(request);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GetFleaRequest##request(WebTarget, FleaJerseyRequest) Start");
            LOGGER.debug("GetFleaRequest##request(WebTarget, FleaJerseyRequest) RequestXml = {}", requestXml);
        }

        FleaJerseyResponse response = target.queryParam("REQUEST", requestXml).request(toMediaType()).get(FleaJerseyResponse.class);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("GetFleaRequest##request(WebTarget, FleaJerseyRequest) FleaJerseyResponse = {}", response);
            LOGGER.debug("GetFleaRequest##request(WebTarget, FleaJerseyRequest) End");
        }

        return response;
    }
}
