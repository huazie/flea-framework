package com.huazie.fleaframework.cache.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 缓存组，对应【flea-cache-config.xml】中
 * 【{@code <cache-group group="" desc="">缓存实现</cache-group>}】
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class CacheGroup {

    private String group; // 缓存组名

    private String desc; // 缓存组描述

    private String cache; // 缓存实现

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
