package com.huazie.fleaframework.jersey.server.resource;

import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Jersey资源接口，包含了 GET, POST, PUT, DELETE 资源API。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface JerseyResource {

    /**
     * GET 资源API，用于处理 GET 资源数据。
     *
     * @param requestData 请求XMl字符串
     * @return 响应对象
     * @since 1.0.0
     */
    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    FleaJerseyResponse doGetResource(@QueryParam("REQUEST") String requestData);

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

    /**
     * PUT 资源API，用于处理 PUT 资源数据。
     *
     * @param request 请求对象
     * @return 响应对象
     * @since 1.0.0
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    FleaJerseyResponse doPutResource(FleaJerseyRequest request);

    /**
     * DELETE 资源API，用于处理 DELETE 资源数据。
     *
     * @param requestData 请求XMl字符串
     * @return 响应对象
     * @since 1.0.0
     */
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    FleaJerseyResponse doDeleteResource(@QueryParam("REQUEST") String requestData);

}
