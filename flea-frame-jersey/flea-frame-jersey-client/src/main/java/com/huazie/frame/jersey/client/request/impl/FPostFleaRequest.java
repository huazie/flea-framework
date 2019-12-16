package com.huazie.frame.jersey.client.request.impl;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.xml.JABXUtils;
import com.huazie.frame.jersey.client.request.RequestConfig;
import com.huazie.frame.jersey.client.request.RequestModeEnum;
import com.huazie.frame.jersey.common.FleaJerseyConstants;
import com.huazie.frame.jersey.common.FleaJerseyManager;
import com.huazie.frame.jersey.common.data.FleaJerseyFileContext;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.common.exception.FleaJerseyCommonException;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(FPostFleaRequest.class);

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

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FPostFleaRequest##request(WebTarget, FleaJerseyRequest) Start");
        }

        String requestData = JABXUtils.toXml(request, false);

        FleaJerseyFileContext fileContext = FleaJerseyManager.getManager().getFileContext();
        FormDataMultiPart formDataMultiPart = null;
        if (ObjectUtils.isNotEmpty(fileContext)) {
            formDataMultiPart = fileContext.getFormDataMultiPart();
            if (ObjectUtils.isNotEmpty(formDataMultiPart)) {
                FormDataBodyPart formDataBodyPart = new FormDataBodyPart(FleaJerseyConstants.FormDataConstants.FORM_DATA_KEY_REQUEST, requestData);
                formDataMultiPart.bodyPart(formDataBodyPart);
            } else {
                // {0}获取失败，请检查
                throw new FleaJerseyCommonException("ERROR-JERSEY-COMMON0000000000", "【FormDataMultiPart】");
            }
        } else {
            // {0}获取失败，请检查
            throw new FleaJerseyCommonException("ERROR-JERSEY-COMMON0000000000", "【FleaJerseyFileContext】");
        }

        Entity<FormDataMultiPart> entity = Entity.entity(formDataMultiPart, toMediaType());
        FleaJerseyResponse response = target.path(FleaJerseyConstants.FileResourceConstants.FILE_UPLOAD_PATH).request().post(entity, FleaJerseyResponse.class);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FPostFleaRequest##request(WebTarget, FleaJerseyRequest) FleaJerseyResponse = {}", response);
            LOGGER.debug("FPostFleaRequest##request(WebTarget, FleaJerseyRequest) End");
        }
        return response;
    }
}
