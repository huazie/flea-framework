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
public class FleaJerseyFilterChainManager {

    private static volatile FleaJerseyFilterChainManager manager;

    private FleaJerseyFilterChain filterChain;

    private FleaJerseyFilterChainManager(FleaJerseyFilterChain filterChain) {
        this.filterChain = filterChain;
    }

    /**
     * <p> 获取过滤器链管理类 </p>
     *
     * @return 过滤器链管理类对象
     * @since 1.0.0
     */
    public static FleaJerseyFilterChainManager getManager() {
        if (ObjectUtils.isEmpty(manager)) {
            synchronized (FleaJerseyFilterChainManager.class) {
                if (ObjectUtils.isEmpty(manager)) {
                    manager = new FleaJerseyFilterChainManager(new FleaJerseyFilterChain());
                }
            }
        }
        return manager;
    }

    /**
     * <p> 执行过滤器 </p>
     *
     * @param request 请求对象
     * @return 响应对象
     * @since 1.0.0
     */
    public FleaJerseyResponse doFilter(FleaJerseyRequest request) {
        return filterChain.doFilter(request);
    }

    /**
     * <p> 执行过滤器 </p>
     *
     * @param requestData 请求数据字符串
     * @return 响应对象
     * @since 1.0.0
     */
    public FleaJerseyResponse doFilter(String requestData) {
        return filterChain.doFilter(requestData);
    }

}
