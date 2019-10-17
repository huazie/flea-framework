package com.huazie.frame.db.common.table.pojo;

/**
 * <p> 分表信息 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SplitTable {

    private String tableName; // 主表名

    private String splitTableName; // 分表名

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSplitTableName() {
        return splitTableName;
    }

    public void setSplitTableName(String splitTableName) {
        this.splitTableName = splitTableName;
    }
}
