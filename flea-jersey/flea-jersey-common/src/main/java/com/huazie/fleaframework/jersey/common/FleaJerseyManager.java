package com.huazie.fleaframework.jersey.common;

import com.huazie.fleaframework.common.FleaFrameManager;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.jersey.common.data.FleaFileObject;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyContext;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyFileContext;
import com.huazie.fleaframework.jersey.common.exceptions.FleaJerseyCommonException;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.core.MediaType;
import java.io.File;

/**
 * Flea Jersey 管理类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyManager {

    private static volatile FleaJerseyManager manager;

    private static ThreadLocal<FleaJerseyContext> sContext = new ThreadLocal<>(); // Flea Jersey 上下文

    /**
     * 构造器私有，只能通过getManager()获取实例
     */
    private FleaJerseyManager() {
    }

    /**
     * 获取一个Flea Jersey管理类实例
     *
     * @return Flea Jersey 管理类
     * @since 1.0.0
     */
    public static FleaJerseyManager getManager() {
        if (ObjectUtils.isEmpty(manager)) {
            synchronized (FleaFrameManager.class) {
                if (ObjectUtils.isEmpty(manager)) {
                    manager = new FleaJerseyManager();
                }
            }
        }
        return manager;
    }

    /**
     * 设置Flea Jersey上下文对象
     *
     * @param context Flea Jersey上下文对象
     * @since 1.0.0
     */
    public void setContext(FleaJerseyContext context) {
        if (ObjectUtils.isNotEmpty(context)) {
            sContext.set(context);
        }
    }

    /**
     * 获取Flea Jersey上下文对象
     *
     * @return 当前线程下的Flea Jersey上下文对象
     * @since 1.0.0
     */
    public FleaJerseyContext getContext() {
        FleaJerseyContext context = sContext.get();
        if (ObjectUtils.isEmpty(context)) {
            context = new FleaJerseyContext();
            setContext(context);
        }
        return context;
    }

    /**
     * 获取文件上下文
     *
     * @return 文件上下文对象
     * @since 1.0.0
     */
    public FleaJerseyFileContext getFileContext() {
        FleaJerseyFileContext fileContext = null;
        FleaJerseyContext context = getContext();
        if (ObjectUtils.isNotEmpty(context)) {
            fileContext = context.getFleaJerseyFileContext();
            if (ObjectUtils.isEmpty(fileContext)) {
                fileContext = new FleaJerseyFileContext();
                // 新建文件上下文，并塞入Flea Jersey上下文中
                context.setFleaJerseyFileContext(fileContext);
            }
        }
        return fileContext;
    }

    /**
     * 获取Flea文件对象
     *
     * @return Flea文件对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public FleaFileObject getFileObject() throws CommonException {
        FormDataBodyPart fileFormDataBodyPart = getFileFormDataBodyPart();
        FormDataContentDisposition formDataContentDisposition = fileFormDataBodyPart.getFormDataContentDisposition();
        FleaFileObject fileObject = new FleaFileObject();
        if (ObjectUtils.isNotEmpty(formDataContentDisposition)) {
            fileObject.setFileName(formDataContentDisposition.getFileName());
        }
        fileObject.setFile(fileFormDataBodyPart.getValueAs(File.class));
        return fileObject;
    }

    /**
     * 获取文件表单信息
     *
     * @return 文件表单数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public FormDataBodyPart getFileFormDataBodyPart() throws CommonException {
        return getFormDataBodyPart(FleaJerseyConstants.FormDataConstants.FORM_DATA_KEY_FILE);
    }

    /**
     * 获取表单数据信息
     *
     * @param formDataKey 表单数据键
     * @return 表单数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public FormDataBodyPart getFormDataBodyPart(String formDataKey) throws CommonException {

        // {0}不能为空，请检查
        StringUtils.checkBlank(formDataKey, FleaJerseyCommonException.class, "ERROR-JERSEY-COMMON0000000001", "【Form Data Key】");

        // 获取文件上下文信息
        FleaJerseyFileContext fleaJerseyFileContext = getFileContext();
        // {0}获取失败，请检查
        ObjectUtils.checkEmpty(fleaJerseyFileContext, FleaJerseyCommonException.class, "ERROR-JERSEY-COMMON0000000000", "【FleaJerseyFileContext】");

        // 获取表单数据
        FormDataMultiPart formDataMultiPart = fleaJerseyFileContext.getFormDataMultiPart();
        // {0}获取失败，请检查
        ObjectUtils.checkEmpty(formDataMultiPart, FleaJerseyCommonException.class, "ERROR-JERSEY-COMMON0000000000", "【FormDataMultiPart】");

        // 获取文件表单数据内容
        FormDataBodyPart fileFormDataBodyPart = formDataMultiPart.getField(formDataKey);
        // {0}获取失败，请检查
        ObjectUtils.checkEmpty(fileFormDataBodyPart, FleaJerseyCommonException.class, "ERROR-JERSEY-COMMON0000000000", "【FILE = FormDataBodyPart】");

        return fileFormDataBodyPart;
    }

    /**
     * 添加文件表单信息
     *
     * @param file 文件对象
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public void addFileDataBodyPart(File file) throws CommonException {
        addFormDataBodyPart(file, FleaJerseyConstants.FormDataConstants.FORM_DATA_KEY_FILE, null);
    }

    /**
     * 添加字符数据表单信息
     *
     * @param formDataStr 表单数据字符串
     * @param formDataKey 表单数据键
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public void addFormDataBodyPart(String formDataStr, String formDataKey) throws CommonException {
        addFormDataBodyPart(formDataStr, formDataKey, null);
    }

    /**
     * 添加通用表单信息
     *
     * @param formDataObj 表单数据对象
     * @param formDataKey 表单数据键
     * @param <T>         表单数据类型
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public <T> void addFormDataBodyPart(T formDataObj, String formDataKey, MediaType formDataMediaType) throws CommonException {

        // {0}不能为空，请检查
        ObjectUtils.checkEmpty(formDataObj, FleaJerseyCommonException.class, "ERROR-JERSEY-COMMON0000000001", "【Form Data】");

        // {0}不能为空，请检查
        StringUtils.checkBlank(formDataKey, FleaJerseyCommonException.class, "ERROR-JERSEY-COMMON0000000001", "【Form Data Key】");

        // 获取文件上下文信息
        FleaJerseyFileContext fileContext = getFileContext();
        if (ObjectUtils.isNotEmpty(fileContext)) {
            FormDataMultiPart formDataMultiPart = fileContext.getFormDataMultiPart();
            if (ObjectUtils.isEmpty(formDataMultiPart)) {
                formDataMultiPart = new FormDataMultiPart();
                fileContext.setFormDataMultiPart(formDataMultiPart);
            }

            FormDataBodyPart formDataBodyPart;
            if (formDataObj instanceof File) {
                formDataBodyPart = new FileDataBodyPart(formDataKey, (File) formDataObj);
            } else if (formDataObj instanceof String) {
                formDataBodyPart = new FormDataBodyPart(formDataKey, (String) formDataObj);
            } else {
                formDataBodyPart = new FormDataBodyPart(formDataKey, formDataObj, formDataMediaType);
            }

            formDataMultiPart.bodyPart(formDataBodyPart);
        }
    }

}
