package com.huazie.frame.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * <p> 读取后缀为properties的配置文件 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class PropertiesUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * <p> 获取指定配置文件对象 </p>
     *
     * @param path 配置文件路径
     * @return 指定path的配置文件对象
     * @since 1.0.0
     */
    public static Properties getProperties(String path) {
        Properties prop = new Properties();
        InputStream input = null;
        BufferedReader reader = null;
        try {
            input = ResourcesUtil.getInputStreamFromClassPath(path);
            if (ObjectUtils.isEmpty(input)) {
                throw new Exception("该路径下找不到指定配置文件");
            }
            reader = new BufferedReader(new InputStreamReader(input));
            prop.load(reader);
        } catch (Exception e) {
            prop = null;
            LOGGER.error("PropertiesUtil##getProperties 读取路径为【" + path + "】的配置出错", e);
        } finally {
            try {
                if (ObjectUtils.isNotEmpty(reader)) {
                    reader.close();
                }
                if (ObjectUtils.isNotEmpty(input)) {
                    input.close();
                }
            } catch (Exception e) {
                prop = null;
                LOGGER.error("PropertiesUtil##getProperties 读取路径为【" + path + "】的配置出错", e);
            }
        }
        return prop;
    }

    /**
     * <p> 根据键，获取对应的值 </p>
     *
     * @param prop 配置对象
     * @param key  指定KEY值
     * @return 对应Object值
     * @since 1.0.0
     */
    public static Object getObjectValue(Properties prop, String key) {
        Object value = null;
        if (ObjectUtils.isNotEmpty(prop)) {
            value = prop.get(key);
        }
        return value;
    }

    /**
     * <p> 根据键，获取对应的字符串值 </p>
     *
     * @param prop 配置对象
     * @param key  指定KEY值
     * @return KEY对应键值
     * @since 1.0.0
     */
    public static String getStringValue(Properties prop, String key) {
        String value = null;
        if (ObjectUtils.isNotEmpty(prop)) {
            value = prop.getProperty(key);
        }
        return value;
    }

    /**
     * <p> 根据键，获取对应的Integer值 </p>
     *
     * @param prop 配置对象
     * @param key  指定KEY值
     * @return 对应Integer值
     * @since 1.0.0
     */
    public static Integer getIntegerValue(Properties prop, String key) {
        Integer value = null;
        String val = getStringValue(prop, key);
        if (StringUtils.isNotBlank(val)) {
            value = Integer.valueOf(val);
        }
        return value;
    }

    /**
     * <p> 根据键，获取对应的Long值 </p>
     *
     * @param prop 配置对象
     * @param key  指定KEY值
     * @return 对应Long值
     * @since 1.0.0
     */
    public static Long getLongValue(Properties prop, String key) {
        Long value = null;
        String val = getStringValue(prop, key);
        if (StringUtils.isNotBlank(val)) {
            value = Long.valueOf(val);
        }
        return value;
    }

    /**
     * <p> 根据键，获取对应的Boolean值 </p>
     *
     * @param prop 配置对象
     * @param key  指定KEY值
     * @return 对应Boolean值
     * @since 1.0.0
     */
    public static Boolean getBooleanValue(Properties prop, String key) {
        Boolean value = null;
        String val = getStringValue(prop, key);
        if (StringUtils.isNotBlank(val)) {
            value = Boolean.valueOf(val);
        }
        return value;
    }
}
