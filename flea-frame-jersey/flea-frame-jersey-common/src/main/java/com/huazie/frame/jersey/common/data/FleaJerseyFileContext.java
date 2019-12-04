package com.huazie.frame.jersey.common.data;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import java.io.File;
import java.io.InputStream;

/**
 * <p> Flea Jersey文件相关上下文 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyFileContext {

    private InputStream inputStream; // 文件输入流

    private FormDataContentDisposition formDataContentDisposition; // 表单内容（文件）

    private FormDataMultiPart formDataMultiPart; // 多文件表单

    private File file; // 文件下载返回

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public FormDataContentDisposition getFormDataContentDisposition() {
        return formDataContentDisposition;
    }

    public void setFormDataContentDisposition(FormDataContentDisposition formDataContentDisposition) {
        this.formDataContentDisposition = formDataContentDisposition;
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
