package com.huazie.fleaframework.jersey.server.resource;

import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Jersey 文件上传资源接口，只包含文件上传资源API。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface JerseyFileUploadResource {

    /**
     * 文件上传资源API，用于处理文件上传资源数据。
     *
     * @param formDataMultiPart 表单数据
     * @return Flea Jersey 响应对象
     * @since 1.0.0
     */
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    FleaJerseyResponse doFileUploadResource(FormDataMultiPart formDataMultiPart);

}
