package com.huazie.fleaframework.cache.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 缓存定义，对应【flea-cache.xml】中
 * 【{@code <cache key="" type="" expiry="" desc="" />}】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Cache {

    private String key; // Flea缓存KEY

    private String type; // Flea缓存类型

    private String expiry; // 有效期（单位：s）

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

    public String getExpiry() {
        return this.expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
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
