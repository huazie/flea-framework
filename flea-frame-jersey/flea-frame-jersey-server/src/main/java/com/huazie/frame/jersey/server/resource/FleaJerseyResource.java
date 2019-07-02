package com.huazie.frame.jersey.server.resource;

import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.server.filter.FilterChainManager;

/**
 * <p> Flea Jersey 资源父类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class FleaJerseyResource {

    /**
     * <p> 处理资源数据 </p>
     *
     * @param request 请求对象
     * @return 响应对象
     * @since 1.0.0
     */
    protected FleaJerseyResponse doResource(FleaJerseyRequest request) {
        return FilterChainManager.getManager().doFilter(request);
    }

}
