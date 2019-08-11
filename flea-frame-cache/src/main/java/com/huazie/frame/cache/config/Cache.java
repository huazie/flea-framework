package com.huazie.frame.cache.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> Flea缓存 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Cache {

    private String key; // Flea缓存KEY

    private String type; // Flea缓存类型

    private String desc; // Flea缓存描述

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
