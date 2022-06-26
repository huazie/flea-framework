package com.huazie.fleaframework.jersey.common.filter.config;

/**
 * 异常过滤器链，可从配置文件【flea-jersey-filter.xml】
 * 中查看 {@code <error> </error>} 节点
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Error extends Filters {
    /**
     * 添加一个异常过滤器配置对象
     *
     * @param filter 过滤器配置对象
     * @since 1.0.0
     */
    public void addErrorFilter(Filter filter) {
        addFilter(filter);
    }
}
