package com.huazie.fleaframework.jersey.common.filter.config;

/**
 * <p> flea-jersey-filter.xml 配置 {@code <service> </service>} 业务服务过滤器链节点 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Service extends Filters {
    /**
     * <p> 添加一个业务服务过滤器配置对象 </p>
     *
     * @param filter 过滤器配置对象
     * @since 1.0.0
     */
    public void addServiceFilter(Filter filter) {
        addFilter(filter);
    }
}
