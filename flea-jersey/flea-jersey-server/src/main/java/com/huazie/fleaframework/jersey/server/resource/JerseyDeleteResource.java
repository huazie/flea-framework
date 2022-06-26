package com.huazie.fleaframework.jersey.server.resource;

import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Jersey DELETE 资源接口，只包含 DELETE 资源API。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface JerseyDeleteResource {

    /**
     * DELETE 资源API，用于处理 DELETE 资源数据。
     *
     * @param requestData 请求数据字符串
     * @return 响应对象
     * @since 1.0.0
     */
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    FleaJerseyResponse doDeleteResource(@QueryParam("REQUEST") String requestData);

}
