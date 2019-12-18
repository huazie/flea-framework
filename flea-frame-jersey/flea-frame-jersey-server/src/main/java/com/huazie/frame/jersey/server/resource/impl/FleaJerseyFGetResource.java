package com.huazie.frame.jersey.server.resource.impl;

import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.server.resource.JerseyFileDownloadResource;
import com.huazie.frame.jersey.server.resource.Resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * <p> Flea Jersey File Get Resource </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyFGetResource extends Resource implements JerseyFileDownloadResource {

    /**
     * @see JerseyFileDownloadResource#doFileDownloadResource(FleaJerseyRequest)
     */
    @GET
    @Path("/fileDownload")
    @Override
    public Response doFileDownloadResource(FleaJerseyRequest request) {
        return doCommonFileDownloadResource(request);
    }

}