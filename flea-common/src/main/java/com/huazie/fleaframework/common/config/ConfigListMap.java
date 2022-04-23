package com.huazie.fleaframework.common.config;

import com.huazie.fleaframework.common.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置数据集 Map
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public abstract class ConfigListMap<T> {

    private Map<String, List<T>> configListMap = new HashMap<>(); // 配置数据集 Map

    /**
     * 获取配置数据键
     *
     * @return 配置数据键
     * @since 2.0.0
     */
    protected abstract String getConfigKey(T t);

    /**
     * 添加配置数据
     *
     * @param config 配置数据
     * @since 2.0.0
     */
    protected void addConfig(T config) {
        if (ObjectUtils.isNotEmpty(config)) {
            String key = getConfigKey(config);
            List<T> configList;
            if (configListMap.containsKey(key)) {
                configList = configListMap.get(key);
                configList.add(config);
            } else {
                configList = new ArrayList<>();
                configList.add(config);
                configListMap.put(key, configList);
            }
        }
    }

    /**
     * 根据指定配置数据键获取指定的配置数据集
     *
     * @param key 配置数据键
     * @return 配置数据集
     * @since 2.0.0
     */
    public List<T> getConfigList(String key) {
        return configListMap.get(key);
    }
}
