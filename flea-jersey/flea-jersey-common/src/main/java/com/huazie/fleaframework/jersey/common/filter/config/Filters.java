package com.huazie.fleaframework.jersey.common.filter.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 过滤器链配置对象 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Filters {

    private List<Filter> filters = new ArrayList<>();

    /**
     * <p> 获取过滤器链 </p>
     *
     * @return 过滤器链
     * @since 1.0.0
     */
    public List<Filter> getFilters() {
        return filters;
    }

    /**
     * <p> 获取过滤器链 </p>
     *
     * @return 过滤器链
     * @since 1.0.0
     */
    public Filter[] toFiltersArray() {
        return filters.toArray(new Filter[0]);
    }

    /**
     * <p> 添加一个过滤器配置对象 </p>
     *
     * @param filter 过滤器配置对象
     * @since 1.0.0
     */
    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
