package com.huazie.fleaframework.common.i18n.config;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.FleaConfigManager;
import com.huazie.fleaframework.common.config.ConfigItem;
import com.huazie.fleaframework.common.config.ConfigItems;
import com.huazie.fleaframework.common.i18n.pojo.FleaI18nData;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ArrayUtils;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Flea I18N 配置类，用于获取指定语言环境下的指定资源对应的国际化数据。
 *
 * <p> 它默认读取资源路径为 flea/i18n，资源文件前缀为 flea_i18n，当然
 * 也可以在 flea-config.xml 中为指定资源文件配置路径和前缀，从而可以
 * 实现读取任意位置的资源数据。
 *
 * <p> 可参考如下进行配置：
 * <pre>
 * {@code
 *   <config-items key="flea-i18n-config" desc="Flea国际化相关配置">
 *       <config-item key="error" desc="error国际化资源特殊配置，指定路径和文件前缀，逗号分隔">flea/i18n,flea_i18n</config-item>
 *   </config-items>
 * }
 * </pre>
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class FleaI18nConfig {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaI18nConfig.class);

    private static volatile FleaI18nConfig config;

    private ConcurrentMap<String, String> resFilePath = new ConcurrentHashMap<>(); // 资源文件路径集

    private ConcurrentMap<String, ResourceBundle> resources = new ConcurrentHashMap<>(); // 资源集

    /**
     * 只允许通过 getConfig() 获取 Flea I18N 配置类实例
     */
    private FleaI18nConfig() {
        init(); // 初始化资源文件相关配置
    }

    /**
     * 获取 Flea I18N 配置类实例
     *
     * @return Flea I18N 配置类实例
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
     * 初始化资源名和资源文件相关属性的映射关系
     *
     * @since 1.0.0
     */
    private void init() {
        ConfigItems fleaI18nItems = FleaConfigManager.getConfigItems(CommonConstants.FleaI18NConstants.FLEA_I18N_CONFIG_ITEMS_KEY);
        if (ObjectUtils.isNotEmpty(fleaI18nItems) && CollectionUtils.isNotEmpty(fleaI18nItems.getConfigItemList())) {
            for (ConfigItem configItem : fleaI18nItems.getConfigItemList()) {
                if (ObjectUtils.isNotEmpty(configItem) && StringUtils.isNotBlank(configItem.getKey()) && StringUtils.isNotBlank(configItem.getValue())) {
                    String[] valueArr = StringUtils.split(configItem.getValue(), CommonConstants.SymbolConstants.COMMA);
                    if (ArrayUtils.isNotEmpty(valueArr) && CommonConstants.NumeralConstants.INT_TWO == valueArr.length) {
                        // 获取资源文件路径
                        String filePath = StringUtils.trim(valueArr[0]);
                        // 获取资源文件前缀
                        String fileNamePrefix = StringUtils.trim(valueArr[1]);
                        if (StringUtils.isNotBlank(filePath) && StringUtils.isNotBlank(fileNamePrefix)) {
                            String configResFilePath;
                            // 如果资源文件路径最后没有 "/"，自动添加
                            if (CommonConstants.SymbolConstants.SLASH.equals(StringUtils.subStrLast(filePath, 1))) {
                                configResFilePath = filePath + fileNamePrefix;
                            } else {
                                configResFilePath = filePath + CommonConstants.SymbolConstants.SLASH + fileNamePrefix;
                            }
                            resFilePath.put(configItem.getKey(), configResFilePath);
                        }
                    }
                }
            }
        }
        // 添加默认资源文件路径
        String defaultResFilePath = CommonConstants.FleaI18NConstants.FLEA_I18N_FILE_PATH +
                CommonConstants.FleaI18NConstants.FLEA_I18N_FILE_NAME_PREFIX; // 默认资源文件路径（仅包含公共的部分）
        resFilePath.put(CommonConstants.SymbolConstants.ASTERISK, defaultResFilePath);
    }

    /**
     * 通过国际化数据的key，获取当前系统指定资源的国际化资源；
     * 其中国际化资源中使用 {} 标记的，需要values中的数据替换。
     *
     * @param key     国际化资源KEY
     * @param values  待替换字符串数组
     * @param resName 资源名
     * @param locale  国际化标识
     * @return 国际化资源数据
     * @since 2.0.0
     */
    public FleaI18nData getI18NData(String key, String[] values, String resName, Locale locale) {
        return new FleaI18nData(key, this.getI18NDataValue(key, values, resName, locale));
    }

    /**
     * 通过国际化数据的key，获取当前系统指定资源的国际化资源
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
     * 通过国际化数据的key，获取当前系统指定资源的国际化资源数据
     *
     * @param key     国际化资源KEY
     * @param values  国际化资源数据替换内容
     * @param resName 资源名
     * @param locale  国际化标识
     * @return 国际化资源数据
     * @since 1.0.0
     */
    public String getI18NDataValue(String key, String[] values, String resName, Locale locale) {
        return StringUtils.getRealValue(getI18NDataValue(key, resName, locale), values);
    }

    /**
     * 通过国际化数据的key，获取当前系统指定资源的国际化资源数据
     *
     * @param key     国际化资源KEY
     * @param resName 资源名
     * @param locale  国际化标识
     * @return 国际化资源数据
     * @since 1.0.0
     */
    public String getI18NDataValue(String key, String resName, Locale locale) {
        Object obj = obj = new Object() {};
        LOGGER.debug1(obj, "Find the key     : {}", key);
        LOGGER.debug1(obj, "Find the resName : {}", resName);
        LOGGER.debug1(obj, "Find the locale  : {} , {}", locale == null ? Locale.getDefault() : locale, locale == null ? Locale.getDefault().getDisplayLanguage() : locale.getDisplayLanguage());

        ResourceBundle resource = getResourceBundle(resName, locale);

        String value = null;
        if (ObjectUtils.isNotEmpty(resource)) {
            value = resource.getString(key);
            if (StringUtils.isBlank(value)) { // 如果取不到数据，则使用key返回
                value = key;
            }
        }

        LOGGER.debug1(obj, "Find the value   : {} ", value);
        return value;
    }

    /**
     * 根据资源名和国际化标识获取指定国际化配置ResourceBundle对象
     *
     * @param resName 资源名
     * @param locale  国际化标识
     * @return 国际化配置ResourceBundle对象
     * @since 1.0.0
     */
    private ResourceBundle getResourceBundle(String resName, Locale locale) {

        String key = generateKey(resName, locale);

        Object obj = new Object() {};
        LOGGER.debug1(obj, "Find the resKey  : {}", key);

        ResourceBundle resource = resources.get(key);

        // 获取资源文件名
        StringBuilder fileName = new StringBuilder(getResFilePath(resName));
        if (StringUtils.isNotBlank(resName)) {
            fileName.append(CommonConstants.SymbolConstants.UNDERLINE).append(resName);
        }

        if (ObjectUtils.isEmpty(locale)) {
            LOGGER.debug1(obj, "Find the expected fileName: {}.properties", fileName);
        } else {
            LOGGER.debug1(obj, "Find the expected fileName: {}_{}.properties", fileName, locale);
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

        Locale realLocale = resource.getLocale();
        if (ObjectUtils.isEmpty(locale) || StringUtils.isBlank(realLocale.toString())) {
            LOGGER.debug1(obj, "Find the real fileName: {}.properties", fileName);
        } else {
            LOGGER.debug1(obj, "Find the real fileName: {}_{}.properties", fileName, realLocale);
        }

        return resource;
    }

    /**
     * 获取国际化资源文件KEY。如果资源名不为空，则资源名作为key，
     * 同时如果国际化标识不为空，则取资源名+下划线+国际化语言作为key；
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
     * 根据资源名，获取资源文件路径
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
