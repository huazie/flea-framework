package com.huazie.frame.common.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> 配置项 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConfigItem {

    private String key; // 配置项键

    private String desc; // 配置项描述

    private String value; // 配置项值

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
