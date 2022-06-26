package com.huazie.fleaframework.jersey.client.request;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.jersey.client.response.Response;

/**
 * Flea Request 接口，对外提供执行请求的能力。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Request {

    /**
     * 执行请求
     *
     * @return 响应结果
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    <T> Response<T> doRequest(Class<T> clazz) throws CommonException;

    /**
     * 获取请求方式
     *
     * @return 请求方式
     * @since 1.0.0
     */
    RequestModeEnum getRequestMode();
}
