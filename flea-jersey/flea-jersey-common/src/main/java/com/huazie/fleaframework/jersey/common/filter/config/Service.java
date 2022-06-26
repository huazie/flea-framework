package com.huazie.fleaframework.jersey.common.filter.config;

/**
 * 业务服务过滤器链，可从配置文件【flea-jersey-filter.xml】
 * 中查看 {@code <service> </service>} 节点
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Service extends Filters {
    /**
     * 添加一个业务服务过滤器配置对象
     *
     * @param filter 过滤器配置对象
     * @since 1.0.0
     */
    public void addServiceFilter(Filter filter) {
        addFilter(filter);
    }
}
