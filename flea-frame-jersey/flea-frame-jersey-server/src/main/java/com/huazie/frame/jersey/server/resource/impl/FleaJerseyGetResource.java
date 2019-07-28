package com.huazie.frame.jersey.server.resource.impl;

import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.server.resource.JerseyGetResource;
import com.huazie.frame.jersey.server.resource.Resource;

/**
 * <p> Flea Jersey Get Resource </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyGetResource extends Resource implements JerseyGetResource {

    /**
     * <p> 处理GET资源数据 </p>
     *
     * @param requestXml 请求XMl字符串
     * @return 响应对象
     * @since 1.0.0
     */
    @Override
    public FleaJerseyResponse doGetResource(String requestXml) {
        return doResource(requestXml);
    }

}
