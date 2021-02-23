package com.huazie.frame.jersey.client.core;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResClient;
import com.huazie.frame.jersey.client.request.Request;
import com.huazie.frame.jersey.client.request.RequestConfig;
import com.huazie.frame.jersey.client.request.RequestFactory;
import com.huazie.frame.jersey.client.response.Response;
import com.huazie.frame.jersey.common.exception.FleaJerseyClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p> Flea Jersey 客户端 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class FleaJerseyClient {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJerseyClient.class);

    private FleaConfigDataSpringBean springBean;

    @Autowired
    public void setSpringBean(FleaConfigDataSpringBean springBean) {
        this.springBean = springBean;
    }

    /**
     * <p> Flea Jersey客户端调用 </p>
     *
     * @param clientCode  客户端编码
     * @param input       业务入参
     * @param outputClazz 业务出参类对象
     * @param <T>         业务出参
     * @return 响应结果
     * @throws Exception
     * @since 1.0.0
     */
    public <T> Response<T> invoke(String clientCode, Object input, Class<T> outputClazz) throws Exception {

        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "Start");
        }

        RequestConfig config = new RequestConfig();

        // 客户端编码不能为空
        StringUtils.checkBlank(clientCode, FleaJerseyClientException.class, "ERROR-JERSEY-CLIENT0000000001");

        // 业务入参不能为空
        ObjectUtils.checkEmpty(input, FleaJerseyClientException.class, "ERROR-JERSEY-CLIENT0000000002");

        // 业务出参类不能为空
        ObjectUtils.checkEmpty(outputClazz, FleaJerseyClientException.class, "ERROR-JERSEY-CLIENT0000000003");

        // 未注入Bean，直接返回null
        if (ObjectUtils.isEmpty(springBean)) {
            return null;
        }

        // 获取Jersey客户端配置
        FleaJerseyResClient resClient = springBean.getResClient(clientCode);
        // 请检查客户端配置【client_code = {0}】：资源服务客户端未配置
        ObjectUtils.checkEmpty(resClient, FleaJerseyClientException.class, "ERROR-JERSEY-CLIENT0000000009", clientCode);

        // 客户端编码
        config.addClientCode(clientCode);
        // 业务入参对象
        config.addInputObj(input);
        // 资源地址
        config.addResourceUrl(resClient.getResourceUrl());
        // 资源编码
        config.addResourceCode(resClient.getResourceCode());
        // 服务编码
        config.addServiceCode(resClient.getServiceCode());
        // 请求方式
        config.addRequestMode(resClient.getRequestMode());
        // 媒体类型
        config.addMediaType(resClient.getMediaType());
        // 业务入参类全名字符串
        config.addClientInput(resClient.getClientInput());
        // 业务出参类全名字符串
        config.addClientOutput(resClient.getClientOutput());

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "Config = {}", config);
        }

        // 传入请求配置，让请求工厂生产一个Flea Jersey请求
        Request request = RequestFactory.getInstance().buildFleaRequest(config);
        Response<T> response = null;
        if (ObjectUtils.isNotEmpty(request)) {
            response = request.doRequest(outputClazz);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "Response = {}", response);
            LOGGER.debug1(obj, "End");
        }

        return response;
    }

}
