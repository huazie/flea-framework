package com.huazie.fleaframework.common.util;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 读取后缀为properties的配置文件
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class PropertiesUtil {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(PropertiesUtil.class);

    private PropertiesUtil() {
    }

    /**
     * 获取指定配置文件对象
     *
     * @param path 配置文件路径
     * @return 指定path的配置文件对象
     * @since 1.0.0
     */
    public static Properties getProperties(String path) {
        Properties prop = new Properties();
        try (InputStream input = IOUtils.getInputStreamFromClassPath(path);
             InputStreamReader inputStreamReader = new InputStreamReader(input);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            prop.load(reader);
        } catch (Exception e) {
            prop = null;
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error1(new Object() {},"读取路径为【" + path + "】的配置出错", e);
            }
        }
        return prop;
    }

    /**
     * 根据键，获取对应的值
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
     * 根据键，获取对应的字符串值
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
     * 根据键，获取对应的Integer值
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
     * 根据键，获取对应的Long值
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
     * 根据键，获取对应的Boolean值
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
