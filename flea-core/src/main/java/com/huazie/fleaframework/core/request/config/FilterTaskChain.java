package com.huazie.fleaframework.core.request.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> flea-request-filter.xml 配置 {@code <filter-task-chain> </filter-task-chain>} 节点 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FilterTaskChain {

    private FilterTasks filterTasks; // 过滤器任务集

    public FilterTasks getFilterTasks() {
        return filterTasks;
    }

    public void setFilterTasks(FilterTasks filterTasks) {
        this.filterTasks = filterTasks;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
