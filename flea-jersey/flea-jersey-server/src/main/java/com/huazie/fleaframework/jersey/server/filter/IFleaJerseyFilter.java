package com.huazie.fleaframework.jersey.server.filter;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;

/**
 * Flea Jersey 过滤器接口，定义了执行前置、业务服务、后置过滤器的API
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyFilter {

    /**
     * 执行前置，业务服务，后置过滤器
     *
     * @param request  请求对象
     * @param response 响应对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws CommonException;

}
