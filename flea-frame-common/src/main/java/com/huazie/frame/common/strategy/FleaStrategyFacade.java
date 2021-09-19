package com.huazie.frame.common.strategy;

import com.huazie.frame.common.exception.FleaStrategyException;

/**
 * Flea策略门面，定义策略调用的统一入口。
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class FleaStrategyFacade {

    private FleaStrategyFacade() {
    }

    /**
     * 策略门面调用方法
     *
     * @param strategy            策略名
     * @param fleaStrategyContext 策略上下文
     * @param <T>                 Flea策略执行结果对应的类型
     * @param <P>                 Flea策略上下文参数
     * @return Flea策略执行结果对应的类型
     * @throws FleaStrategyException Flea策略异常
     * @since 1.1.0
     */
    public static <T, P> T invoke(String strategy, IFleaStrategyContext<T, P> fleaStrategyContext) throws FleaStrategyException {
        return fleaStrategyContext.invoke(strategy);
    }
}
