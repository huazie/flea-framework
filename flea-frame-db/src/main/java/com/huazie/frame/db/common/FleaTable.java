package com.huazie.frame.db.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  自定义实体类注解，用于定义表名
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年6月2日
 *
 */
@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FleaTable {
	public abstract String name();
}
