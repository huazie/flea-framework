package com.huazie.fleaframework.jersey.client.request;

import com.huazie.fleaframework.common.FleaCommonConfig;
import com.huazie.fleaframework.common.util.StringUtils;

/**
 * 请求配置类，为构建不同请求对象提供数据支撑。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RequestConfig extends FleaCommonConfig {

    /**
     * 添加 客户端编码 配置
     *
     * @param clientCode 客户端编码
     * @since 1.0.0
     */
    public void addClientCode(String clientCode) {
        if (StringUtils.isNotBlank(clientCode)) {
            put(RequestConfigEnum.CLIENT_CODE.getKey(), clientCode);
        }
    }

    /**
     * 获取 客户端编码 配置</p>
     *
     * @return 客户端编码
     * @since 1.0.0
     */
    public String getClientCode() {
        return get(RequestConfigEnum.CLIENT_CODE.getKey(), String.class);
    }

    /**
     * 添加 资源地址 配置
     *
     * @param resourceUrl 资源地址
     * @since 1.0.0
     */
    public void addResourceUrl(String resourceUrl) {
        if (StringUtils.isNotBlank(resourceUrl)) {
            put(RequestConfigEnum.RESOURCE_URL.getKey(), resourceUrl);
        }
    }

    /**
     * 获取 资源地址 配置
     *
     * @return 资源地址
     * @since 1.0.0
     */
    public String getResourceUrl() {
        return get(RequestConfigEnum.RESOURCE_URL.getKey(), String.class);
    }

    /**
     * 添加 资源编码 配置
     *
     * @param resourceCode 资源编码
     * @since 1.0.0
     */
    public void addResourceCode(String resourceCode) {
        if (StringUtils.isNotBlank(resourceCode)) {
            put(RequestConfigEnum.RESOURCE_CODE.getKey(), resourceCode);
        }
    }

    /**
     * 获取 资源编码 配置
     *
     * @return 资源编码
     * @since 1.0.0
     */
    public String getResourceCode() {
        return get(RequestConfigEnum.RESOURCE_CODE.getKey(), String.class);
    }

    /**
     * 添加 服务编码 配置
     *
     * @param serviceCode 服务编码
     * @since 1.0.0
     */
    public void addServiceCode(String serviceCode) {
        if (StringUtils.isNotBlank(serviceCode)) {
            put(RequestConfigEnum.SERVICE_CODE.getKey(), serviceCode);
        }
    }

    /**
     * 获取 服务编码 配置
     *
     * @return 服务编码
     * @since 1.0.0
     */
    public String getServiceCode() {
        return get(RequestConfigEnum.SERVICE_CODE.getKey(), String.class);
    }

    /**
     * 添加 请求方式 配置</p>
     *
     * @param requestMode 请求方式
     * @since 1.0.0
     */
    public void addRequestMode(String requestMode) {
        if (StringUtils.isNotBlank(requestMode)) {
            put(RequestConfigEnum.REQUEST_MODE.getKey(), requestMode);
        }
    }

    /**
     * 获取 请求方式 配置</p>
     *
     * @return 请求方式
     * @since 1.0.0
     */
    public String getRequestMode() {
        return get(RequestConfigEnum.REQUEST_MODE.getKey(), String.class);
    }

    /**
     * 添加 媒体类型 配置
     *
     * @param mediaType 媒体类型字符串
     * @since 1.0.0
     */
    public void addMediaType(String mediaType) {
        if (StringUtils.isNotBlank(mediaType)) {
            put(RequestConfigEnum.MEDIA_TYPE.getKey(), mediaType);
        }
    }

    /**
     * 获取 媒体类型 配置
     *
     * @return 媒体类型
     * @since 1.0.0
     */
    public String getMediaType() {
        return get(RequestConfigEnum.MEDIA_TYPE.getKey(), String.class);
    }

    /**
     * 添加 业务入参实例对象 配置
     *
     * @param input 业务入参实例对象
     * @since 1.0.0
     */
    public void addInputObj(Object input) {
        put(RequestConfigEnum.INPUT_OBJECT.getKey(), input);
    }

    /**
     * 获取 业务入参实例对象 配置
     *
     * @return 业务入参实例对象
     * @since 1.0.0
     */
    public Object getInputObj() {
        return get(RequestConfigEnum.INPUT_OBJECT.getKey());
    }

    /**
     * 添加 客户端业务入参 配置</p>
     *
     * @param clientInput 客户端业务入参
     * @since 1.0.0
     */
    public void addClientInput(String clientInput) {
        put(RequestConfigEnum.CLIENT_INPUT.getKey(), clientInput);
    }

    /**
     * 获取 客户端业务入参 配置</p>
     *
     * @return 客户端业务入参
     * @since 1.0.0
     */
    public String getClientInput() {
        return get(RequestConfigEnum.CLIENT_INPUT.getKey(), String.class);
    }

    /**
     * 添加 客户端业务出参 配置</p>
     *
     * @param clientOutput 客户端业务出参
     * @since 1.0.0
     */
    public void addClientOutput(String clientOutput) {
        put(RequestConfigEnum.CLIENT_OUTPUT.getKey(), clientOutput);
    }

    /**
     * 获取 客户端业务出参 配置</p>
     *
     * @return 客户端业务出参
     * @since 1.0.0
     */
    public String getClientOutput() {
        return get(RequestConfigEnum.CLIENT_OUTPUT.getKey(), String.class);
    }

}
