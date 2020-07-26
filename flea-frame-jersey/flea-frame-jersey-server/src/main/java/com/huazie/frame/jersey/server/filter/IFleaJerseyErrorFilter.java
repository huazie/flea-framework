package com.huazie.frame.jersey.server.filter;

import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;

/**
 * <p> Flea Jersey 异常过滤器接口类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyErrorFilter {

    /**
     * <p> 处理异常信息 </p>
     *
     * @param request   Flea Jersey 请求对象
     * @param response  Flea Jersey 响应对象
     * @param throwable 异常类
     * @since 1.0.0
     */
    void doFilter(FleaJerseyRequest request, FleaJerseyResponse response, Throwable throwable);

}
