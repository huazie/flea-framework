package com.huazie.frame.db.common.table.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * <p> 属性列对象 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class Column implements Serializable {

    private static final long serialVersionUID = 3292859915470233725L;

    public static final String COLUMN_ATTR_NAME = "attrName";

    public static final String COLUMN_TAB_COL_NAME = "tabColumnName";

    private Class<?> attrType; // 实体类属性对应类型

    private String attrName; // 实体类属性变量名

    private String tabColumnName; // 实体类对应的表字段名

    private String tabColumnDesc; // 实体类对应的表字段描述

    private Object attrValue; // 实体类属性值

    private boolean primaryKey; // 是否是主键

    private boolean nullable; // 是否可空

    private boolean unique; // 是否唯一

    private String pkColumnValue; // ID生成器表中的主键值模板

    private boolean generatorFlag; // 生成器标识

    public Class<?> getAttrType() {
        return attrType;
    }

    public void setAttrType(Class<?> attrType) {
        this.attrType = attrType;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getTabColumnName() {
        return tabColumnName;
    }

    public void setTabColumnName(String tabColumnName) {
        this.tabColumnName = tabColumnName;
    }

    public String getTabColumnDesc() {
        return tabColumnDesc;
    }

    public void setTabColumnDesc(String tabColumnDesc) {
        this.tabColumnDesc = tabColumnDesc;
    }

    public Object getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(Object attrValue) {
        this.attrValue = attrValue;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean getUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public String getPkColumnValue() {
        return pkColumnValue;
    }

    public void setPkColumnValue(String pkColumnValue) {
        this.pkColumnValue = pkColumnValue;
    }

    public boolean isGeneratorFlag() {
        return generatorFlag;
    }

    public void setGeneratorFlag(boolean generatorFlag) {
        this.generatorFlag = generatorFlag;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
