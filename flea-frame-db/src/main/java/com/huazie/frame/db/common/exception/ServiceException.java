package com.huazie.frame.db.common.exception;

/**
 * 
 * @Description 业务逻辑层出现的异常
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2017年2月19日
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -1661201330754177789L;

	private int type;// 异常的类型
	
	public ServiceException(int type, String message) {
		super(message);
		this.type = type;
	}

	public ServiceException(String message) {
		super(message);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
