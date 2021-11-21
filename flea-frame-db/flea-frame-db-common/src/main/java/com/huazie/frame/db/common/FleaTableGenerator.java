package com.huazie.frame.db.common;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Flea 表生成器
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
@Target({TYPE, METHOD, FIELD})
@Retention(RUNTIME)
public @interface FleaTableGenerator {

    /**
     * 生成器标识，boolean类型，默认为 true。
     *
     * @return true：生成器表在模板库中 false：生成器表在分库中
     * @since 1.2.0
     */
    boolean generatorFlag() default true;
}
