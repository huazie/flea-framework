package com.huazie.fleaframework.common.config;

import com.huazie.fleaframework.common.util.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 配置项列表 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConfigItems extends ConfigKey {

    private List<ConfigItem> configItemList = new ArrayList<>(); // 配置项列表中的各个配置项

    public List<ConfigItem> getConfigItemList() {
        return configItemList;
    }

    /**
     * <p> 添加一个配置项 </p>
     *
     * @param configItem 配置项
     * @since 1.0.0
     */
    public void addConfigItem(ConfigItem configItem) {
        configItemList.add(configItem);
    }

    /**
     * <p> 获取指定配置项列表中的配置项的Map，便于根据各配置项key查找 </p>
     *
     * @return 配置项的Map
     * @since 1.0.0
     */
    public Map<String, ConfigItem> toConfigItemMap() {
        Map<String, ConfigItem> configItemMap = new HashMap<>();
        for (ConfigItem configItem : configItemList) {
            configItemMap.put(configItem.getKey(), configItem);
        }
        return configItemMap;
    }

    /**
     * <p> 根据Key获取指定的配置项 </p>
     *
     * @param key 配置项键
     * @return 配置项
     * @since 1.0.0
     */
    public ConfigItem getConfigItem(String key) {
        ConfigItem configItem = null;
        Map<String, ConfigItem> configItemMap = toConfigItemMap();
        if (MapUtils.isNotEmpty(configItemMap)) {
            configItem = configItemMap.get(key);
        }
        return configItem;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
