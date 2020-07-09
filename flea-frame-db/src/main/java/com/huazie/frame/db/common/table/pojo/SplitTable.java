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

    private String pkColumnValue; // 生成器表中的主键值 【如果存在分表，则是对应分表的主键值】

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

    public String getPkColumnValue() {
        return pkColumnValue;
    }

    public void setPkColumnValue(String pkColumnValue) {
        this.pkColumnValue = pkColumnValue;
    }
}
