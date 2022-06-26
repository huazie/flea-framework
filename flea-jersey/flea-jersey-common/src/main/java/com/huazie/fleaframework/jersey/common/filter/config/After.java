package com.huazie.fleaframework.jersey.common.filter.config;

/**
 * 后置过滤器链，可从配置文件【flea-jersey-filter.xml】
 * 中查看 {@code <after> </after>} 节点
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class After extends Filters {
    /**
     * 添加一个后置过滤器配置对象
     *
     * @param filter 过滤器配置对象
     * @since 1.0.0
     */
    public void addAfterFilter(Filter filter) {
        addFilter(filter);
    }
}
