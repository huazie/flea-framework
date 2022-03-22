package com.huazie.fleaframework.core.request.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 过滤器任务，在配置文件 <b>flea-request-filter.xml</b>
 * 中查看 {@code <filter-task>} 节点。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FilterTask {

    private String clazz; // 过滤器任务实现类

    private int order; // 过滤器任务执行顺序

    private String desc; // 过滤器任务描述

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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
