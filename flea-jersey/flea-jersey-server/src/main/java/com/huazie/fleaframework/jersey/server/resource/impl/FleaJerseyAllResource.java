package com.huazie.fleaframework.jersey.server.resource.impl;

import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import com.huazie.fleaframework.jersey.server.resource.JerseyResource;
import com.huazie.fleaframework.jersey.server.resource.Resource;

/**
 * Flea Jersey资源抽象类，包含 GET, POST, PUT, DELETE 资源API。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyAllResource extends Resource implements JerseyResource {

    /**
     * @see JerseyResource#doGetResource(String)
     */
    @Override
    public FleaJerseyResponse doGetResource(String requestData) {
        return doResource(requestData);
    }

    /**
     * @see JerseyResource#doPostResource(FleaJerseyRequest)
     */
    @Override
    public FleaJerseyResponse doPostResource(FleaJerseyRequest request) {
        return doResource(request);
    }

    /**
     * @see JerseyResource#doPutResource(FleaJerseyRequest)
     */
    @Override
    public FleaJerseyResponse doPutResource(FleaJerseyRequest request) {
        return doResource(request);
    }

    /**
     * @see JerseyResource#doDeleteResource(String)
     */
    @Override
    public FleaJerseyResponse doDeleteResource(String requestData) {
        return doResource(requestData);
    }
}
