package com.huazie.fleaframework.db.common.lib.split.config;

import com.huazie.fleaframework.common.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分库配置 Map
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public abstract class LibMap {

    private Map<String, Lib> libMap = null;

    public abstract List<Lib> getLibList();

    /**
     * 获取各分库配置定义
     *
     * @return 各分库配置定义集合
     * @since 2.0.0
     */
    public Map<String, Lib> toLibMap() {
        if (ObjectUtils.isEmpty(libMap)) {
            libMap = new HashMap<>();
            for (Lib lib : getLibList()) {
                libMap.put(lib.getName(), lib);
            }
        }
        return libMap;
    }

    /**
     * 根据模板库名获取分库配置定义
     *
     * @param name 模板库名
     * @return 分库配置定义
     * @since 2.0.0
     */
    public Lib getFleaLib(String name) {
        return toLibMap().get(name);
    }
}
