package com.huazie.frame.jersey.server.resource;

import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.server.filter.FilterChainManager;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

/**
 * <p> Flea Jersey 资源父类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class Resource {

    @Context
    protected Request request; // 请求信息的上下文

    @Context
    protected UriInfo uriInfo; // 路径信息的上下文

    @Context
    protected HttpHeaders httpHeaders; // 请求头信息的上下文

    @Context
    protected ServletContext servletContext; // Servlet上下文

    @Context
    protected HttpServletRequest httpServletRequest; // Http Servlet请求对象

    @Context
    protected HttpServletResponse httpServletResponse; // Http Servlet响应对象

    @Context
    protected ResourceInfo resourceInfo; // 资源信息

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

    /**
     * <p> 处理资源数据 </p>
     *
     * @param requestXml 请求XMl字符串
     * @return 响应对象
     * @since 1.0.0
     */
    protected FleaJerseyResponse doResource(String requestXml) {
        return FilterChainManager.getManager().doFilter(requestXml);
    }

}
