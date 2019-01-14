package com.huazie.frame.db.common.exception;

/**
 * 
 * 分表异常实现类
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月29日
 *
 */
public class TableSplitException extends Exception {

	private static final long serialVersionUID = 2242056751387595190L;

	public TableSplitException(String message, Throwable cause) {
		super(message, cause);
	}

	public TableSplitException(String message) {
		super(message);
	}
	
}
