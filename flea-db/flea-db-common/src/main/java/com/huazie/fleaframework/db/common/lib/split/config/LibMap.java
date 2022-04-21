package com.huazie.fleaframework.db.common.lib.split.config;

import com.huazie.fleaframework.common.config.ConfigMap;

import java.util.List;

/**
 * 分库配置 Map
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public abstract class LibMap extends ConfigMap<Lib> {

    public abstract List<Lib> getLibList();

    @Override
    protected List<Lib> getConfigList() {
        return getLibList();
    }

    @Override
    protected String getConfigKey(Lib lib) {
        return lib.getName();
    }

    /**
     * 根据模板库名获取分库配置定义
     *
     * @param name 模板库名
     * @return 分库配置定义
     * @since 2.0.0
     */
    public Lib getFleaLib(String name) {
        return getConfig(name);
    }
}
