package com.huazie.frame.common.i18n.config;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.FleaConfigManager;
import com.huazie.frame.common.config.ConfigItem;
import com.huazie.frame.common.config.ConfigItems;
import com.huazie.frame.common.i18n.pojo.FleaI18nData;
import com.huazie.frame.common.util.ArrayUtils;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.MapUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * <p> flea i18n 配置类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaI18nConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaI18nConfig.class);

    private static volatile FleaI18nConfig config;

    private String defaultResFilePath = CommonConstants.FleaI18NConstants.FLEA_I18N_FILE_PATH +
            CommonConstants.FleaI18NConstants.FLEA_I18N_FILE_NAME_PREFIX; // 默认资源文件路径（仅包含公共的部分）

    private Map<String, String> resFilePath = new HashMap<String, String>(); // 资源文件路径集，默认取defaultResFilePath

    private Map<String, ResourceBundle> resources = new HashMap<String, ResourceBundle>(); // 资源集合

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
                    config.init(); // 初始化资源文件相关属性
                }
            }
        }
        return config;
    }

    /**
     * <p> 初始化资源文件相关属性 </p>
     *
     * @since 1.0.0
     */
    private void init() {
        ConfigItems fleaI18nItems = FleaConfigManager.getConfigItems(CommonConstants.FleaI18NConstants.FLEA_I18N_CONFIG_ITEMS_KEY);
        if (ObjectUtils.isNotEmpty(fleaI18nItems)) {
            // 获取资源名的相关配置
            Map<String, ConfigItem> configItemMap = fleaI18nItems.toConfigItemMap();
            if (MapUtils.isNotEmpty(configItemMap)) {
                Set<String> configItemKeySet = configItemMap.keySet();
                if (CollectionUtils.isNotEmpty(configItemKeySet)) {
                    for (String configItemKey : configItemKeySet) {
                        ConfigItem configItem = configItemMap.get(configItemKey);
                        if (ObjectUtils.isNotEmpty(configItem)) {
                            String configItemValue = configItem.getValue();
                            if (ObjectUtils.isNotEmpty(configItemValue)) {
                                String[] valueArr = StringUtils.split(configItemValue, CommonConstants.SymbolConstants.COMMA);
                                if (ArrayUtils.isNotEmpty(valueArr) && CommonConstants.NumeralConstants.INT_TWO == valueArr.length) {
                                    // 获取资源文件路径
                                    String filePath = StringUtils.trim(valueArr[0]);
                                    // 获取资源文件前缀
                                    String fileNamePrefix = StringUtils.trim(valueArr[1]);
                                    if (ObjectUtils.isNotEmpty(filePath) && ObjectUtils.isNotEmpty(fileNamePrefix)) {
                                        String configResFilePath;
                                        // 如果资源文件路径最后没有 "/"，自动添加
                                        if (CommonConstants.SymbolConstants.SLASH.equals(StringUtils.subStrLast(filePath, 1))) {
                                            configResFilePath = filePath + fileNamePrefix;
                                        } else {
                                            configResFilePath = filePath + CommonConstants.SymbolConstants.SLASH + fileNamePrefix;
                                        }
                                        resFilePath.put(configItemKey, configResFilePath);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // 添加默认资源文件路径
        resFilePath.put(CommonConstants.SymbolConstants.ASTERISK, defaultResFilePath);
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
        if (ObjectUtils.isNotEmpty(resource)) {
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
        StringBuilder fileName = new StringBuilder(getResFilePath(resName));
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

    /**
     * <p> 根据资源名，获取资源文件路径 </p>
     *
     * @param resName 资源名
     * @return 资源文件路径
     * @since 1.0.0
     */
    private String getResFilePath(String resName) {
        // 首先根据资源名，从 资源文件路径集中获取
        String resFilePathStr = resFilePath.get(resName);
        if (ObjectUtils.isEmpty(resFilePathStr)) {
            // 取默认资源文件路径
            resFilePathStr = resFilePath.get(CommonConstants.SymbolConstants.ASTERISK);
        }
        return resFilePathStr;
    }

}
