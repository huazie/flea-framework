package com.huazie.fleaframework.jersey.client.request.impl;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.xml.JABXUtils;
import com.huazie.fleaframework.jersey.client.request.RequestConfig;
import com.huazie.fleaframework.jersey.client.request.RequestModeEnum;
import com.huazie.fleaframework.jersey.common.FleaJerseyConstants;
import com.huazie.fleaframework.jersey.common.FleaJerseyManager;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

/**
 * 文件 POST 请求，对外提供了执行文件上传请求的能力。
 * <p> 注：服务端提供的资源入口方法需包含 POST 注解和
 * Path 注解【如：<b>@Path("/fileUpload")</b>】。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FPostFleaRequest extends FleaRequest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FPostFleaRequest.class);

    /**
     * 默认的构造方法
     *
     * @since 1.0.0
     */
    public FPostFleaRequest() {
    }

    /**
     * 带请求配置参数的构造方法
     *
     * @param config 请求配置
     * @since 1.0.0
     */
    public FPostFleaRequest(RequestConfig config) {
        super(config);
    }

    @Override
    protected void init() {
        modeEnum = RequestModeEnum.FPOST;
    }

    @Override
    protected FleaJerseyResponse request(WebTarget target, FleaJerseyRequest request) throws CommonException {

        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "FILE POST Request, Start");
        }

        String requestData = JABXUtils.toXml(request, false);
        // 添加请求表单数据
        FleaJerseyManager.getManager().addFormDataBodyPart(requestData, FleaJerseyConstants.FormDataConstants.FORM_DATA_KEY_REQUEST);

        Entity<FormDataMultiPart> entity = Entity.entity(FleaJerseyManager.getManager().getFileContext().getFormDataMultiPart(), toMediaType());

        // 文件上传POST请求发送
        FleaJerseyResponse response = target
                .path(FleaJerseyConstants.FileResourceConstants.FILE_UPLOAD_PATH)
                .request()
                .post(entity, FleaJerseyResponse.class);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "FILE POST Request, FleaJerseyResponse = {}", response);
            LOGGER.debug1(obj, "FILE POST Request, End");
        }

        return response;
    }
}
