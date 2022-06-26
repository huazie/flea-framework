package com.huazie.fleaframework.jersey.server.resource;

import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Jersey POST 资源接口，只包含 POST 资源API。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface JerseyPostResource {

    /**
     * POST 资源API，用于处理 POST 资源数据。
     *
     * @param request 请求对象
     * @return 响应对象
     * @since 1.0.0
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    FleaJerseyResponse doPostResource(FleaJerseyRequest request);

}
