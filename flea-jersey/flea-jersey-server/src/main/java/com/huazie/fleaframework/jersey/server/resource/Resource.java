package com.huazie.fleaframework.jersey.server.resource;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.xml.JABXUtils;
import com.huazie.fleaframework.jersey.common.FleaJerseyConstants;
import com.huazie.fleaframework.jersey.common.FleaJerseyManager;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyContext;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyFileContext;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import com.huazie.fleaframework.jersey.server.filter.FleaJerseyFilterChainManager;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

/**
 * Flea Jersey 接口资源父类，包含普通资源的公共处理逻辑，
 * 上传资源的公共处理逻辑，下载资源的公共处理逻辑。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class Resource {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(Resource.class);

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
     * 处理资源数据 
     *
     * @param fleaJerseyRequest 请求对象
     * @return 响应对象
     * @since 1.0.0
     */
    protected FleaJerseyResponse doResource(FleaJerseyRequest fleaJerseyRequest) {
        initContext();
        return FleaJerseyFilterChainManager.getManager().doFilter(fleaJerseyRequest);
    }

    /**
     * 处理资源数据 
     *
     * @param requestData 请求数据字符串
     * @return 响应对象
     * @since 1.0.0
     */
    protected FleaJerseyResponse doResource(String requestData) {
        initContext();
        return FleaJerseyFilterChainManager.getManager().doFilter(requestData);
    }

    /**
     * 处理文件上传资源数据 
     *
     * @param formDataMultiPart 表单数据
     * @return 响应对象
     * @since 1.0.0
     */
    protected FleaJerseyResponse doCommonFileUploadResource(FormDataMultiPart formDataMultiPart) {

        FleaJerseyResponse fleaJerseyResponse = null;

        if (ObjectUtils.isNotEmpty(formDataMultiPart)) {

            // 生成文件上下文
            FleaJerseyFileContext fleaJerseyFileContext = new FleaJerseyFileContext();
            fleaJerseyFileContext.setFormDataMultiPart(formDataMultiPart);
            // 设置文件上下文
            FleaJerseyManager.getManager().getContext().setFleaJerseyFileContext(fleaJerseyFileContext);

            // 获取请求表单数据
            FormDataBodyPart formDataBodyPart = formDataMultiPart.getField(FleaJerseyConstants.FormDataConstants.FORM_DATA_KEY_REQUEST);
            // 获取请求参数
            String requestData = formDataBodyPart.getValueAs(String.class);
            // 处理文件上传资源数据
            fleaJerseyResponse = doResource(requestData);
        }

        return fleaJerseyResponse;
    }

    /**
     * 处理文件下载资源数据 
     *
     * @param requestData 请求数据字符串
     * @return Jersey响应对象
     * @since 1.0.0
     */
    protected FormDataMultiPart doCommonFileDownloadResource(String requestData) {

        FleaJerseyResponse fleaJerseyResponse = doResource(requestData);

        String responseData = JABXUtils.toXml(fleaJerseyResponse, false);

        FormDataMultiPart formDataMultiPart = null;

        try {
            // 将响应数据添加到表单中
            FleaJerseyManager.getManager().addFormDataBodyPart(responseData, FleaJerseyConstants.FormDataConstants.FORM_DATA_KEY_RESPONSE);
            formDataMultiPart = FleaJerseyManager.getManager().getFileContext().getFormDataMultiPart();
        } catch (CommonException e) {
            LOGGER.error1(new Object() {}, "Exception occurs : \n", e);
        }
        return formDataMultiPart;
    }

    /**
     * 初始化上下文对象 
     *
     * @since 1.0.0
     */
    private void initContext() {
        FleaJerseyContext context = FleaJerseyManager.getManager().getContext();
        context.setRequest(request);
        context.setUriInfo(uriInfo);
        context.setHttpHeaders(httpHeaders);
        context.setResourceInfo(resourceInfo);
        context.setServletContext(servletContext);
        context.setHttpServletRequest(httpServletRequest);
        context.setHttpServletResponse(httpServletResponse);
        FleaJerseyManager.getManager().setContext(context);
    }

}
