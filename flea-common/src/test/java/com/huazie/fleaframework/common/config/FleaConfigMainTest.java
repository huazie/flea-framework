package com.huazie.fleaframework.common.config;

import com.huazie.fleaframework.common.FleaConfigXmlDigesterHelper;
import com.huazie.fleaframework.common.config.concurrency.GetConfigItemTest;

/**
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaConfigMainTest {

    public static void main(String[] args) {
        FleaConfig config = FleaConfigXmlDigesterHelper.getInstance().getFleaConfig();
        ConfigItems configItems = config.getConfigItems("flea-jersey-client");
        GetConfigItemTest.testGetConfigItem(configItems);
    }

}
