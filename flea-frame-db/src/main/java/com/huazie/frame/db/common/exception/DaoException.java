package com.huazie.frame.db.common.exception;

/**
 * 
 * @Description 持久化Dao层操作出现的异常
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2016年9月27日
 * 
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = -8545806832715974487L;

	private int type;// 异常类型

	public DaoException(int type, String message) {
		super(message);
		this.type = type;
	}

	public DaoException(String message) {
		super(message);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
