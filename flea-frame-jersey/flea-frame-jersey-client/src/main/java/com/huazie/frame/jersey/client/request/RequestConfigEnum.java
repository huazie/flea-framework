package com.huazie.frame.jersey.client.request;

public enum RequestConfigEnum {

    CLIENT_CODE("CLIENT_CODE", "客户端编码"),
    INPUT_OBJECT("INPUT_OBJECT", "业务入参实例对象");

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
