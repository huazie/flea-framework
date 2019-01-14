package com.huazie.frame.db.common.exception;

import java.sql.SQLException;

/**
 * 
 * SQL模板异常实现类
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月29日
 *
 */
public class SqlTemplateException extends SQLException {

	private static final long serialVersionUID = -6012817461659087101L;

	public SqlTemplateException(String message, Throwable cause) {
		super(message, cause);
	}

	public SqlTemplateException(String message) {
		super(message);
	}

}
