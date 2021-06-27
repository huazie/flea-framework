package com.huazie.frame.common.strategy;

import com.huazie.frame.common.exception.FleaStrategyException;

/**
 * Flea策略接口，定义统一的策略执行方法。
 *
 * @param <T> Flea策略执行结果对应的类型
 * @param <P> Flea策略上下文参数
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public interface IFleaStrategy<T, P> {

    /**
     * 策略执行
     *
     * @param contextParam Flea策略上下文参数
     * @return 策略执行结果对应的类型
     * @throws FleaStrategyException Flea策略异常
     * @since 1.1.0
     */
    T execute(final P contextParam) throws FleaStrategyException;
}
