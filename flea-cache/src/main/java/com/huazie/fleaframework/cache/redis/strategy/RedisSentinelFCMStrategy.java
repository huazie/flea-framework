package com.huazie.fleaframework.cache.redis.strategy;

import com.huazie.fleaframework.cache.AbstractFleaCacheManager;
import com.huazie.fleaframework.cache.common.CacheConstants;
import com.huazie.fleaframework.cache.redis.manager.RedisSentinelFleaCacheManager;
import com.huazie.fleaframework.common.FleaCommonConfig;
import com.huazie.fleaframework.common.strategy.IFleaStrategy;
import com.huazie.fleaframework.common.util.ObjectUtils;

/**
 * Redis哨兵 Flea缓存管理者策略
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class RedisSentinelFCMStrategy implements IFleaStrategy<AbstractFleaCacheManager, FleaCommonConfig> {

    @Override
    public AbstractFleaCacheManager execute(final FleaCommonConfig contextParam) {
        if (ObjectUtils.isNotEmpty(contextParam)) {
            int database = contextParam.get(CacheConstants.RedisConfigConstants.REDIS_SENTINEL_CONFIG_DATABASE, Integer.class);
            if (database < 0 || database > 15)
                database = 0;
            return new RedisSentinelFleaCacheManager(database);
        }
        return new RedisSentinelFleaCacheManager();
    }
}
