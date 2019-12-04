package com.huazie.frame.jersey.server.resource;

import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * <p> Jersey文件POST资源接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface JerseyFilePostResource {

    /**
     * <p> 处理文件上传POST资源数据 </p>
     *
     * @param formDataMultiPart 表单数据
     * @return 响应对象
     * @since 1.0.0
     */
    @POST
    @Path("/jerseyUpload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    FleaJerseyResponse doFileUploadPostResource(FormDataMultiPart formDataMultiPart);

    /**
     * <p> 处理文件下载POST资源数据 </p>
     *
     * @param fleaJerseyRequest 请求对象
     * @return Jersey响应对象
     * @since 1.0.0
     */
    @POST
    @Path("/jerseyDownload")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    Response doFileDownloadPostResource(FleaJerseyRequest fleaJerseyRequest);

}
