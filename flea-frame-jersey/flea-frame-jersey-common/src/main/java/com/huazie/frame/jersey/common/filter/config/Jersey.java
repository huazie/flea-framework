package com.huazie.frame.jersey.common.filter.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> flea-jersey-filter.xml 配置 {@code <jersey> </jersey>} 节点 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Jersey {

    private FilterChain filterChain; // 过滤器链对象

    public FilterChain getFilterChain() {
        return filterChain;
    }

    public void setFilterChain(FilterChain filterChain) {
        this.filterChain = filterChain;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
