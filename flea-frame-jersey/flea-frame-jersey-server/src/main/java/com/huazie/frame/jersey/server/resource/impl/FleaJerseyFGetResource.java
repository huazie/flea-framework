package com.huazie.frame.jersey.server.resource.impl;

import com.huazie.frame.jersey.server.resource.JerseyFileDownloadResource;
import com.huazie.frame.jersey.server.resource.Resource;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

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
    @Override
    public FormDataMultiPart doFileDownloadResource(String requestData) {
        return doCommonFileDownloadResource(requestData);
    }

}