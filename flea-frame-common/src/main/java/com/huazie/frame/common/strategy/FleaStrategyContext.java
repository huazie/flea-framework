package com.huazie.frame.common.strategy;

import com.huazie.frame.common.exception.FleaStrategyException;
import com.huazie.frame.common.exception.FleaStrategyNotFoundException;
import com.huazie.frame.common.util.ObjectUtils;

import java.util.Map;

/**
 * Flea抽象策略上下文，封装了公共的策略执行逻辑，
 * 其中Flea策略Map由其子类进行初始化，键为策略名，
 * 值为具体的Flea策略实例。
 *
 * @param <T> Flea策略执行结果对应的类型
 * @param <P> Flea策略上下文参数
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public abstract class FleaStrategyContext<T, P> implements IFleaStrategyContext<T, P> {

    private Map<String, IFleaStrategy<T, P>> fleaStrategyMap; // Flea策略Map

    private P contextParam; // Flea策略上下文参数

    /**
     * 初始化策略上下文
     *
     * @since 1.1.0
     */
    public FleaStrategyContext() {
        fleaStrategyMap = init();
    }

    /**
     * 初始化策略上下文
     *
     * @param contextParam Flea策略上下文参数
     * @since 1.1.0
     */
    public FleaStrategyContext(P contextParam) {
        this();
        this.contextParam = contextParam;
    }

    /**
     * 初始化Flea策略Map
     *
     * @return Flea策略Map
     * @since 1.1.0
     */
    protected abstract Map<String, IFleaStrategy<T, P>> init();

    @Override
    public T invoke(String strategy) throws FleaStrategyException {
        if (ObjectUtils.isEmpty(fleaStrategyMap)) {
            throw new FleaStrategyException("The Strategy Map is not initialized!");
        }
        IFleaStrategy<T, P> fleaStrategy = fleaStrategyMap.get(strategy);
        if (ObjectUtils.isEmpty(fleaStrategy)) {
            throw new FleaStrategyNotFoundException("The Strategy [name =\"" + strategy + "\"] is not found!");
        }
        return fleaStrategy.execute(contextParam);
    }

    @Override
    public void setContext(P contextParam) {
        this.contextParam = contextParam;
    }

    @Override
    public P getContext() {
        return contextParam;
    }
}
