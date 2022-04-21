package com.huazie.fleaframework.common.config;

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

    private Map<String, T> configMap = null; // 配置数据Map

    /**
     * 获取配置数据集合
     *
     * @return 配置数据集合
     * @since 2.0.0
     */
    protected abstract List<T> getConfigList();

    /**
     * 获取配置数据键
     *
     * @return 配置数据键
     * @since 2.0.0
     */
    protected abstract String getConfigKey(T t);

    /**
     * 获取配置数据Map
     *
     * @return 配置数据Map
     * @since 2.0.0
     */
    public Map<String, T> toConfigMap() {
        if (ObjectUtils.isEmpty(configMap)) {
            configMap = new HashMap<>();
            for (T config : getConfigList()) {
                configMap.put(getConfigKey(config), config);
            }
        }
        return configMap;
    }

    /**
     * 根据指定配置数据键获取指定的配置数据
     *
     * @param key 配置数据键
     * @return 配置数据
     * @since 2.0.0
     */
    public T getConfig(String key) {
        return toConfigMap().get(key);
    }
}
