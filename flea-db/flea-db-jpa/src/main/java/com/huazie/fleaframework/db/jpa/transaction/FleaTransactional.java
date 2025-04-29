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
     * @since 1.1.0
     */
    String value() default "fleaTransaction";

    /**
     * 指定持久化单元名
     *
     * @return 持久化单元名
     * @since 1.1.0
     */
    String unitName() default "";

    /**
     * 指定分库序列集
     * <p> 格式：分库序列键1=分库序列值1;分库序列键2=分库序列值2
     * <p> 分库序列键值可以动态引用方法参数，如 #name，表示引用变量名为 name的参数。
     * <p> 这里需要JDK版本 ≥ 8，并且在 pom.xml 中明确配置 maven-compiler-plugin，并添加 -parameters 参数。
     * <p> 注：-parameters 是 Java 8 引入的编译选项，用于在编译时保留方法参数名称（供反射使用）
     *
     * @return 分库序列集
     * @since 2.0.0
     */
    String seq() default "";

    /**
     * 指定分库序列提供类
     *
     * @return 分库序列提供类
     * @since 2.0.0
     */
    Class<?> seqProvider() default Void.class;

    /**
     * 指定分库序列提供类调用的方法
     *
     * @return 分库序列提供类调用的方法
     * @since 2.0.0
     */
    String seqMethod() default "";
}
