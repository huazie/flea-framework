package com.huazie.fleaframework.db.common.table.split.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分表配置集合定义，参考 flea-table-split.xml 中 {@code <tables></tables>}
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Tables {

    private List<Table> tableList = new ArrayList<>();

    public List<Table> getTableList() {
        return tableList;
    }

    public Table[] getTableArray() {
        return tableList.toArray(new Table[0]);
    }

    /**
     * <p> 获取各分表配置 </p>
     *
     * @return 各分表配置集合
     * @since 1.0.0
     */
    Map<String, Table> toTableMap() {
        Map<String, Table> tableMap = new HashMap<>();
        for (Table table : tableList) {
            tableMap.put(table.getName(), table);
        }
        return tableMap;
    }

    public void addTable(Table table) {
        tableList.add(table);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
