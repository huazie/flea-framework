package com.huazie.frame.jersey.server.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * <p> Flea Jersey Get Resource , 只用于测试调用 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyTestGetResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String doGetResource(@QueryParam("id") final String resId) {
        return doGet(resId);
    }

    protected abstract String doGet(String resId);

}
