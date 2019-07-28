package com.huazie.frame.jersey.server.resource.impl;

import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.server.resource.JerseyPutResource;
import com.huazie.frame.jersey.server.resource.Resource;

/**
 * <p> Flea Jersey Put Resource </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyPutResource extends Resource implements JerseyPutResource {

    /**
     * <p> 处理PUT资源数据 </p>
     *
     * @param request 请求对象
     * @return 响应对象
     * @since 1.0.0
     */
    @Override
    public FleaJerseyResponse doPutResource(FleaJerseyRequest request) {
        return doResource(request);
    }

}
