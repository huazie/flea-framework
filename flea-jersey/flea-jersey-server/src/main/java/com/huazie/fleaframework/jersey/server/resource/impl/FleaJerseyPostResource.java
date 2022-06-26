package com.huazie.fleaframework.jersey.server.resource.impl;

import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import com.huazie.fleaframework.jersey.server.resource.JerseyPostResource;

/**
 * Flea Jersey POST 资源，包含 通用 POST 资源API，文件上传 POST 资源API。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyPostResource extends FleaJerseyFPostResource implements JerseyPostResource {

    /**
     * @see JerseyPostResource#doPostResource(FleaJerseyRequest)
     */
    @Override
    public FleaJerseyResponse doPostResource(FleaJerseyRequest request) {
        return doResource(request);
    }

}
