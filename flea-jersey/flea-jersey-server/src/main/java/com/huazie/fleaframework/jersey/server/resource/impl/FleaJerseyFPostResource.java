package com.huazie.fleaframework.jersey.server.resource.impl;

import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import com.huazie.fleaframework.jersey.server.resource.JerseyFileUploadResource;
import com.huazie.fleaframework.jersey.server.resource.Resource;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Flea Jersey 文件 POST 资源，只包含文件 POST 资源API。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyFPostResource extends Resource implements JerseyFileUploadResource {

    /**
     * @see JerseyFileUploadResource#doFileUploadResource(FormDataMultiPart)
     */
    @POST
    @Path("/fileUpload")
    @Override
    public FleaJerseyResponse doFileUploadResource(FormDataMultiPart formDataMultiPart) {
        return doCommonFileUploadResource(formDataMultiPart);
    }

}
