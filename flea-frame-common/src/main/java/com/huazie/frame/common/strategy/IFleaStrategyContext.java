package com.huazie.frame.common.strategy;

import com.huazie.frame.common.exception.FleaException;

/**
 * Flea策略上下文接口，定义统一的策略上下文调用方法。
 *
 * @param <T> Flea策略执行结果对应的类型
 * @param <P> Flea策略上下文参数
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public interface IFleaStrategyContext<T, P> {

    /**
     * 策略上下文调用
     *
     * @param strategy 策略名称
     * @return 策略执行结果对应的类型
     * @since 1.1.0
     */
    T invoke(String strategy) throws FleaException;

    /**
     * 设置策略上下文参数
     *
     * @param contextParam 上下文参数对象
     * @since 1.0.0
     */
    void setContext(P contextParam);

    /**
     * 获取策略上下文参数
     *
     * @return 策略上下文参数
     * @since 1.0.0
     */
    P getContext();
}
