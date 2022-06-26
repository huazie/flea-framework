package com.huazie.fleaframework.jersey.server.resource;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Jersey 文件下载资源接口，只包含文件下载资源API。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface JerseyFileDownloadResource {

    /**
     * 文件下载资源API，用于处理文件下载资源数据。
     *
     * @param requestData 请求数据字符串
     * @return 支持媒体类型的表单数据
     * @since 1.0.0
     */
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.MULTIPART_FORM_DATA)
    FormDataMultiPart doFileDownloadResource(String requestData);
}
