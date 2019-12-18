package com.huazie.frame.jersey.common;

import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.jersey.common.data.FleaJerseyContext;
import com.huazie.frame.jersey.common.data.FleaJerseyFileContext;
import com.huazie.frame.jersey.common.exception.FleaJerseyCommonException;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import java.io.File;

/**
 * <p> Flea Jersey 管理类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyManager {

    private static volatile FleaJerseyManager manager;

    private static ThreadLocal<FleaJerseyContext> sContext = new ThreadLocal<FleaJerseyContext>(); // Flea Jersey 上下文

    /**
     * <p> 构造器私有，只能通过getManager()获取实例 </p>
     */
    private FleaJerseyManager() {
    }

    /**
     * <p> 获取一个Flea Jersey管理类实例 </p>
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
     * <p> 设置Flea Jersey上下文对象 </p>
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
     * <p> 获取Flea Jersey上下文对象 </p>
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
     * <p> 获取文件上下文 </p>
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
     * <p> 获取文件表单信息 </p>
     *
     * @return 文件表单数据
     * @throws FleaJerseyCommonException Flea Jersey通用异常
     * @since 1.0.0
     */
    public FormDataBodyPart getFileFormDataBodyPart() throws FleaJerseyCommonException {

        // 获取文件上下文信息
        FleaJerseyFileContext fleaJerseyFileContext = FleaJerseyManager.getManager().getContext().getFleaJerseyFileContext();
        if (ObjectUtils.isEmpty(fleaJerseyFileContext)) {
            // {0}获取失败，请检查
            throw new FleaJerseyCommonException("ERROR-JERSEY-COMMON0000000000", "【FleaJerseyFileContext】");
        }

        // 获取表单数据
        FormDataMultiPart formDataMultiPart = fleaJerseyFileContext.getFormDataMultiPart();
        if (ObjectUtils.isEmpty(formDataMultiPart)) {
            // {0}获取失败，请检查
            throw new FleaJerseyCommonException("ERROR-JERSEY-COMMON0000000000", "【FormDataMultiPart】");
        }

        // 获取文件表单数据内容
        FormDataBodyPart fileFormDataBodyPart = formDataMultiPart.getField(FleaJerseyConstants.FormDataConstants.FORM_DATA_KEY_FILE);
        if (ObjectUtils.isEmpty(fileFormDataBodyPart)) {
            // {0}获取失败，请检查
            throw new FleaJerseyCommonException("ERROR-JERSEY-COMMON0000000000", "【FILE = FormDataBodyPart】");
        }

        return fileFormDataBodyPart;
    }

    /**
     * <p> 设置文件表单信息 </p>
     *
     * @param file 文件对象
     * @throws FleaJerseyCommonException Flea Jersey通用异常
     * @since 1.0.0
     */
    public void addFileDataBodyPart(File file) throws FleaJerseyCommonException {

        if (ObjectUtils.isEmpty(file) || !file.exists()) {
            // {0}获取失败，请检查
            throw new FleaJerseyCommonException("ERROR-JERSEY-COMMON0000000000", "【File】");
        }

        // 获取文件上下文信息
        FleaJerseyFileContext fileContext = getFileContext();
        if (ObjectUtils.isNotEmpty(fileContext)) {
            FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
            // 添加文件表单信息
            FileDataBodyPart fileDataBodyPart = new FileDataBodyPart(FleaJerseyConstants.FormDataConstants.FORM_DATA_KEY_FILE, file);
            formDataMultiPart.bodyPart(fileDataBodyPart);
            // 添加到文件上下文中
            fileContext.setFormDataMultiPart(formDataMultiPart);
        }
    }

}
