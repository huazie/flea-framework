package com.huazie.fleaframework.db.common.table.split.config;

import com.huazie.fleaframework.common.util.MapUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 其他分表配置文件，对应【flea-table-split.xml】中
 * 【{@code <table-file> </table-file>}】
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class TableFile {

    private String location; // 文件位置

    private List<Table> tableList = new ArrayList<>(); // 其他分表配置文件中定义的分表配置定义集合

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }

    /**
     * 获取分表配置文件中分表配置定义的Map，便于根据各主表名查找
     *
     * @return 分表配置定义的Map
     * @since 2.0.0
     */
    public Map<String, Table> toTableFileFleaTableMap() {
        Map<String, Table> fleaTableMap = new HashMap<>();
        for (Table table : tableList) {
            fleaTableMap.put(table.getName(), table);
        }
        return fleaTableMap;
    }

    /**
     * 根据主表名获取指定的分表配置定义
     *
     * @param name 主表名
     * @return 分表配置定义
     * @since 2.0.0
     */
    public Table getFleaTable(String name) {
        Table table = null;
        Map<String, Table> fleaTableMap = toTableFileFleaTableMap();
        if (MapUtils.isNotEmpty(fleaTableMap)) {
            table = fleaTableMap.get(name);
        }
        return table;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
