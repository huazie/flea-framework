package com.huazie.frame.common.i18n.config;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.i18n.pojo.FleaI18nData;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.PropertiesUtil;
import com.huazie.frame.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * <p> flea i18n 配置类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaI18nConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaI18nConfig.class);

    private static volatile FleaI18nConfig config;

    private static String RES_FILE_NAME = CommonConstants.FleaI18NConstants.FLEA_I18N_FILE_PATH + CommonConstants.FleaI18NConstants.FLEA_I18N_FILE_NAME_PREFIX;

    private static Map<String, ResourceBundle> resources = new HashMap<String, ResourceBundle>();//资源集合

    static {
        // Flea i18n config
        String fileName = CommonConstants.FleaI18NConstants.FLEA_I18N_CONFIG_FILE_NAME;
        if (StringUtils.isNotBlank(System.getProperty("fleaframe.i18n.config.filename"))) {
            fileName = StringUtils.trim(System.getProperty("fleaframe.i18n.config.filename"));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("FleaI18nConfig Use the specified flea_i18n_config.properties：{}", fileName);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaI18nConfig Use the current flea_i18n_config.properties：{}", fileName);
        }
        Properties fleaI18nProp = PropertiesUtil.getProperties(fileName);
        if (ObjectUtils.isNotEmpty(fleaI18nProp)) {
            // 获取资源文件路径
            String filePath = PropertiesUtil.getStringValue(fleaI18nProp, CommonConstants.FleaI18NConstants.FLEA_I18N_CONFIG_KEY_FILE_PATH);
            // 获取资源文件前缀
            String fileNamePrefix = PropertiesUtil.getStringValue(fleaI18nProp, CommonConstants.FleaI18NConstants.FLEA_I18N_CONFIG_KEY_FILE_NAME_PREFIX);
            // 如果资源文件路径最后没有 "/"，自动添加
            if (CommonConstants.SymbolConstants.SLASH.equals(StringUtils.subStrLast(filePath, 1))) {
                RES_FILE_NAME = filePath + fileNamePrefix;
            } else {
                RES_FILE_NAME = filePath + CommonConstants.SymbolConstants.SLASH + fileNamePrefix;
            }
        }
    }

    /**
     * <p> 只允许通过getConfig()获取 flea i18n配置类实例 </p>
     */
    private FleaI18nConfig() {
    }

    /**
     * <p>获取Flea i18n 配置类实例</p>
     *
     * @return Flea I18n 配置类实例
     * @since 1.0.0
     */
    public static FleaI18nConfig getConfig() {
        if (ObjectUtils.isEmpty(config)) {
            synchronized (FleaI18nConfig.class) {
                if (ObjectUtils.isEmpty(config)) {
                    config = new FleaI18nConfig();
                }
            }
        }
        return config;
    }

    /**
     * <p> 通过国际化数据的key，获取当前系统指定资源的国际化资源 </p>
     *
     * @param key     国际化资源KEY
     * @param resName 资源名
     * @param locale  国际化标识
     * @return 国际化资源数据
     * @since 1.0.0
     */
    public FleaI18nData getI18NData(String key, String resName, Locale locale) {
        return new FleaI18nData(key, this.getI18NDataValue(key, resName, locale));
    }

    /**
     * <p> 通过国际化数据的key，获取当前系统指定资源的国际化资源数据 </p>
     *
     * @param key     国际化资源KEY
     * @param values  国际化资源数据替换内容
     * @param resName 资源名
     * @param locale  国际化标识
     * @return 国际化资源数据
     * @since 1.0.0
     */
    public String getI18NDataValue(String key, String[] values, String resName, Locale locale) {
        String value = getI18NDataValue(key, resName, locale);
        if (ArrayUtils.isNotEmpty(values)) {
            StringBuilder builder = new StringBuilder(value);
            for (int i = 0; i < values.length; i++) {
                StringUtils.replace(builder, CommonConstants.SymbolConstants.LEFT_CURLY_BRACE + i + CommonConstants.SymbolConstants.RIGHT_CURLY_BRACE, values[i]);
            }
            value = builder.toString();
        }
        return value;
    }

    /**
     * <p> 通过国际化数据的key，获取当前系统指定资源的国际化资源数据 </p>
     *
     * @param key     国际化资源KEY
     * @param resName 资源名
     * @param locale  国际化标识
     * @return 国际化资源数据
     * @since 1.0.0
     */
    public String getI18NDataValue(String key, String resName, Locale locale) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Find the key     : {}", key);
            LOGGER.debug("Find the resName : {}", resName);
            LOGGER.debug("Find the locale  : {} , {}", locale == null ? Locale.getDefault() : locale, locale == null ? Locale.getDefault().getDisplayLanguage() : locale.getDisplayLanguage());
        }
        ResourceBundle resource = getResourceBundle(resName, locale);

        String value = null;
        if (null != resource) {
            value = resource.getString(key);
            if (StringUtils.isBlank(value)) { // 如果取不到数据，则使用key返回
                value = key;
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Find the value   : {} ", value);
        }
        return value;
    }

    /**
     * <p> 根据资源名和国际化标识获取指定国际化配置ResourceBundle对象 </p>
     *
     * @param resName 资源名
     * @param locale  国际化标识
     * @return 国际化配置ResourceBundle对象
     * @since 1.0.0
     */
    private ResourceBundle getResourceBundle(String resName, Locale locale) {

        String key = generateKey(resName, locale);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Find the resKey  : {}", key);
        }

        ResourceBundle resource = resources.get(key);

        // 获取资源文件名
        StringBuilder fileName = new StringBuilder(RES_FILE_NAME);
        if (StringUtils.isNotBlank(resName)) {
            fileName.append(CommonConstants.SymbolConstants.UNDERLINE).append(resName);
        }

        if (LOGGER.isDebugEnabled()) {
            if (ObjectUtils.isEmpty(locale)) {
                LOGGER.debug("Find the fileName: {}.properties", fileName);
            } else {
                LOGGER.debug("Find the fileName: {}_{}.properties", fileName, locale);
            }
        }

        // 获取资源文件
        if (ObjectUtils.isEmpty(resource)) {
            if (ObjectUtils.isEmpty(locale)) {
                resource = ResourceBundle.getBundle(fileName.toString());
            } else {
                resource = ResourceBundle.getBundle(fileName.toString(), locale);
            }
            resources.put(key, resource);
        }

        return resource;
    }

    /**
     * <p> 获取国际化资源文件KEY </p>
     * <p> 如果资源名不为空，则资源名作为key，同时如果国际化标识不为空，则取资源名+下划线+国际化语言作为key；
     *
     * @param resName 资源名
     * @param locale  国际化标识
     * @return 国际化资源文件KEY
     * @since 1.0.0
     */
    private String generateKey(String resName, Locale locale) {
        String key = "";
        if (StringUtils.isNotBlank(resName)) {
            key = resName;
            if (ObjectUtils.isNotEmpty(locale)) {
                key += CommonConstants.SymbolConstants.UNDERLINE + locale;
            }
        }
        return key;
    }

}
