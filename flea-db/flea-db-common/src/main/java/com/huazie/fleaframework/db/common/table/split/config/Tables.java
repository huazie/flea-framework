package com.huazie.fleaframework.db.common.table.split.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 分表配置集，参考 flea-table-split.xml 中 {@code <tables></tables>}
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class Tables extends TableMap {

    private List<Table> tableList = new ArrayList<>();

    public List<Table> getTableList() {
        return tableList;
    }

    /**
     * 添加分表配置
     *
     * @param table 分表配置
     * @since 2.0.0
     */
    public void addTable(Table table) {
        tableList.add(table);
        addConfig(table);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
