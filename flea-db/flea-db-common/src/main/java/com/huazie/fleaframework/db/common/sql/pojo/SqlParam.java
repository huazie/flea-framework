package com.huazie.fleaframework.db.common.sql.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * <p> 自定义SQL模板参数 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SqlParam implements Serializable {

    private static final long serialVersionUID = 899301273401363946L;

    private int index;          // 参数索引，对应原生SQL中的位置

    private String attrName;    // 参数属性名，对应实体类变量名

    private Object attrValue;   // 参数属性值，对应实体类变量值

    private String tabColName;  // 参数列名，对应实体类映射表对应字段

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public Object getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(Object attrValue) {
        this.attrValue = attrValue;
    }

    public String getTabColName() {
        return tabColName;
    }

    public void setTabColName(String tabColName) {
        this.tabColName = tabColName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
