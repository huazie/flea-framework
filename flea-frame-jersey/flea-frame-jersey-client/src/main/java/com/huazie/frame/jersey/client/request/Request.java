package com.huazie.frame.jersey.client.request;

import com.huazie.frame.jersey.client.response.Response;

/**
 * <p> Flea Request 接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Request {

    /**
     * <p> 执行请求 </p>
     *
     * @return 响应结果
     * @throws Exception
     * @since 1.0.0
     */
    <T> Response<T> doRequest(Class<T> clazz) throws Exception;

    /**
     * <p> 获取请求方式 </p>
     *
     * @return
     */
    RequestModeEnum getRequestMode();
}
