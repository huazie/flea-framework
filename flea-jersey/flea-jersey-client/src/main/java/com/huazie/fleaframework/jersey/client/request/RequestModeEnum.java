package com.huazie.fleaframework.jersey.client.request;

/**
 * 请求方式枚举，每一类请求方式对应一种 Flea Jersey 请求。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum RequestModeEnum {

    GET("GET", "com.huazie.fleaframework.jersey.client.request.impl.GetFleaRequest", "GET请求"),
    FGET("FGET", "com.huazie.fleaframework.jersey.client.request.impl.FGetFleaRequest", "文件GET请求"),
    POST("POST", "com.huazie.fleaframework.jersey.client.request.impl.PostFleaRequest", "POST请求"),
    FPOST("FPOST", "com.huazie.fleaframework.jersey.client.request.impl.FPostFleaRequest", "文件POST请求"),
    PUT("PUT", "com.huazie.fleaframework.jersey.client.request.impl.PutFleaRequest", "PUT请求"),
    DELETE("DELETE", "com.huazie.fleaframework.jersey.client.request.impl.DeleteFleaRequest", "DELETE请求");

    private String mode; // 请求方式

    private String implClass; // 请求实现类

    private String desc; // 请求方式描述

    RequestModeEnum(String mode, String implClass, String desc) {
        this.mode = mode;
        this.implClass = implClass;
        this.desc = desc;
    }

    public String getMode() {
        return mode;
    }

    public String getImplClass() {
        return implClass;
    }

    public String getDesc() {
        return desc;
    }
}
