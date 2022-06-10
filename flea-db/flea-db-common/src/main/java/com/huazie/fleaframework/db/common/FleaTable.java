package com.huazie.fleaframework.db.common;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义实体类注解，用于定义表名
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface FleaTable {
    /**
     * 定义实体对应数据库表名
     *
     * @return 表名
     * @since 1.0.0
     */
    String name() default "";
}
