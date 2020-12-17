package com.huazie.frame.common;

import com.huazie.frame.common.util.IOUtils;
import com.huazie.frame.common.util.ObjectUtils;
import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * <p> Xml Digester 解析工具类</p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class XmlDigesterHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlDigesterHelper.class);

    /**
     * <p> Xml文件解析 </p>
     *
     * @param filePath Xml文件路径
     * @param digester Digester对象
     * @param clazz    xml文件映射的POJO类Class对象
     * @param <T>      POJO类对象的对象类型
     * @return xml文件映射的POJO类实例
     * @since 1.0.0
     */
    public static <T> T parse(String filePath, Digester digester, Class<T> clazz) {

        T config = null;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The File Path : {}", filePath);
        }

        try (InputStream input = IOUtils.getInputStreamFromClassPath(filePath)) {
            config = parse(input, digester, clazz);
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Exception = ", e);
            }
        }

        return config;
    }

    /**
     * <p> Xml文件解析 </p>
     *
     * @param input    Xml文件输入流
     * @param digester Digester对象
     * @param clazz    xml文件映射的POJO类Class对象
     * @param <T>      POJO类对象的对象类型
     * @return xml文件映射的POJO类实例
     * @since 1.0.0
     */
    public static <T> T parse(InputStream input, Digester digester, Class<T> clazz) throws Exception {

        T config = null;

        if (ObjectUtils.isNotEmpty(input)) {
            Object obj = digester.parse(input);
            if (clazz.isInstance(obj)) {
                config = clazz.cast(obj);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Config = {}", config);
        }

        return config;
    }

}
