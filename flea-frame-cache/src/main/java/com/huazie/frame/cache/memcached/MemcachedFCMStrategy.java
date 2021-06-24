package com.huazie.frame.cache.memcached;

import com.huazie.frame.common.strategy.IFleaStrategy;

/**
 * Memcached Flea缓存管理者策略
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class MemcachedFCMStrategy implements IFleaStrategy<MemCachedFleaCacheManager> {

    @Override
    public MemCachedFleaCacheManager execute() {
        return new MemCachedFleaCacheManager();
    }
}
