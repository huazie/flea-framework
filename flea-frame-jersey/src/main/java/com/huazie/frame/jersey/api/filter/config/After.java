package com.huazie.frame.jersey.api.filter.config;

/**
 * <p> flea-jersey-filter.xml 配置 {@code <after> </after>} 后置过滤器链节点 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class After extends Filters {
    /**
     * <p> 添加一个后置过滤器配置对象 </p>
     *
     * @param filter 过滤器配置对象
     * @since 1.0.0
     */
    public void addAfterFilter(Filter filter) {
        addFilter(filter);
    }
}
