package com.huazie.frame.jersey.api.filter;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ReflectUtils;
import com.huazie.frame.jersey.api.data.FleaJerseyRequest;
import com.huazie.frame.jersey.api.data.FleaJerseyResponse;
import com.huazie.frame.jersey.api.filter.config.Filter;
import com.huazie.frame.jersey.api.filter.config.FleaJerseyFilterConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> 过滤器链管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FilterChainManager {

    private static volatile FilterChainManager manager;

    private FleaJerseyFilterChain filterChain;

    private FilterChainManager() {
    }

    /**
     * <p> 获取过滤器链管理类 </p>
     *
     * @return 过滤器链管理类对象
     * @since 1.0.0
     */
    public static FilterChainManager getManager() {
        if (ObjectUtils.isEmpty(manager)) {
            synchronized (FilterChainManager.class) {
                if (ObjectUtils.isEmpty(manager)) {
                    manager = new FilterChainManager();
                    initFilterChain();
                }
            }
        }
        return manager;
    }

    private static void initFilterChain() {
        manager.filterChain = new FleaJerseyFilterChain();
        // 获取Flea Jersey 过滤器链配置对象
        FleaJerseyFilterConfig config = FleaJerseyFilterConfig.getConfig();
        // 设置前置过滤器
        manager.filterChain.setBeforeFilters(convert(config.getBeforeFilters()));
        // 设置业务服务过滤器
        manager.filterChain.setServiceFilters(convert(config.getServiceFilters()));
        // 设置后置过滤器
        manager.filterChain.setAfterFilters(convert(config.getAfterFilters()));
        // 设置异常过滤器
        manager.filterChain.setErrorFilters(convertError(config.getErrorFilters()));
    }

    /**
     * <p> 过滤器链配置集合 转化成对应过滤器链实现类集合 </p>
     *
     * @param filters 过滤器链配置
     * @return 过滤器链实现类集合
     * @since 1.0.0
     */
    private static List<IFleaJerseyFilter> convert(List<Filter> filters) {
        List<IFleaJerseyFilter> jerseyFilters = null;
        if (CollectionUtils.isNotEmpty(filters)) {
            jerseyFilters = new ArrayList<IFleaJerseyFilter>();
            for (Filter filter : filters) {
                if (ObjectUtils.isNotEmpty(filter)) {
                    String className = filter.getClazz();
                    IFleaJerseyFilter jerseyFilter = (IFleaJerseyFilter) ReflectUtils.newInstance(className);
                    if (ObjectUtils.isNotEmpty(jerseyFilter)) {
                        jerseyFilters.add(jerseyFilter);
                    }
                }
            }
        }
        return jerseyFilters;
    }

    /**
     * <p> 异常过滤器链配置集合 转化成对应异常过滤器链实现类集合 </p>
     *
     * @param filters 异常过滤器链配置
     * @return 异常过滤器链实现类集合
     * @since 1.0.0
     */
    private static List<IFleaJerseyErrorFilter> convertError(List<Filter> filters) {
        List<IFleaJerseyErrorFilter> jerseyErrorFilters = null;
        if (CollectionUtils.isNotEmpty(filters)) {
            jerseyErrorFilters = new ArrayList<IFleaJerseyErrorFilter>();
            for (Filter filter : filters) {
                if (ObjectUtils.isNotEmpty(filter)) {
                    String className = filter.getClazz();
                    IFleaJerseyErrorFilter jerseyErrorFilter = (IFleaJerseyErrorFilter) ReflectUtils.newInstance(className);
                    if (ObjectUtils.isNotEmpty(jerseyErrorFilter)) {
                        jerseyErrorFilters.add(jerseyErrorFilter);
                    }
                }
            }
        }
        return jerseyErrorFilters;
    }


    /**
     * <p> 执行过滤器 </p>
     *
     * @param request 请求对象
     * @since 1.0.0
     */
    public FleaJerseyResponse doFilter(FleaJerseyRequest request) {
        return filterChain.doFilter(request);
    }

}
