package com.huazie.fleaframework.db.jpa.transaction;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义Flea事务注解，用于描述某个方法上的事务属性。
 *
 * <p> Flea事务切面，识别带有自定义Flea事务注解的方法，
 * 在该方法调用之前自动开启事务，调用成功后提交事务，
 * 出现异常时自动回滚事务。
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface FleaTransactional {
    /**
     * 指定事务名
     *
     * @return 事务名
     */
    String value() default "fleaTransaction";

    /**
     * 指定持久化单元名
     *
     * @return 持久化单元名
     */
    String unitName() default "";
}
