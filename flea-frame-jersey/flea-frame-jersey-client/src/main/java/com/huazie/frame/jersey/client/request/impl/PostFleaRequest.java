package com.huazie.frame.jersey.client.request.impl;

import com.huazie.frame.jersey.client.request.RequestConfig;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;

/**
 * <p> Post 请求 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class PostFleaRequest<T> extends FleaRequest<T>{

    public PostFleaRequest() {
    }

    public PostFleaRequest(RequestConfig config) {
        super(config);
    }

    @Override
    protected FleaJerseyResponse request() throws Exception {
        return null;
    }
}
