package com.huazie.fleaframework.jersey.common.data;

import com.huazie.fleaframework.common.FleaCommonConfig;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

/**
 * Flea Jersey 上下文
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyContext extends FleaCommonConfig {

    private Request request; // 请求信息的上下文

    private UriInfo uriInfo; // 路径信息的上下文

    private HttpHeaders httpHeaders; // 请求头信息的上下文

    private ServletContext servletContext; // Servlet上下文

    private HttpServletRequest httpServletRequest; // Http Servlet请求对象

    private HttpServletResponse httpServletResponse; // Http Servlet响应对象

    private ResourceInfo resourceInfo; // 资源信息

    private FleaJerseyFileContext fleaJerseyFileContext; // Flea Jersey文件相关上下文

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public UriInfo getUriInfo() {
        return uriInfo;
    }

    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public ResourceInfo getResourceInfo() {
        return resourceInfo;
    }

    public void setResourceInfo(ResourceInfo resourceInfo) {
        this.resourceInfo = resourceInfo;
    }

    public FleaJerseyFileContext getFleaJerseyFileContext() {
        return fleaJerseyFileContext;
    }

    public void setFleaJerseyFileContext(FleaJerseyFileContext fleaJerseyFileContext) {
        this.fleaJerseyFileContext = fleaJerseyFileContext;
    }
}
