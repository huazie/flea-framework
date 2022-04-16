package com.huazie.fleaframework.db.common.table.pojo;

import com.huazie.fleaframework.db.common.lib.pojo.SplitLib;

/**
 * 分表信息，包含了与分表配置相关的内容
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SplitTable {

    private String tableName; // 模板表名

    private String splitTableName; // 分表名

    private String pkColumnValue; // ID生成器表中的主键值

    private String splitTablePkColumnValue; // ID生成器表中分表的主键值

    private boolean isExistSplitTable; // 是否存在分表 【true：存在 false：不存在】

    private boolean isExistSplitTablePkColumn; // 是否存在ID生成器表中分表的主键值

    private boolean generatorFlag; // 生成器标识

    private SplitLib splitLib; // 分库信息

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

    public String getSplitTablePkColumnValue() {
        return splitTablePkColumnValue;
    }

    public void setSplitTablePkColumnValue(String splitTablePkColumnValue) {
        this.splitTablePkColumnValue = splitTablePkColumnValue;
    }

    public boolean isExistSplitTable() {
        return isExistSplitTable;
    }

    public void setExistSplitTable(boolean existSplitTable) {
        isExistSplitTable = existSplitTable;
    }

    public boolean isExistSplitTablePkColumn() {
        return isExistSplitTablePkColumn;
    }

    public void setExistSplitTablePkColumn(boolean existSplitTablePkColumn) {
        isExistSplitTablePkColumn = existSplitTablePkColumn;
    }

    public boolean isGeneratorFlag() {
        return generatorFlag;
    }

    public void setGeneratorFlag(boolean generatorFlag) {
        this.generatorFlag = generatorFlag;
    }

    public SplitLib getSplitLib() {
        return splitLib;
    }

    public void setSplitLib(SplitLib splitLib) {
        this.splitLib = splitLib;
    }
}
