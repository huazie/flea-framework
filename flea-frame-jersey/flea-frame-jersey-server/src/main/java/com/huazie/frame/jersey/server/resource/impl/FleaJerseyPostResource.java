package com.huazie.frame.jersey.server.resource.impl;

import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.server.resource.JerseyFilePostResource;
import com.huazie.frame.jersey.server.resource.JerseyPostResource;
import com.huazie.frame.jersey.server.resource.Resource;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.core.Response;

/**
 * <p> Flea Jersey Post Resource </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyPostResource extends Resource implements JerseyPostResource, JerseyFilePostResource {

    /**
     * @see JerseyPostResource#doPostResource(FleaJerseyRequest)
     */
    @Override
    public FleaJerseyResponse doPostResource(FleaJerseyRequest fleaJerseyRequest) {
        return doResource(fleaJerseyRequest);
    }

    /**
     * @see JerseyFilePostResource#doFileUploadPostResource(FormDataMultiPart formDataMultiPart)
     */
    @Override
    public FleaJerseyResponse doFileUploadPostResource(FormDataMultiPart formDataMultiPart) {
        return doFileUploadResource(formDataMultiPart);
    }

    /**
     * @see JerseyFilePostResource#doFileDownloadPostResource(FleaJerseyRequest)
     */
    @Override
    public Response doFileDownloadPostResource(FleaJerseyRequest fleaJerseyRequest) {
        return doFileDownloadResource(fleaJerseyRequest);
    }
}
