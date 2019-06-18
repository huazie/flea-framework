package com.huazie.frame.jersey.api.filter;

import com.huazie.frame.jersey.api.data.FleaJerseyResponse;

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
     * @param response  Flea Jersey 响应对象
     * @param throwable 异常类
     * @throws Exception
     * @since 1.0.0
     */
    void doFilter(FleaJerseyResponse response, Throwable throwable);

}
