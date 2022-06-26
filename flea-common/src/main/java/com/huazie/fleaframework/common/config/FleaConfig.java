package com.huazie.fleaframework.common.config;

import com.huazie.fleaframework.common.util.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Flea配置数据，可在【flea-config.xml】文件中查看
 * {@code <flea-config> </flea-config>} 节点。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaConfig {

    private List<ConfigItems> configItemsList = new ArrayList<>();

    /**
     * 获取Flea配置数据
     *
     * @return Flea配置数据
     * @since 1.0.0
     */
    public List<ConfigItems> getConfigItemsList() {
        return configItemsList;
    }

    /**
     * 获取Flea配置数据
     *
     * @return Flea配置数据
     * @since 1.0.0
     */
    public ConfigItems[] toConfigItemsArray() {
        return configItemsList.toArray(new ConfigItems[0]);
    }

    /**
     * 添加一个配置项列表
     *
     * @param configItems 配置项列表
     * @since 1.0.0
     */
    public void addConfigItems(ConfigItems configItems) {
        configItemsList.add(configItems);
    }

    /**
     * 获取配置项列表的Map，便于根据各配置项列表key查找
     *
     * @return 配置项列表的Map
     * @since 1.0.0
     */
    public Map<String, ConfigItems> toConfigItemsMap() {
        Map<String, ConfigItems> configItemsMap = new HashMap<>();
        for (ConfigItems configItems : configItemsList) {
            configItemsMap.put(configItems.getKey(), configItems);
        }
        return configItemsMap;
    }

    /**
     * 根据指定key，获取某个指定的配置项列表
     *
     * @param key 指定配置项列表的key
     * @return 某个指定的配置项列表
     * @since 1.0.0
     */
    public ConfigItems getConfigItems(String key) {
        ConfigItems configItems = null;
        Map<String, ConfigItems> configItemsMap = toConfigItemsMap();
        if (MapUtils.isNotEmpty(configItemsMap)) {
            configItems = configItemsMap.get(key);
        }
        return configItems;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
