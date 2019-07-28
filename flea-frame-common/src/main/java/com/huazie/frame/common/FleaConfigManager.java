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
     * <p> 获取配置项列表 </p>
     *
     * @param itemsKey 配置项列表键
     * @return 配置项列表
     * @since 1.0.0
     */
    public static ConfigItems getConfigItems(String itemsKey) {
        FleaConfig config = FleaConfigXmlDigesterHelper.getInstance().getFleaConfig();
        ConfigItems configItems = config.getConfigItems(itemsKey);
        return configItems;
    }

    /**
     * <p> 获取配置项 (需要先获取配置项列表)</p>
     *
     * @param itemKey     配置项键
     * @param configItems 配置项列表
     * @return 配置项
     * @since 1.0.0
     */
    public static ConfigItem getConfigItem(String itemKey, ConfigItems configItems) {
        ConfigItem item = null;
        if (ObjectUtils.isNotEmpty(configItems)) {
            item = configItems.getConfigItem(itemKey);
        }
        return item;
    }

    /**
     * <p> 获取配置项 </p>
     *
     * @param itemsKey 配置项列表键
     * @param itemKey  配置项键
     * @return 配置项
     * @since 1.0.0
     */
    public static ConfigItem getConfigItem(String itemsKey, String itemKey) {
        return getConfigItem(itemKey, getConfigItems(itemsKey));
    }

    /**
     * <p> 获取配置项值 (需要先获取配置项列表)</p>
     *
     * @param itemKey     配置项键
     * @param configItems 配置项列表
     * @return 配置项值
     * @since 1.0.0
     */
    public static String getConfigItemValue(String itemKey, ConfigItems configItems) {
        return getConfigItemValue(getConfigItem(itemKey, configItems));
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
        return getConfigItemValue(getConfigItem(itemsKey, itemKey));
    }

    /**
     * <p> 获取配置项值 </p>
     *
     * @param item 配置项
     * @return 配置项值
     * @since 1.0.0
     */
    public static String getConfigItemValue(ConfigItem item) {
        String value = null;
        if (ObjectUtils.isNotEmpty(item)) {
            value = item.getValue();
        }
        return value;
    }

}
