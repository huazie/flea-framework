package com.huazie.fleaframework.core.request.config;

import com.huazie.fleaframework.core.request.FleaRequestXmlDigesterHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Flea Request 配置类，其中包含了对 <b>flea-request.xml</b> 
 * 和 <b>flea-request-filter.xml</b> 的解析和读取能力。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaRequestConfig {

    /**
     * 获取 Flea Session，可从配置文件 <b>flea-request.xml</b>
     * 中查看 {@code <flea-session> } 节点。
     *
     * @return Flea Session
     * @since 1.0.0
     */
    public static FleaSession getFleaSession() {
        return getFleaRequest().getFleaSession();
    }

    /**
     * 获取 Flea Url，可从配置文件 <b>flea-request.xml</b>
     * 中查看 {@code <flea-url> } 节点。
     *
     * @return Flea Url
     * @since 1.0.0
     */
    public static FleaUrl getFleaUrl() {
        return getFleaRequest().getFleaUrl();
    }

    /**
     * 获取 Flea Request，可从配置文件 <b>flea-request.xml</b>
     * 中查看 {@code <flea-request> } 节点。
     *
     * @return Flea Request
     * @since 1.0.0
     */
    private static FleaRequest getFleaRequest() {
        return FleaRequestXmlDigesterHelper.getInstance().getFleaRequest();
    }

    /**
     * 获取过滤器任务列表，可从配置文件 <b>flea-request-filter.xml</b>
     * 中查看 {@code <filter-tasks>} 节点。
     *
     * @return 过滤器任务列表
     * @since 1.0.0
     */
    public static List<FilterTask> getFilterTasks() {
        return sort(getFilterTaskChain().getFilterTasks().getFilterTaskList());
    }

    /**
     * 获取过滤器任务链，可从配置文件 <b>flea-request-filter.xml</b>
     * 中查看 {@code <filter-task-chain> } 节点。
     *
     * @return 过滤器任务链
     * @since 1.0.0
     */
    private static FilterTaskChain getFilterTaskChain() {
        return FleaRequestXmlDigesterHelper.getInstance().getFleaRequestFilter().getFilterTaskChain();
    }

    /**
     * 重新排序 filterTaskList， 依据 Filter 中的 order 值
     *
     * @param filterTaskList 过滤器链
     * @since 1.0.0
     */
    private static List<FilterTask> sort(List<FilterTask> filterTaskList) {
        Collections.sort(filterTaskList, new Comparator<FilterTask>() {
            @Override
            public int compare(FilterTask o1, FilterTask o2) {
                // 按照 order 升序
                return o1.getOrder() - o2.getOrder();
            }
        });
        return filterTaskList;
    }

}
