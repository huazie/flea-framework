package com.huazie.frame.jersey.server.resource.impl;

import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.server.resource.JerseyDeleteResource;
import com.huazie.frame.jersey.server.resource.Resource;

/**
 * <p> Flea Jersey Delete Resource </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyDeleteResource extends Resource implements JerseyDeleteResource {

    /**
     * <p> 处理DELETE资源数据 </p>
     *
     * @param requestXml 请求XMl字符串
     * @return 响应对象
     * @since 1.0.0
     */
    @Override
    public FleaJerseyResponse doDeleteResource(String requestXml) {
        return doResource(requestXml);
    }

}
