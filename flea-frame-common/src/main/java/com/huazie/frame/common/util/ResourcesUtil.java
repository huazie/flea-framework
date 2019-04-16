package com.huazie.frame.common.util;

import java.io.InputStream;

/**
 * <p> 资源文件获取工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ResourcesUtil {

    /**
     * <p> 根据文件路径获取文件输入流对象 </p>
     *
     * @param path 文件路径
     * @return 指定path文件的输入流对象
     */
    public static InputStream getInputStreamFromClassPath(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

}
