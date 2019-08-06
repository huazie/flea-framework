package com.huazie.frame.common.config;

import com.huazie.frame.common.FleaConfigXmlDigesterHelper;
import com.huazie.frame.common.util.ObjectUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaConfigTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaConfigTest.class);

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
        if (ObjectUtils.isNotEmpty(configItems)) {
            configItem1 = configItems.getConfigItem("system_user_id");
            configItem2 = configItems.getConfigItem("system_user_pwd");
        }
        LOGGER.debug("ConfigItem1 = {}", configItem1);
        LOGGER.debug("ConfigItem2 = {}", configItem2);
    }

}
