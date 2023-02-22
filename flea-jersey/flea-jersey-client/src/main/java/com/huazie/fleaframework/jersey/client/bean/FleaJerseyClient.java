package com.huazie.fleaframework.jersey.client.bean;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResClient;
import com.huazie.fleaframework.jersey.client.request.Request;
import com.huazie.fleaframework.jersey.client.request.RequestConfig;
import com.huazie.fleaframework.jersey.client.request.RequestFactory;
import com.huazie.fleaframework.jersey.client.response.Response;
import com.huazie.fleaframework.jersey.common.exception.FleaJerseyClientException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Flea Jersey 客户端，对外提供统一的Jersey接口客户端调用API。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class FleaJerseyClient {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJerseyClient.class);

    private FleaConfigDataSpringBean springBean;

    @Resource
    public void setSpringBean(FleaConfigDataSpringBean springBean) {
        this.springBean = springBean;
    }

    /**
     * Flea Jersey 接口客户端调用，相关实现逻辑如下：
     *
     * 首先根据客户端编码获取 Flea Jersey 接口客户端配置，
     * 然后根据 Flea Jersey 接口客户端配置构建通用的请求配置，
     * 接着传入请求配置，让请求工厂生产一个 Flea Jersey 请求，
     * 最后执行 Flea Jersey 请求。
     *
     * @param clientCode  客户端编码
     * @param input       业务入参
     * @param outputClazz 业务出参类对象
     * @param <T>         业务出参
     * @return 响应结果
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public <T> Response<T> invoke(String clientCode, Object input, Class<T> outputClazz) throws CommonException {

        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "Start");
        }

        // 客户端编码不能为空
        StringUtils.checkBlank(clientCode, FleaJerseyClientException.class, "ERROR-JERSEY-CLIENT0000000001");

        // 业务入参不能为空
        ObjectUtils.checkEmpty(input, FleaJerseyClientException.class, "ERROR-JERSEY-CLIENT0000000002");

        // 业务出参类不能为空
        ObjectUtils.checkEmpty(outputClazz, FleaJerseyClientException.class, "ERROR-JERSEY-CLIENT0000000003");

        // 未注入Bean，直接返回null
        if (ObjectUtils.isEmpty(springBean)) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.debug1(new Object() {}, "未注入配置数据 Spring Bean，请检查");
            }
            return null;
        }

        // 获取Jersey客户端配置
        FleaJerseyResClient resClient = springBean.getResClient(clientCode);
        // 请检查客户端配置【client_code = {0}】：资源服务客户端未配置
        ObjectUtils.checkEmpty(resClient, FleaJerseyClientException.class, "ERROR-JERSEY-CLIENT0000000009", clientCode);

        RequestConfig config = new RequestConfig();
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
            LOGGER.debug1(obj, "Request Config = {}", config);
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
