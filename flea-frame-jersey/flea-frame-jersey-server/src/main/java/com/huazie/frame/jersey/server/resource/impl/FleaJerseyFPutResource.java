package com.huazie.frame.jersey.server.resource.impl;

import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.server.resource.JerseyFileUploadResource;
import com.huazie.frame.jersey.server.resource.Resource;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 * <p> Flea Jersey Put Resource </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyFPutResource extends Resource implements JerseyFileUploadResource {

    /**
     * @see JerseyFileUploadResource#doFileUploadResource(FormDataMultiPart)
     */
    @PUT
    @Path("/fileUpload")
    @Override
    public FleaJerseyResponse doFileUploadResource(FormDataMultiPart formDataMultiPart) {
        return doCommonFileUploadResource(formDataMultiPart);
    }

}
