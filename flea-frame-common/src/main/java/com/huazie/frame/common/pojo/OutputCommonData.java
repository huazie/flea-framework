package com.huazie.frame.common.pojo;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p>
 * 		定义公用的返回数据
 * </p>
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2017年2月19日
 *
 */
public class OutputCommonData implements Serializable{

	private static final long serialVersionUID = -9098279075924276663L;
	
	private String retCode;
	private String retMess;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMess() {
		return retMess;
	}

	public void setRetMess(String retMess) {
		this.retMess = retMess;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
