package com.huazie.fleaframework.cache.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 缓存数据，对应【flea-cache-config.xml】中
 * 【{@code <cache-data type="" desc="">缓存组名</cache-data>}】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheData {

    private String type; // 缓存数据类型

    private String desc; // 缓存数据描述

    private String group; // 缓存数据组

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
