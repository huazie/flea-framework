package com.huazie.fleaframework.common.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> 配置Key </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConfigKey {

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
