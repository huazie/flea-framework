package com.huazie.frame.common;

import com.huazie.frame.common.config.ConfigItem;
import com.huazie.frame.common.config.ConfigItems;
import com.huazie.frame.common.config.FleaConfig;
import com.huazie.frame.common.util.ObjectUtils;

/**
 * <p> Flea Config Manager </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaConfigManager {

    /**
     * <p> 获取配置项 </p>
     *
     * @param itemsKey 配置项列表键
     * @param itemKey  配置项键
     * @return 配置项
     * @since 1.0.0
     */
    public static ConfigItem getConfigItem(String itemsKey, String itemKey) {
        FleaConfig config = FleaConfigXmlDigesterHelper.getInstance().getFleaConfig();
        ConfigItems configItems = config.getConfigItems(itemsKey);
        ConfigItem configItem = null;
        if (ObjectUtils.isNotEmpty(configItems)) {
            configItem = configItems.getConfigItem(itemKey);
        }
        return configItem;
    }

    /**
     * <p> 获取配置项值 </p>
     *
     * @param itemsKey 配置项列表键
     * @param itemKey  配置项键
     * @return 配置项值
     * @since 1.0.0
     */
    public static String getConfigItemValue(String itemsKey, String itemKey) {
        ConfigItem configItem = getConfigItem(itemsKey, itemKey);
        String value = null;
        if (ObjectUtils.isNotEmpty(configItem)) {
            value = configItem.getValue();
        }
        return value;
    }
}
