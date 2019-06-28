package com.huazie.frame.jersey.common.filter.config;

/**
 * <p> flea-jersey-filter.xml 配置 {@code <error> </error>} 异常过滤器链节点 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Error extends Filters {
    /**
     * <p> 添加一个异常过滤器配置对象 </p>
     *
     * @param filter 过滤器配置对象
     * @since 1.0.0
     */
    public void addErrorFilter(Filter filter) {
        addFilter(filter);
    }
}
