package com.huazie.frame.cache.common;

import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.common.FleaCommonConfig;
import com.huazie.frame.common.strategy.AbstractFleaStrategyContext;

/**
 * Flea缓存管理者策略上下文
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class FCMStrategyContext extends AbstractFleaStrategyContext<AbstractFleaCacheManager, FleaCommonConfig> {

    @Override
    public AbstractFleaCacheManager invoke(String strategy) {

        return null;
    }
}
