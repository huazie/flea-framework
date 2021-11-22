package com.huazie.fleaframework.cache.redis.strategy;

import com.huazie.fleaframework.cache.AbstractFleaCacheManager;
import com.huazie.fleaframework.cache.redis.manager.RedisClusterFleaCacheManager;
import com.huazie.fleaframework.common.FleaCommonConfig;
import com.huazie.fleaframework.common.strategy.IFleaStrategy;

/**
 * Redis集群 Flea缓存管理者策略
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class RedisClusterFCMStrategy implements IFleaStrategy<AbstractFleaCacheManager, FleaCommonConfig> {

    @Override
    public AbstractFleaCacheManager execute(final FleaCommonConfig contextParam) {
        return new RedisClusterFleaCacheManager();
    }
}
