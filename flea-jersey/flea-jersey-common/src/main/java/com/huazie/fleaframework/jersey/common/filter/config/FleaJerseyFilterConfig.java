package com.huazie.fleaframework.jersey.common.filter.config;

import com.huazie.fleaframework.jersey.common.FleaJerseyConstants.JerseyFilterConstants;
import com.huazie.fleaframework.jersey.common.JerseyXmlDigesterHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Flea Jersey Filter 配置类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyFilterConfig {

    /**
     * 设置 Jersey 过滤器链配置文件 路径
     *
     * @param path Jersey过滤器配置文件路径
     * @since 2.0.0
     */
    public static void setFilePath(String path) {
        System.setProperty(JerseyFilterConstants.JERSEY_FILTER_FILE_SYSTEM_KEY, path);
    }

    /**
     * 获取前置过滤器链
     *
     * @return 前置过滤器链
     * @since 1.0.0
     */
    public static List<Filter> getBeforeFilters() {
        return sort(getFilterChain().getBefore().getFilters());
    }

    /**
     * 获取业务服务过滤器链
     *
     * @return 业务服务过滤器链
     * @since 1.0.0
     */
    public static List<Filter> getServiceFilters() {
        return sort(getFilterChain().getService().getFilters());
    }

    /**
     * 获取后置过滤器链
     *
     * @return 后置过滤器链
     * @since 1.0.0
     */
    public static List<Filter> getAfterFilters() {
        return sort(getFilterChain().getAfter().getFilters());
    }

    /**
     * 获取异常过滤器链
     *
     * @return 异常过滤器链
     * @since 1.0.0
     */
    public static List<Filter> getErrorFilters() {
        return sort(getFilterChain().getError().getFilters());
    }

    /**
     * 获取过滤器链配置
     *
     * @return 过滤器链配置
     * @since 1.0.0
     */
    private static FilterChain getFilterChain() {
        return JerseyXmlDigesterHelper.getInstance().getJersey().getFilterChain();
    }

    /**
     * 重新排序 filters， 依据 Filter 中的 order 值
     *
     * @param filters 过滤器链
     * @since 1.0.0
     */
    private static List<Filter> sort(List<Filter> filters) {
        Collections.sort(filters, new Comparator<Filter>() {
            @Override
            public int compare(Filter o1, Filter o2) {
                // 按照 order 升序
                return Float.compare(o1.getOrder(), o2.getOrder());
            }
        });
        return filters;
    }

    /**
     * 根据国际码，获取国际码和错误码映射配置对象
     *
     * @param i18nCode 国际码
     * @return 国际码和错误码映射配置对象
     * @since 2.0.0
     */
    public static I18nErrorMapping getI18nErrorMapping(String i18nCode) {
        return getFilterI18nError().getI18nErrorMapping(i18nCode);
    }

    /**
     * 获取过滤器国际码和错误码配置
     *
     * @return 过滤器国际码和错误码配置
     * @since 2.0.0
     */
    private static FilterI18nError getFilterI18nError() {
        return JerseyXmlDigesterHelper.getInstance().getJersey().getFilterI18nError();
    }
}
