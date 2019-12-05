package com.huazie.frame.jersey.server.filter;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.jersey.common.data.FleaJerseyContext;
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

    private static ThreadLocal<FleaJerseyContext> sContext = new ThreadLocal<FleaJerseyContext>(); // Flea Jersey 上下文

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

    /**
     * <p> 设置Flea Jersey上下文对象 </p>
     *
     * @param context Flea Jersey上下文对象
     * @since 1.0.0
     */
    public void setContext(FleaJerseyContext context) {
        if (ObjectUtils.isNotEmpty(context)) {
            sContext.set(context);
        }
    }

    /**
     * <p> 获取Flea Jersey上下文对象 </p>
     *
     * @return 当前线程下的Flea Jersey上下文对象
     * @since 1.0.0
     */
    public FleaJerseyContext getContext() {
        FleaJerseyContext context = sContext.get();
        if (ObjectUtils.isEmpty(context)) {
            context = new FleaJerseyContext();
            setContext(context);
        }
        return context;
    }

}
