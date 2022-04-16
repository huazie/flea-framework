package com.huazie.fleaframework.db.common.table.split.config;

import com.huazie.fleaframework.common.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分表配置 Map
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public abstract class TableMap {

    private Map<String, Table> tableMap = null; // 分表配置文件中分表配置Map

    public abstract List<Table> getTableList();

    /**
     * 获取分表配置文件中分表配置定义的Map，便于根据各模板表名查找
     *
     * @return 分表配置定义的Map
     * @since 2.0.0
     */
    public Map<String, Table> toTableMap() {
        if (ObjectUtils.isEmpty(tableMap)) {
            tableMap = new HashMap<>();
            for (Table table : getTableList()) {
                tableMap.put(table.getName(), table);
            }
        }
        return tableMap;
    }

    /**
     * 根据模板表名获取指定的分表配置定义
     *
     * @param name 模板表名
     * @return 分表配置定义
     * @since 2.0.0
     */
    public Table getFleaTable(String name) {
        return toTableMap().get(name);
    }
}
