package com.huazie.fleaframework.db.common.table.split.config;

import com.huazie.fleaframework.common.config.ConfigMap;

/**
 * 分表配置 Map
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public abstract class TableMap extends ConfigMap<Table> {

    @Override
    protected String getConfigKey(Table table) {
        return table.getName();
    }

    /**
     * 根据模板表名获取指定的分表配置定义
     *
     * @param name 模板表名
     * @return 分表配置定义
     * @since 2.0.0
     */
    public Table getFleaTable(String name) {
        return getConfig(name);
    }
}
