package com.huazie.fleaframework.core.request;

import com.huazie.fleaframework.common.FleaCommonConfig;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Flea请求上下文，包含 Servlet请求对象 和 响应对象。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaRequestContext extends FleaCommonConfig {

    private static final long serialVersionUID = 4962127931313870946L;

    public static final String REDIRECT_FLAG = "REDIRECT_FLAG"; // 重定向标识

    public static final String REDIRECT_URL = "REDIRECT_URL"; // 重定向地址

    private ServletRequest servletRequest;

    private ServletResponse servletResponse;

    public FleaRequestContext() {
    }

    public FleaRequestContext(ServletRequest servletRequest, ServletResponse servletResponse) {
        this.servletRequest = servletRequest;
        this.servletResponse = servletResponse;
    }

    public ServletRequest getServletRequest() {
        return servletRequest;
    }

    public void setServletRequest(ServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public ServletResponse getServletResponse() {
        return servletResponse;
    }

    public void setServletResponse(ServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }
}
