package com.huazie.frame.jersey.client.request;

/**
 * <p> 请求配置枚举 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum RequestConfigEnum {

    CLIENT_CODE("client_code", "资源服务客户端编码"),
    RESOURCE_URL("resource_url", "Jersey WS资源地址"),
    RESOURCE_CODE("resource_code", "资源编码"),
    SERVICE_CODE("service_code", "服务编码"),
    REQUEST_MODE("request_mode", "请求方式"),
    MEDIA9_TYPE("media_type", "媒体类型"),
    INPUT_OBJECT("input_object", "业务入参实例对象"),
    CLIENT_INPUT("client_input", "业务入参完整类名字符串"),
    CLIENT_OUTPUT("client_output", "业务出参完整类名字符串");

    private String key; // 请求配置键

    private String desc; // 请求配置描述

    RequestConfigEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
