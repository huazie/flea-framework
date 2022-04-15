package com.huazie.fleaframework.db.common.table.split.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea分表定义，对应【flea-table-split.xml】中
 * 【{@code <flea-table-split> <flea-table-split>}】
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaTableSplit {

    private Tables tables; // 分表配置集

    private TableFiles tableFiles; // 分表配置文件集

    public Tables getTables() {
        return tables;
    }

    public void setTables(Tables tables) {
        this.tables = tables;
    }

    public TableFiles getTableFiles() {
        return tableFiles;
    }

    public void setTableFiles(TableFiles tableFiles) {
        this.tableFiles = tableFiles;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
