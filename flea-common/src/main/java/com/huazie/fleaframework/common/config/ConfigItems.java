package com.huazie.fleaframework.common.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置项集，可在【flea-config.xml】文件中查看
 * {@code <config-items> </config-items> }节点。
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class ConfigItems extends ConfigKeyMap<ConfigItem> {

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
        addConfig(configItem);
    }

    @Override
    protected String getConfigKey(ConfigItem configItem) {
        return configItem.getKey();
    }

    /**
     * <p> 根据Key获取指定的配置项 </p>
     *
     * @param key 配置项键
     * @return 配置项
     * @since 1.0.0
     */
    public ConfigItem getConfigItem(String key) {
        return getConfig(key);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
