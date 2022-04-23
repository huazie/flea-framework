package com.huazie.fleaframework.common.config;

import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置数据 Map
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public abstract class ConfigMap<T> {

    private Map<String, T> configMap = new HashMap<>(); // 配置数据Map

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
            configMap.put(getConfigKey(config), config);
        }
    }

    /**
     * 添加配置数据集
     *
     * @param configList 配置数据集
     * @since 2.0.0
     */
    protected void addConfigList(List<T> configList) {
        if (CollectionUtils.isNotEmpty(configList)) {
            for (T config : configList) {
                addConfig(config);
            }
        }
    }

    /**
     * 根据指定配置数据键获取指定的配置数据
     *
     * @param key 配置数据键
     * @return 配置数据
     * @since 2.0.0
     */
    public T getConfig(String key) {
        return configMap.get(key);
    }
}
