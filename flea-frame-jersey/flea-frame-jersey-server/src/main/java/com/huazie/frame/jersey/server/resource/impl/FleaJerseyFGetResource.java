package com.huazie.frame.jersey.server.resource.impl;

import com.huazie.frame.jersey.server.resource.JerseyFileDownloadResource;
import com.huazie.frame.jersey.server.resource.Resource;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * <p> Flea Jersey File Get Resource </p>
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