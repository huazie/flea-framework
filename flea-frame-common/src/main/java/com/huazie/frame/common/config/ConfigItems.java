package com.huazie.frame.common.config;

import com.huazie.frame.common.util.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p> 配置项列表 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ConfigItems {

    private String key; // 配置项列表键

    private String desc; // 配置项列表值

    private List<ConfigItem> configItemList = new ArrayList<ConfigItem>(); // 配置项列表中的各个配置项

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

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
        Map<String, ConfigItem> configItemMap = new HashMap<String, ConfigItem>();
        Iterator<ConfigItem> configItemIt = configItemList.iterator();
        while (configItemIt.hasNext()) {
            ConfigItem configItem = configItemIt.next();
            configItemMap.put(configItem.getKey(), configItem);
        }
        return configItemMap;
    }

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
