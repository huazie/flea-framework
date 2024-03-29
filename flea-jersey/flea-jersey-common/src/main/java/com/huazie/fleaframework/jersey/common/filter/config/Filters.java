package com.huazie.fleaframework.jersey.common.filter.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器链配置对象
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Filters {

    private List<Filter> filters = new ArrayList<>();

    /**
     * 获取过滤器链
     *
     * @return 过滤器链
     * @since 1.0.0
     */
    public List<Filter> getFilters() {
        return this.filters;
    }

    /**
     * 获取过滤器链
     *
     * @return 过滤器链
     * @since 1.0.0
     */
    public Filter[] toFiltersArray() {
        return this.filters.toArray(new Filter[0]);
    }

    /**
     * 添加多个过滤器配置对象
     *
     * @param filters 过滤器配置对象集
     * @since 2.0.0
     */
    public void addFilters(List<Filter> filters) {
        this.filters.addAll(filters);
    }

    /**
     * 添加一个过滤器配置对象
     *
     * @param filter 过滤器配置对象
     * @since 1.0.0
     */
    public void addFilter(Filter filter) {
        this.filters.add(filter);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
