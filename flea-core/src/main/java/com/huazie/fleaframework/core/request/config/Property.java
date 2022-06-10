package com.huazie.fleaframework.core.request.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 属性定义，在配置文件 <b>flea-request.xml</b>
 * 中查看 {@code <property> } 节点。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Property {

    private String key; // 属性键

    private String desc; // 属性描述

    private String value; // 属性值

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
