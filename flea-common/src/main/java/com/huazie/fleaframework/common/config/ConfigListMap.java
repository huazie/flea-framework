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

    private Map<String, List<T>> configListMap = null; // 配置数据集 Map

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
    public Map<String, List<T>> toConfigListMap() {
        if (ObjectUtils.isEmpty(configListMap)) {
            configListMap = new HashMap<>();
            for (T config : getConfigList()) {
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
        }
        return configListMap;
    }

    /**
     * 根据指定配置数据键获取指定的配置数据
     *
     * @param key 配置数据键
     * @return 配置数据
     * @since 2.0.0
     */
    public List<T> getConfigList(String key) {
        return toConfigListMap().get(key);
    }
}
