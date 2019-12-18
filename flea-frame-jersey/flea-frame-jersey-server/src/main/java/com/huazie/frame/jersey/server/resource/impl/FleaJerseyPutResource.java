package com.huazie.frame.jersey.server.resource.impl;

import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.server.resource.JerseyPutResource;

/**
 * <p> Flea Jersey Put Resource </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyPutResource extends FleaJerseyFPutResource implements JerseyPutResource {

    /**
     * @see JerseyPutResource#doPutResource(FleaJerseyRequest)
     */
    @Override
    public FleaJerseyResponse doPutResource(FleaJerseyRequest request) {
        return doResource(request);
    }

}
