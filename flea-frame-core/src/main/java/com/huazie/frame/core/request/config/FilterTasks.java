package com.huazie.frame.core.request.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> flea-request-filter.xml 配置 {@code <filter-tasks> </filter-tasks>} 节点 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FilterTasks {

    private List<FilterTask> filterTaskList = new ArrayList<FilterTask>(); // 过滤器任务列表

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
