package com.huazie.frame.jersey.common.data;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import java.io.File;

/**
 * <p> Flea Jersey文件相关上下文 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyFileContext {

    private FormDataBodyPart fileFormDataBodyPart; // 文件表单数据

    private FormDataMultiPart formDataMultiPart; // 多文件表单

    private File file; // 文件下载返回

    public FormDataBodyPart getFileFormDataBodyPart() {
        return fileFormDataBodyPart;
    }

    public void setFileFormDataBodyPart(FormDataBodyPart fileFormDataBodyPart) {
        this.fileFormDataBodyPart = fileFormDataBodyPart;
    }

    public FormDataMultiPart getFormDataMultiPart() {
        return formDataMultiPart;
    }

    public void setFormDataMultiPart(FormDataMultiPart formDataMultiPart) {
        this.formDataMultiPart = formDataMultiPart;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
