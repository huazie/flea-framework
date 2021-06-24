package com.huazie.frame.common.strategy;

/**
 * Flea抽象策略上下文。
 *
 * @param <T> Flea策略执行结果对应的类型
 * @param <P> Flea策略上下文参数
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public abstract class AbstractFleaStrategyContext<T, P> implements IFleaStrategyContext<T, P> {

    private P contextParam;

    @Override
    public void setContext(P contextParam) {
        this.contextParam = contextParam;
    }

    @Override
    public P getContext() {
        return contextParam;
    }
}
