package com.huazie.frame.jersey.server.filter;

import com.huazie.frame.jersey.common.data.FleaJerseyContext;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;

/**
 * <p> Flea Jersey 管理类</p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyManager {

    /**
     * <p> 执行过滤器 </p>
     *
     * @param request 请求对象
     * @return 响应对象
     * @since 1.0.0
     */
    public static FleaJerseyResponse doFilter(FleaJerseyRequest request) {
        return FilterChainManager.getManager().doFilter(request);
    }

    /**
     * <p> 执行过滤器 </p>
     *
     * @param requestData 请求数据字符串
     * @return 响应对象
     * @since 1.0.0
     */
    public static FleaJerseyResponse doFilter(String requestData) {
        return FilterChainManager.getManager().doFilter(requestData);
    }

    /**
     * <p> 设置Flea Jersey上下文对象 </p>
     *
     * @param context Flea Jersey上下文对象
     * @since 1.0.0
     */
    public static void setContext(FleaJerseyContext context) {
        FilterChainManager.getManager().setContext(context);
    }

    /**
     * <p> 获取Flea Jersey上下文对象 </p>
     *
     * @return 当前线程下的Flea Jersey上下文对象
     * @since 1.0.0
     */
    public static FleaJerseyContext getContext() {
        return FilterChainManager.getManager().getContext();
    }

}
