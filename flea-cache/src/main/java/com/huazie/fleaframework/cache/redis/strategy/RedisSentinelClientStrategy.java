package com.huazie.fleaframework.cache.redis.strategy;

import com.huazie.fleaframework.cache.redis.RedisClient;
import com.huazie.fleaframework.cache.redis.impl.FleaRedisSentinelClient;
import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.exceptions.FleaStrategyException;
import com.huazie.fleaframework.common.strategy.IFleaStrategy;

/**
 * 哨兵模式Redis客户端 策略
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class RedisSentinelClientStrategy implements IFleaStrategy<RedisClient, String> {

    @Override
    public RedisClient execute(String poolName) throws FleaStrategyException {
        RedisClient originRedisClient;
        // 新建一个Flea Redis哨兵客户端类实例
        if (CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME.equals(poolName)) {
            originRedisClient = new FleaRedisSentinelClient.Builder().build();
        } else {
            originRedisClient = new FleaRedisSentinelClient.Builder(poolName).build();
        }
        return originRedisClient;
    }
}
