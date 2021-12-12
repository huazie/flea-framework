package com.huazie.fleaframework.cache.memcached.strategy;

import com.huazie.fleaframework.cache.AbstractFleaCacheManager;
import com.huazie.fleaframework.cache.memcached.manager.MemCachedFleaCacheManager;
import com.huazie.fleaframework.common.FleaCommonConfig;
import com.huazie.fleaframework.common.strategy.IFleaStrategy;

/**
 * MemCached Flea缓存管理者策略
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class MemCachedFCMStrategy implements IFleaStrategy<AbstractFleaCacheManager, FleaCommonConfig> {

    @Override
    public AbstractFleaCacheManager execute(final FleaCommonConfig contextParam) {
        return new MemCachedFleaCacheManager();
    }
}
