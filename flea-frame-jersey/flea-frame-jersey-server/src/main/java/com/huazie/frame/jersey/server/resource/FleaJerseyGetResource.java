package com.huazie.frame.jersey.server.resource;

import com.huazie.frame.jersey.common.data.FleaJerseyResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * <p> Flea Jersey Get Resource </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyGetResource extends FleaJerseyResource {

    /**
     * <p> 处理GET资源数据 </p>
     *
     * @param requestXml 请求XMl字符串
     * @return 响应对象
     * @since 1.0.0
     */
    @GET
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_XML})
    public FleaJerseyResponse doGetResource(@QueryParam("REQUEST") String requestXml) {
        return doResource(requestXml);
    }

}