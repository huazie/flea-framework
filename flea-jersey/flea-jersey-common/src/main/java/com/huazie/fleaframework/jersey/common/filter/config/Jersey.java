package com.huazie.fleaframework.jersey.common.filter.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea Jersey接口配置对象，可从配置文件【flea-jersey-filter.xml】
 * 中查看 {@code <jersey> </jersey>} 节点
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Jersey {

    private FilterChain filterChain; // 过滤器链对象

    private FilterI18nError filterI18nError; // 过滤器国际码和错误码配置对象

    public FilterChain getFilterChain() {
        return filterChain;
    }

    public void setFilterChain(FilterChain filterChain) {
        this.filterChain = filterChain;
    }

    public FilterI18nError getFilterI18nError() {
        return filterI18nError;
    }

    public void setFilterI18nError(FilterI18nError filterI18nError) {
        this.filterI18nError = filterI18nError;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
