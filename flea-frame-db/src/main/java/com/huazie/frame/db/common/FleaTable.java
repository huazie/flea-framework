package com.huazie.frame.db.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> 自定义实体类注解，用于定义表名 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FleaTable {
    String name();
}
