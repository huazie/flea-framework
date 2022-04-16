package com.huazie.fleaframework.db.common.table.split.config;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 其他分表配置文件，对应【flea-table-split.xml】中
 * 【{@code <table-file> </table-file>}】
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class TableFile extends TableMap {

    private String location; // 其他分表配置文件路径

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

    @Override
    public List<Table> getTableList() {
        return tableList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
