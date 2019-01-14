package com.huazie.frame.common.i18n.pojo;

import java.io.Serializable;

/**
 *  i18n数据
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年7月29日
 *
 */
@SuppressWarnings("serial")
public class FleaI18nData implements Serializable{
	
	private String key;
	
	private String value;
	
	public FleaI18nData() {
	}

	public FleaI18nData(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
