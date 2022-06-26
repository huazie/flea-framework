package com.huazie.fleaframework.jersey.server.filter;

import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;

/**
 * Flea Jersey 异常过滤器接口类，定义了异常过滤器的API。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyErrorFilter {

    /**
     * 执行异常过滤器，处理异常信息。
     *
     * @param request   Flea Jersey 请求对象
     * @param response  Flea Jersey 响应对象
     * @param throwable 异常类
     * @since 1.0.0
     */
    void doFilter(FleaJerseyRequest request, FleaJerseyResponse response, Throwable throwable);

}
