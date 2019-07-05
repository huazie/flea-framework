package com.huazie.frame.jersey.client.request.impl;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.jersey.client.request.Request;
import com.huazie.frame.jersey.client.request.RequestConfig;
import com.huazie.frame.jersey.client.response.ResponseResult;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.common.exception.FleaJerseyClientException;

import javax.ws.rs.client.WebTarget;

/**
 * <p> Flea 抽象请求 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaRequest<T> implements Request {

    protected WebTarget target;

    protected FleaJerseyRequest fleaJerseyRequest;

    private RequestConfig config; // 请求配置

    public FleaRequest() {
    }

    /**
     * <p> 带请求配置参数的构造的方法 </p>
     *
     * @param config 请求配置
     * @since 1.0.0
     */
    public FleaRequest(RequestConfig config) {
        this.config = config;
    }

    @Override
    public ResponseResult<T> doRequest() throws Exception {

        if (ObjectUtils.isEmpty(config) || config.isEmpty()) {
            // 未初始化请求配置，请检查！
            throw new FleaJerseyClientException("");
        }

        // 获取 客户端编码
        String clientCode = config.getClientCode();
        if (StringUtils.isBlank(clientCode)) {
            // 客户端编码不能为空
            throw new FleaJerseyClientException("ERROR-JERSEY-CLIENT0000000001");
        }

        // 获取 业务入参
        Object input = config.getInputObj();
        if (ObjectUtils.isEmpty(input)) {
            // 业务入参不能为空
            throw new FleaJerseyClientException("ERROR-JERSEY-CLIENT0000000002");
        }

        // 根据 客户端编码 获取 flea jersey 客户端配置
        // 资源地址
        String resourceUrl = "http://localhost:8080/fleafs";
        // 资源编码
        String resourceCode = "upload";
        // 服务编码
        String serviceCode = "FLEA_SERVICE_UPLOAD_AUTH";

        String inputParam = "com.huazie.ffs.pojo.upload.input.InputUploadAuthInfo";
        String outputParam = "com.huazie.ffs.pojo.upload.output.OutputUploadAuthInfo";




        return null;
    }

    protected abstract FleaJerseyResponse request() throws Exception;

    public RequestConfig getConfig() {
        return config;
    }

    public void setConfig(RequestConfig config) {
        this.config = config;
    }
}
