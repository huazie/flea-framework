package com.huazie.fleaframework.jersey.common.filter.config;

/**
 * <p> flea-jersey-filter.xml 配置 {@code <before> </before>} 前置过滤器链节点 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Before extends Filters {
    /**
     * <p> 添加一个前置过滤器配置对象 </p>
     *
     * @param filter 过滤器配置对象
     * @since 1.0.0
     */
    public void addBeforeFilter(Filter filter) {
        addFilter(filter);
    }
}
