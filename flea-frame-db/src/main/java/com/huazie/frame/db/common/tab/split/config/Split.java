package com.huazie.frame.db.common.tab.split.config;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p>
 * 分表后缀配置类
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月25日
 *
 */
public class Split implements Serializable {

	private static final long serialVersionUID = -5675907324751016293L;

	private String key; // 分表key

	private String column; // 分表列名

	private String implClass; // 分表转换实现类

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getImplClass() {
		return implClass;
	}

	public void setImplClass(String implClass) {
		this.implClass = implClass;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
