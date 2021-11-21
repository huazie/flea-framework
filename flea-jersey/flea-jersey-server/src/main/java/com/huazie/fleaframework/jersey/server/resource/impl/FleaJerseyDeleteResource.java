package com.huazie.fleaframework.jersey.server.resource.impl;

import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import com.huazie.fleaframework.jersey.server.resource.JerseyDeleteResource;
import com.huazie.fleaframework.jersey.server.resource.Resource;

/**
 * <p> Flea Jersey Delete Resource </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyDeleteResource extends Resource implements JerseyDeleteResource {

    /**
     * @see JerseyDeleteResource#doDeleteResource(String)
     */
    @Override
    public FleaJerseyResponse doDeleteResource(String requestData) {
        return doResource(requestData);
    }

}
