package com.huazie.fleaframework.core.request.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器任务集，在配置文件 <b>flea-request-filter.xml</b>
 * 中查看 {@code <filter-tasks>} 节点。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FilterTasks {

    private List<FilterTask> filterTaskList = new ArrayList<>(); // 过滤器任务列表

    public List<FilterTask> getFilterTaskList() {
        return filterTaskList;
    }

    /**
     * <p> 添加一个过滤器任务 </p>
     *
     * @param filterTask 过滤器任务
     * @since 1.0.0
     */
    public void addFilterTask(FilterTask filterTask) {
        filterTaskList.add(filterTask);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
