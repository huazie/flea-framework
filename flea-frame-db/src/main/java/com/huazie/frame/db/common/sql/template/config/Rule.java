package com.huazie.frame.db.common.sql.template.config;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *  <p>
 *   SQL模板校验规则
 *   
 * @author huazie
 * @version v1.0.0
 * @date 2018年3月23日
 *
 */
public class Rule extends Properties{
	
	private String id;
	
	private String name;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
