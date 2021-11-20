package com.huazie.frame.db.jpa.persistence;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义持久化上下文
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
@Target({TYPE, METHOD, FIELD})
@Retention(RUNTIME)
public @interface FleaPersistenceContext {

    /**
     * 持久化单元的名称，该名称定义在 persistence.xml 文件中
     *
     * @return 持久化单元名
     * @since 1.0.0
     */
    String unitName() default "";
}
