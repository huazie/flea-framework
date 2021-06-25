package com.huazie.frame.cache.memcached;

import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.common.FleaCommonConfig;
import com.huazie.frame.common.strategy.IFleaStrategy;

/**
 * Memcached Flea缓存管理者策略
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class MemcachedFCMStrategy implements IFleaStrategy<AbstractFleaCacheManager, FleaCommonConfig> {

    @Override
    public AbstractFleaCacheManager execute(final FleaCommonConfig contextParam) {
        return new MemCachedFleaCacheManager();
    }
}