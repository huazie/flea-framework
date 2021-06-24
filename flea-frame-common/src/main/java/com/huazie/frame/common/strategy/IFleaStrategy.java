package com.huazie.frame.common.strategy;

/**
 * Flea策略接口，定义统一的策略执行方法。
 *
 * @param <T> Flea策略执行结果对应的类型
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaStrategy<T> {

    /**
     * 策略执行
     *
     * @return 策略执行结果对应的类型
     * @since 1.1.0
     */
    T execute();
}
