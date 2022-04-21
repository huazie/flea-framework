package com.huazie.fleaframework.common.config;

/**
 * 配置键 Map
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class ConfigKeyMap<T> extends ConfigMap<T> {

    private String key; // 配置键

    private String desc; // 配置描述

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
