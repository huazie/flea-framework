package com.huazie.fleaframework.cache.core.strategy;

import com.huazie.fleaframework.cache.AbstractFleaCacheManager;
import com.huazie.fleaframework.cache.core.manager.CoreFleaCacheManager;
import com.huazie.fleaframework.common.FleaCommonConfig;
import com.huazie.fleaframework.common.strategy.IFleaStrategy;

/**
 * 核心 Flea缓存管理者策略
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class CoreFCMStrategy implements IFleaStrategy<AbstractFleaCacheManager, FleaCommonConfig> {

    @Override
    public AbstractFleaCacheManager execute(final FleaCommonConfig contextParam) {
        return new CoreFleaCacheManager();
    }
}
