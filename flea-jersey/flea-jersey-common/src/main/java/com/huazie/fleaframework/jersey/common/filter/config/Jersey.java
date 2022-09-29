package com.huazie.fleaframework.jersey.common.filter.config;

import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.xml.ImportList;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea Jersey接口配置对象，可从配置文件【flea-jersey-filter.xml】
 * 中查看 {@code <jersey> </jersey>} 节点
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Jersey extends ImportList {

    private FilterChain filterChain; // 过滤器链对象

    private FilterI18nError filterI18nError; // 过滤器国际码和错误码配置对象

    public FilterChain getFilterChain() {
        if (ObjectUtils.isEmpty(filterChain))
            filterChain = new FilterChain();
        return filterChain;
    }

    public void setFilterChain(FilterChain filterChain) {
        this.filterChain = filterChain;
    }

    public FilterI18nError getFilterI18nError() {
        if (ObjectUtils.isEmpty(filterI18nError))
            filterI18nError = new FilterI18nError();
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
