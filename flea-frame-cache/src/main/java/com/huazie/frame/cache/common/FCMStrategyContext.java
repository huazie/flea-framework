package com.huazie.frame.cache.common;

import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.core.CoreFCMStrategy;
import com.huazie.frame.cache.memcached.MemCachedFCMStrategy;
import com.huazie.frame.cache.redis.RedisClusterFCMStrategy;
import com.huazie.frame.cache.redis.RedisShardedFCMStrategy;
import com.huazie.frame.common.FleaCommonConfig;
import com.huazie.frame.common.strategy.FleaStrategyContext;
import com.huazie.frame.common.strategy.IFleaStrategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Flea缓存管理者策略上下文
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class FCMStrategyContext extends FleaStrategyContext<AbstractFleaCacheManager, FleaCommonConfig> {

    @Override
    public Map<String, IFleaStrategy<AbstractFleaCacheManager, FleaCommonConfig>> init() {
        Map<String, IFleaStrategy<AbstractFleaCacheManager, FleaCommonConfig>> fleaStrategyMap = new HashMap<>();
        fleaStrategyMap.put(CacheEnum.MemCached.getName(), new MemCachedFCMStrategy());
        fleaStrategyMap.put(CacheEnum.RedisSharded.getName(), new RedisShardedFCMStrategy());
        fleaStrategyMap.put(CacheEnum.RedisCluster.getName(), new RedisClusterFCMStrategy());
        fleaStrategyMap.put(CacheEnum.FleaCore.getName(), new CoreFCMStrategy());
        return Collections.unmodifiableMap(fleaStrategyMap);
    }
}
