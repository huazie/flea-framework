package com.huazie.frame.jersey.server.resource.impl;

import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.server.resource.JerseyFileUploadResource;
import com.huazie.frame.jersey.server.resource.JerseyPostResource;
import com.huazie.frame.jersey.server.resource.Resource;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.POST;

/**
 * <p> Flea Jersey Post Resource </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyPostResource extends Resource implements JerseyPostResource, JerseyFileUploadResource {

    /**
     * @see JerseyPostResource#doPostResource(FleaJerseyRequest request)
     */
    @Override
    public FleaJerseyResponse doPostResource(FleaJerseyRequest request) {
        return doResource(request);
    }

    /**
     * @see JerseyFileUploadResource#doFileUploadResource(FormDataMultiPart formDataMultiPart)
     */
    @POST
    @Override
    public FleaJerseyResponse doFileUploadResource(FormDataMultiPart formDataMultiPart) {
        return doCommonFileUploadResource(formDataMultiPart);
    }

}
