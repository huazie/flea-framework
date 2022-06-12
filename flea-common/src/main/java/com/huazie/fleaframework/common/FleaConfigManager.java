package com.huazie.fleaframework.common;

import com.huazie.fleaframework.common.config.ConfigItem;
import com.huazie.fleaframework.common.config.ConfigItems;
import com.huazie.fleaframework.common.config.FleaConfig;
import com.huazie.fleaframework.common.util.ObjectUtils;

/**
 * Flea Config Manager
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaConfigManager {

    private FleaConfigManager() {
    }

    /**
     * 获取配置项列表
     *
     * @param itemsKey 配置项列表键
     * @return 配置项列表
     * @since 1.0.0
     */
    public static ConfigItems getConfigItems(String itemsKey) {
        FleaConfig config = FleaConfigXmlDigesterHelper.getInstance().getFleaConfig();
        return config.getConfigItems(itemsKey);
    }

    /**
     * 获取配置项 (需要先获取配置项列表)
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
     * 获取配置项
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
     * 获取配置项值 (需要先获取配置项列表)
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
     * 获取配置项值
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
     * 获取配置项值
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
