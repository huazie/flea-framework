package com.huazie.fleaframework.jersey.common.filter.config;

import com.huazie.fleaframework.jersey.common.JerseyXmlDigesterHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p> Flea Jersey Filter 配置类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyFilterConfig {

    /**
     * <p> 获取前置过滤器链 </p>
     *
     * @return 前置过滤器链
     * @since 1.0.0
     */
    public static List<Filter> getBeforeFilters() {
        return sort(getFilterChain().getBefore().getFilters());
    }

    /**
     * <p> 获取业务服务过滤器链 </p>
     *
     * @return 业务服务过滤器链
     * @since 1.0.0
     */
    public static List<Filter> getServiceFilters() {
        return sort(getFilterChain().getService().getFilters());
    }

    /**
     * <p> 获取后置过滤器链 </p>
     *
     * @return 后置过滤器链
     * @since 1.0.0
     */
    public static List<Filter> getAfterFilters() {
        return sort(getFilterChain().getAfter().getFilters());
    }

    /**
     * <p> 获取异常过滤器链 </p>
     *
     * @return 异常过滤器链
     * @since 1.0.0
     */
    public static List<Filter> getErrorFilters() {
        return sort(getFilterChain().getError().getFilters());
    }

    private static FilterChain getFilterChain() {
        return JerseyXmlDigesterHelper.getInstance().getJersey().getFilterChain();
    }

    /**
     * <p> 重新排序 filters， 依据 Filter 中的 order 值 </p>
     *
     * @param filters 过滤器链
     * @since 1.0.0
     */
    private static List<Filter> sort(List<Filter> filters) {
        Collections.sort(filters, new Comparator<Filter>() {
            @Override
            public int compare(Filter o1, Filter o2) {
                // 按照 order 升序
                return o1.getOrder() - o2.getOrder();
            }
        });
        return filters;
    }

}
