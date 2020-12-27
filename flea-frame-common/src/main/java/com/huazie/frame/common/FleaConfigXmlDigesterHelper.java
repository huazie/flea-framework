package com.huazie.frame.common;

import com.huazie.frame.common.config.ConfigItem;
import com.huazie.frame.common.config.ConfigItems;
import com.huazie.frame.common.config.FleaConfig;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import org.apache.commons.digester.Digester;

/**
 * <p> Flea Config XML解析类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaConfigXmlDigesterHelper {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaConfigXmlDigesterHelper.class);

    private static volatile FleaConfigXmlDigesterHelper xmlDigester;

    private static Boolean isInit = Boolean.FALSE;
    private static Boolean isFleaConfigInit = Boolean.FALSE;

    private static FleaConfig fleaConfig;

    /**
     * <p> 只允许通过getInstance()获取 XML解析类 </p>
     */
    private FleaConfigXmlDigesterHelper() {
    }

    /**
     * <p> 获取XML工具类 </p>
     *
     * @return XML解析工具类对象
     * @since 1.0.0
     */
    public static FleaConfigXmlDigesterHelper getInstance() {
        if (isInit.equals(Boolean.FALSE)) {
            synchronized (isInit) {
                if (isInit.equals(Boolean.FALSE)) {
                    xmlDigester = new FleaConfigXmlDigesterHelper();
                    isInit = Boolean.TRUE;
                }
            }
        }
        return xmlDigester;
    }

    /**
     * <p> 获取Jersey配置 </p>
     *
     * @return Jersey配置对象
     * @since 1.0.0
     */
    public FleaConfig getFleaConfig() {
        if (ObjectUtils.isEmpty(fleaConfig)) {
            synchronized (isFleaConfigInit) {
                if (isFleaConfigInit.equals(Boolean.FALSE)) {
                    try {
                        fleaConfig = newFleaConfig();
                        isFleaConfigInit = Boolean.TRUE;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return fleaConfig;
    }

    private FleaConfig newFleaConfig() {

        String fileName = CommonConstants.FleaConfigConstants.FLEA_CONFIG_FILE_NAME;
        if (StringUtils.isNotBlank(System.getProperty(CommonConstants.FleaConfigConstants.FLEA_CONFIG_FILE_SYSTEM_KEY))) {
            fileName = StringUtils.trim(System.getProperty(CommonConstants.FleaConfigConstants.FLEA_CONFIG_FILE_SYSTEM_KEY));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Use the specified flea-config.xml : " + fileName);
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Use the current flea-config.xml : " + fileName);
            LOGGER.debug("Start to parse the flea-config.xml");
        }

        FleaConfig obj = XmlDigesterHelper.parse(fileName, newFleaConfigFileDigester(), FleaConfig.class);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Config = {}", obj);
            LOGGER.debug("End to parse the flea-config.xml");
        }

        return obj;
    }

    /**
     * <p> 解析flea-config.xml的Digester对象 </p>
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
