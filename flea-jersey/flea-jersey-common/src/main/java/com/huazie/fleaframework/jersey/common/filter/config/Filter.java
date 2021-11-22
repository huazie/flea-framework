package com.huazie.fleaframework.jersey.common.filter.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> flea-jersey-filter.xml 配置 {@code <filter> </filter>} 过滤器节点 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Filter {

    private String clazz; // 过滤器实现类全路径

    private int order;    // 过滤器执行顺序

    private String desc;  // 过滤器定义描述

    public String getClazz() {
        return this.clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
