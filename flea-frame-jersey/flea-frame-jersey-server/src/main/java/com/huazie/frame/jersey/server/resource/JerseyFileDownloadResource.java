package com.huazie.frame.jersey.server.resource;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * <p> Jersey 文件下载资源 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface JerseyFileDownloadResource {

    /**
     * <p> 处理文件下载资源数据 </p>
     *
     * @param requestData 请求数据字符串
     * @return Jersey响应对象
     * @since 1.0.0
     */
    @GET
    @Path("/fileDownload")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.MULTIPART_FORM_DATA)
    FormDataMultiPart doFileDownloadResource(@QueryParam("REQUEST") String requestData);
}
