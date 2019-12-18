package com.huazie.frame.jersey.server.resource.impl;

import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.server.resource.JerseyFileDownloadResource;
import com.huazie.frame.jersey.server.resource.JerseyGetResource;
import com.huazie.frame.jersey.server.resource.Resource;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

/**
 * <p> Flea Jersey Get Resource </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyGetResource extends Resource implements JerseyGetResource, JerseyFileDownloadResource {

    /**
     * @see JerseyGetResource#doGetResource(String requestData)
     */
    @Override
    public FleaJerseyResponse doGetResource(String requestData) {
        return doResource(requestData);
    }

    /**
     * @see JerseyFileDownloadResource#doFileDownloadResource(FleaJerseyRequest request)
     */
    @GET
    @Override
    public Response doFileDownloadResource(FleaJerseyRequest request) {
        return doCommonFileDownloadResource(request);
    }
}