package com.huazie.frame.common.pojo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @Description 跳蚤系统数据
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2017年4月1日
 *
 */
public class FleaSystemData implements Serializable{

	private static final long serialVersionUID = 3268928430543316461L;
	
	private String name;	//系统名
	
	private String url;		//系统URL

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
