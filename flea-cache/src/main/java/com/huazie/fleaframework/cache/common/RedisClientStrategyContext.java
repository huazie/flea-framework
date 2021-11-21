package com.huazie.fleaframework.cache.common;

import com.huazie.fleaframework.cache.redis.RedisClient;
import com.huazie.fleaframework.cache.redis.strategy.RedisShardedClientStrategy;
import com.huazie.fleaframework.cache.redis.strategy.RedisClusterClientStrategy;
import com.huazie.fleaframework.common.strategy.FleaStrategyContext;
import com.huazie.fleaframework.common.strategy.IFleaStrategy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Redis客户端策略上下文
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class RedisClientStrategyContext extends FleaStrategyContext<RedisClient, String> {

    public RedisClientStrategyContext() {
        super();
    }

    public RedisClientStrategyContext(String contextParam) {
        super(contextParam);
    }

    @Override
    protected Map<String, IFleaStrategy<RedisClient, String>> init() {
        Map<String, IFleaStrategy<RedisClient, String>> fleaStrategyMap = new HashMap<>();
        fleaStrategyMap.put(CacheModeEnum.SHARDED.name(), new RedisShardedClientStrategy());
        fleaStrategyMap.put(CacheModeEnum.CLUSTER.name(), new RedisClusterClientStrategy());
        return Collections.unmodifiableMap(fleaStrategyMap);
    }
}
