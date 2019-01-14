package com.huazie.frame.db.common.tab.column;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * 属性列对象
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月29日
 *
 */
public class Column implements Serializable {

	private static final long serialVersionUID = 3292859915470233725L;
	
	public final static String COLUMN_ATTR_NAME = "attrName";
	
	public final static String COLUMN_TAB_COL_NAME = "tabColumnName";

	private Class<?> attrType; // 实体类属性对应类型

	private String attrName; // 实体类属性变量名

	private String tabColumnName; // 实体类对应的表字段名

	private Object attrValue; // 实体类属性值

	private boolean primaryKey; // 是否是主键

	private boolean nullable; // 是否可空

	private Boolean unique; // 是否唯一

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

	public Boolean getUnique() {
		return unique;
	}

	public void setUnique(Boolean unique) {
		this.unique = unique;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
