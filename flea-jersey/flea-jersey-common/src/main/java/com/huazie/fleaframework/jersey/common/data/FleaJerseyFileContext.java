package com.huazie.fleaframework.jersey.common.data;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;

/**
 * Flea Jersey文件相关上下文
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyFileContext {

    private FormDataMultiPart formDataMultiPart; // 多文件表单

    public FormDataMultiPart getFormDataMultiPart() {
        return formDataMultiPart;
    }

    public void setFormDataMultiPart(FormDataMultiPart formDataMultiPart) {
        this.formDataMultiPart = formDataMultiPart;
    }
}
