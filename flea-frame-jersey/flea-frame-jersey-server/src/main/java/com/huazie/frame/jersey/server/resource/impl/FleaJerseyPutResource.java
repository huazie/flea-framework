package com.huazie.frame.jersey.server.resource.impl;

import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.server.resource.JerseyFileUploadResource;
import com.huazie.frame.jersey.server.resource.JerseyPutResource;
import com.huazie.frame.jersey.server.resource.Resource;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

/**
 * <p> Flea Jersey Put Resource </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyPutResource extends Resource implements JerseyPutResource, JerseyFileUploadResource {

    /**
     * @see JerseyPutResource#doPutResource(FleaJerseyRequest request)
     */
    @Override
    public FleaJerseyResponse doPutResource(FleaJerseyRequest request) {
        return doResource(request);
    }

    /**
     * @see JerseyFileUploadResource#doFileUploadResource(FormDataMultiPart formDataMultiPart)
     */
    @Override
    public FleaJerseyResponse doFileUploadResource(FormDataMultiPart formDataMultiPart) {
        return doCommonFileUploadResource(formDataMultiPart);
    }
}
