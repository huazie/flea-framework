package com.huazie.frame.jersey.server.filter;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;

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

    private FilterChainManager(FleaJerseyFilterChain filterChain) {
        this.filterChain = filterChain;
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
                    manager = new FilterChainManager(new FleaJerseyFilterChain());
                }
            }
        }
        return manager;
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
