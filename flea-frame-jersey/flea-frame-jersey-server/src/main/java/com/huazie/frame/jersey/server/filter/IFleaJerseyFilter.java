package com.huazie.frame.jersey.server.filter;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;

/**
 * <p> Flea Jersey 过滤器接口类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyFilter {

    /**
     * <p> 执行过滤器 </p>
     *
     * @param request  请求对象
     * @param response 响应对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws CommonException;

}
