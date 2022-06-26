package com.huazie.fleaframework.jersey.server.resource.impl;

import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import com.huazie.fleaframework.jersey.server.resource.JerseyGetResource;

/**
 * Flea Jersey GET 资源，包含 通用 GET 资源API，文件下载 GET 资源API。
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