package com.huazie.fleaframework.common;

import com.huazie.fleaframework.common.config.ConfigItem;
import com.huazie.fleaframework.common.config.ConfigItems;
import com.huazie.fleaframework.common.config.FleaConfig;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.common.util.xml.XmlDigesterHelper;
import org.apache.commons.digester.Digester;

/**
 * Flea Config XML解析类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaConfigXmlDigesterHelper {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaConfigXmlDigesterHelper.class);

    private static volatile FleaConfigXmlDigesterHelper xmlDigester;

    private static final Object fleaConfigInitLock = new Object();

    private static FleaConfig fleaConfig;

    /**
     * 只允许通过getInstance()获取 XML解析类
     */
    private FleaConfigXmlDigesterHelper() {
    }

    /**
     * 获取XML工具类
     *
     * @return XML解析工具类对象
     * @since 1.0.0
     */
    public static FleaConfigXmlDigesterHelper getInstance() {
        if (ObjectUtils.isEmpty(xmlDigester)) {
            synchronized (FleaConfigXmlDigesterHelper.class) {
                if (ObjectUtils.isEmpty(xmlDigester)) {
                    xmlDigester = new FleaConfigXmlDigesterHelper();
                }
            }
        }
        return xmlDigester;
    }

    /**
     * 获取Flea配置数据
     *
     * @return Flea配置数据
     * @since 1.0.0
     */
    public FleaConfig getFleaConfig() {
        if (ObjectUtils.isEmpty(fleaConfig)) {
            synchronized (fleaConfigInitLock) {
                if (ObjectUtils.isEmpty(fleaConfig)) {
                    fleaConfig = newFleaConfig();
                }
            }
        }
        return fleaConfig;
    }

    private FleaConfig newFleaConfig() {

        String fileName = CommonConstants.FleaConfigConstants.FLEA_CONFIG_FILE_NAME;
        if (StringUtils.isNotBlank(System.getProperty(CommonConstants.FleaConfigConstants.FLEA_CONFIG_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(CommonConstants.FleaConfigConstants.FLEA_CONFIG_FILE_SYSTEM_KEY));
            LOGGER.debug("Use the specified flea-config.xml : " + fileName);
        }

        LOGGER.debug("Use the current flea-config.xml : " + fileName);
        LOGGER.debug("Start to parse the flea-config.xml");

        FleaConfig obj = XmlDigesterHelper.parse(fileName, newFleaConfigFileDigester(), FleaConfig.class);

        LOGGER.debug("End to parse the flea-config.xml");

        return obj;
    }

    /**
     * 解析flea-config.xml的Digester对象
     *
     * @return Digester对象
     * @since 1.0.0
     */
    private Digester newFleaConfigFileDigester() {
        Digester digester = new Digester();
        digester.setValidating(false);

        digester.addObjectCreate("flea-config", FleaConfig.class.getName());
        digester.addSetProperties("flea-config");

        digester.addObjectCreate("flea-config/config-items", ConfigItems.class.getName());
        digester.addSetProperties("flea-config/config-items");

        digester.addObjectCreate("flea-config/config-items/config-item", ConfigItem.class.getName());
        digester.addSetProperties("flea-config/config-items/config-item");
        digester.addBeanPropertySetter("flea-config/config-items/config-item", "value");

        digester.addSetNext("flea-config/config-items", "addConfigItems", ConfigItems.class.getName());

        digester.addSetNext("flea-config/config-items/config-item", "addConfigItem", ConfigItem.class.getName());
        return digester;
    }

}
