package com.huazie.fleaframework.jersey.server.resource.impl;

import com.huazie.fleaframework.jersey.server.resource.JerseyFileDownloadResource;
import com.huazie.fleaframework.jersey.server.resource.Resource;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Flea Jersey 文件 GET 资源，只包含文件 GET 资源API。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyFGetResource extends Resource implements JerseyFileDownloadResource {

    /**
     * @see JerseyFileDownloadResource#doFileDownloadResource(String)
     */
    @GET
    @Path("/fileDownload")
    @Override
    public FormDataMultiPart doFileDownloadResource(@QueryParam("REQUEST") String requestData) {
        return doCommonFileDownloadResource(requestData);
    }

}