package com.huazie.fleaframework.jersey.server.resource.impl;

import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import com.huazie.fleaframework.jersey.server.resource.JerseyPutResource;

/**
 * Flea Jersey PUT 资源，包含 通用 PUT 资源API，文件上传 PUT 资源API。
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
