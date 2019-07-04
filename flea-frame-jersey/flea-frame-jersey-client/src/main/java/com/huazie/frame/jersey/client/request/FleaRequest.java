package com.huazie.frame.jersey.client.request;

import com.huazie.frame.jersey.client.ResponseResult;

/**
 * <p> Flea Request 接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FleaRequest {

    /**
     * <p> 执行请求 </p>
     *
     * @param <T> 业务出参
     * @return 响应结果
     * @throws Exception
     * @since 1.0.0
     */
    <T> ResponseResult<T> doRequest() throws Exception;

}
