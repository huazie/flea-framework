package com.huazie.fleaframework.jersey.common.filter.config;

/**
 * 前置过滤器链，可从配置文件【flea-jersey-filter.xml 】
 * 中查看 {@code <before> </before>} 节点
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Before extends Filters {
    /**
     * 添加一个前置过滤器配置对象
     *
     * @param filter 过滤器配置对象
     * @since 1.0.0
     */
    public void addBeforeFilter(Filter filter) {
        addFilter(filter);
    }
}
