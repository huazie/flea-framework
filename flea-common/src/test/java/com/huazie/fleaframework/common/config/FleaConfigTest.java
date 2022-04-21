package com.huazie.fleaframework.common.config;

import com.huazie.fleaframework.common.FleaConfigXmlDigesterHelper;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ObjectUtils;
import org.junit.Test;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaConfigTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaConfigTest.class);

    @Test
    public void testFleaConfig() {
        FleaConfigXmlDigesterHelper xmlDigesterHelper = FleaConfigXmlDigesterHelper.getInstance();
        FleaConfig config = xmlDigesterHelper.getFleaConfig();
        LOGGER.debug("Config = {}", config);
    }

    @Test
    public void testConfigItem() {
        FleaConfig config = FleaConfigXmlDigesterHelper.getInstance().getFleaConfig();
        ConfigItems configItems = config.getConfigItems("flea-jersey-client");
        ConfigItem configItem1 = null;
        ConfigItem configItem2 = null;
        LOGGER.debug("Before ConfigItems = {}", configItems);
        if (ObjectUtils.isNotEmpty(configItems)) {
            configItem1 = configItems.getConfigItem("system_account_id");
            configItem2 = configItems.getConfigItem("system_account_pwd");
        }
        LOGGER.debug("ConfigItem1 = {}", configItem1);
        LOGGER.debug("ConfigItem2 = {}", configItem2);
        LOGGER.debug("After ConfigItems = {}", configItems);
    }

}
