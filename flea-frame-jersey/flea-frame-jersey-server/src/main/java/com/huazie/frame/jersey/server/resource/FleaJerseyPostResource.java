package com.huazie.frame.jersey.server.resource;

import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <p> Flea Jersey Post Resource </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyPostResource extends FleaJerseyResource {

    /**
     * <p> 处理POST资源数据 </p>
     *
     * @param request 请求对象
     * @return 响应对象
     * @since 1.0.0
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public FleaJerseyResponse doPostResource(FleaJerseyRequest request) {
        return doResource(request);
    }

}
