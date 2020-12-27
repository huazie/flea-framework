package com.huazie.frame.jersey.client.request.impl;

import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.xml.JABXUtils;
import com.huazie.frame.jersey.client.request.RequestConfig;
import com.huazie.frame.jersey.client.request.RequestModeEnum;
import com.huazie.frame.jersey.common.FleaJerseyConstants;
import com.huazie.frame.jersey.common.FleaJerseyManager;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

/**
 * <p> 文件POST请求 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FPostFleaRequest extends FleaRequest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FPostFleaRequest.class);

    /**
     * <p> 不带参数的构造方法 </p>
     *
     * @since 1.0.0
     */
    public FPostFleaRequest() {
    }

    /**
     * <p> 带参数的构造方法 </p>
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
    protected FleaJerseyResponse request(WebTarget target, FleaJerseyRequest request) throws Exception {

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
