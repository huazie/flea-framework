package com.huazie.fleaframework.jersey.server.filter;

import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;

/**
 * Flea Jersey 接口过滤器链管理类，采用单例模式，其私有构造方法中
 * 接收一个Flea Jersey 接口过滤器链的实例对象。
 *
 * <p> 它对外提供 {@code doFilter} 重载方法，用于执行过滤器。
 *
 * @author huazie
 * @version 1.0.0
 * @see FleaJerseyFilterChain
 * @since 1.0.0
 */
public class FleaJerseyFilterChainManager {

    private static volatile FleaJerseyFilterChainManager manager;

    private FleaJerseyFilterChain filterChain;

    private FleaJerseyFilterChainManager(FleaJerseyFilterChain filterChain) {
        this.filterChain = filterChain;
    }

    /**
     * 获取过滤器链管理类
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
     * 执行过滤器
     *
     * @param request 请求对象
     * @return 响应对象
     * @since 1.0.0
     */
    public FleaJerseyResponse doFilter(FleaJerseyRequest request) {
        return filterChain.doFilter(request);
    }

    /**
     * 执行过滤器
     *
     * @param requestData 请求数据字符串
     * @return 响应对象
     * @since 1.0.0
     */
    public FleaJerseyResponse doFilter(String requestData) {
        return filterChain.doFilter(requestData);
    }

}
