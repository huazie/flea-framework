package com.huazie.fleaframework.jersey.server.resource.impl;

import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import com.huazie.fleaframework.jersey.server.resource.JerseyGetResource;

/**
 * <p> Flea Jersey Get Resource </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyGetResource extends FleaJerseyFGetResource implements JerseyGetResource {

    /**
     * @see JerseyGetResource#doGetResource(String)
     */
    @Override
    public FleaJerseyResponse doGetResource(String requestData) {
        return doResource(requestData);
    }

}