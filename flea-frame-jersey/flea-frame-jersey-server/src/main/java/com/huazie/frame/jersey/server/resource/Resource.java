package com.huazie.frame.jersey.server.resource;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.xml.JABXUtils;
import com.huazie.frame.jersey.common.FleaJerseyConstants;
import com.huazie.frame.jersey.common.data.FleaJerseyContext;
import com.huazie.frame.jersey.common.data.FleaJerseyFileContext;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.server.filter.FleaJerseyManager;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.File;

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
     * @param fleaJerseyRequest 请求对象
     * @return 响应对象
     * @since 1.0.0
     */
    protected FleaJerseyResponse doResource(FleaJerseyRequest fleaJerseyRequest) {
        initContext();
        return FleaJerseyManager.doFilter(fleaJerseyRequest);
    }

    /**
     * <p> 处理资源数据 </p>
     *
     * @param requestData 请求数据字符串
     * @return 响应对象
     * @since 1.0.0
     */
    protected FleaJerseyResponse doResource(String requestData) {
        initContext();
        return FleaJerseyManager.doFilter(requestData);
    }

    /**
     * <p> 处理文件上传资源数据 </p>
     *
     * @param formDataMultiPart 表单数据
     * @return 响应对象
     * @since 1.0.0
     */
    protected FleaJerseyResponse doCommonFileUploadResource(FormDataMultiPart formDataMultiPart) {

        FleaJerseyResponse fleaJerseyResponse = null;

        if (ObjectUtils.isNotEmpty(formDataMultiPart)) {

            FormDataBodyPart fileFormDataBodyPart = formDataMultiPart.getField(FleaJerseyConstants.FormDataConstants.FORM_DATA_KEY_FILE);
            // 生成文件上下文
            FleaJerseyFileContext fleaJerseyFileContext = new FleaJerseyFileContext();
            fleaJerseyFileContext.setFileFormDataBodyPart(fileFormDataBodyPart);
            // 设置文件上下文
            FleaJerseyManager.getContext().setFleaJerseyFileContext(fleaJerseyFileContext);

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
     * <p> 处理文件下载资源数据 </p>
     *
     * @param fleaJerseyRequest 请求对象
     * @return Jersey响应对象
     * @since 1.0.0
     */
    protected Response doCommonFileDownloadResource(FleaJerseyRequest fleaJerseyRequest) {
        FleaJerseyResponse fleaJerseyResponse = doResource(fleaJerseyRequest);
        String responseData = JABXUtils.toXml(fleaJerseyResponse, false);
        // 获取文件上下文
        FleaJerseyFileContext fleaJerseyFileContext = FleaJerseyManager.getContext().getFleaJerseyFileContext();

        Response response = null;
        if (ObjectUtils.isNotEmpty(fleaJerseyFileContext)) {
            File downloadFile = fleaJerseyFileContext.getFile();
            if (ObjectUtils.isNotEmpty(downloadFile)) {
                if (downloadFile.exists()) {
                    response = Response.ok(downloadFile)
                            .header("Content-disposition", "attachment;response=" + responseData)
                            .header("Cache-Control", "no-cache").build();
                } else {
                    response = Response.status(Response.Status.NOT_FOUND).build();
                }
            } else {
                // 这边取 StreamingOutput

            }
        }

        return response;
    }

    /**
     * <p> 初始化上下文对象 </p>
     *
     * @since 1.0.0
     */
    private void initContext() {
        FleaJerseyContext context = FleaJerseyManager.getContext();
        context.setRequest(request);
        context.setUriInfo(uriInfo);
        context.setHttpHeaders(httpHeaders);
        context.setResourceInfo(resourceInfo);
        context.setServletContext(servletContext);
        context.setHttpServletRequest(httpServletRequest);
        context.setHttpServletResponse(httpServletResponse);
        FleaJerseyManager.setContext(context);
    }

}
