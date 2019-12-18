package com.huazie.frame.jersey.server.resource;

import com.huazie.frame.jersey.common.data.FleaJerseyRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * <p> Jersey 文件下载资源 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface JerseyFileDownloadResource {

    /**
     * <p> 处理文件下载POST资源数据 </p>
     *
     * @param request 请求对象
     * @return Jersey响应对象
     * @since 1.0.0
     */
    @Path("/fileDownload")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    Response doFileDownloadResource(FleaJerseyRequest request);
}
