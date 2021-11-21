package com.huazie.fleaframework.core.request.config;

import com.huazie.fleaframework.core.request.FleaRequestXmlDigesterHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p> Flea Request 配置类 （包含 对 flea-request.xml 和 flea-request-filter.xml 的解析）  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaRequestConfig {

    /**
     * <p> 获取Flea Session </p>
     *
     * @return Flea Session
     * @since 1.0.0
     */
    public static FleaSession getFleaSession() {
        return getFleaRequest().getFleaSession();
    }

    /**
     * <p> 获取Flea Url </p>
     *
     * @return Flea Url
     * @since 1.0.0
     */
    public static FleaUrl getFleaUrl() {
        return getFleaRequest().getFleaUrl();
    }

    private static FleaRequest getFleaRequest() {
        return FleaRequestXmlDigesterHelper.getInstance().getFleaRequest();
    }

    /**
     * <p> 获取过滤器任务列表 </p>
     *
     * @return 过滤器任务列表
     * @since 1.0.0
     */
    public static List<FilterTask> getFilterTasks() {
        return sort(getFilterTaskChain().getFilterTasks().getFilterTaskList());
    }

    private static FilterTaskChain getFilterTaskChain() {
        return FleaRequestXmlDigesterHelper.getInstance().getFleaRequestFilter().getFilterTaskChain();
    }

    /**
     * <p> 重新排序 filterTaskList， 依据 Filter 中的 order 值 </p>
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
