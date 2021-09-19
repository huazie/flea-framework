package com.huazie.frame.cache.redis.strategy;

import com.huazie.frame.cache.redis.RedisClient;
import com.huazie.frame.cache.redis.impl.FleaRedisClusterClient;
import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.exception.FleaStrategyException;
import com.huazie.frame.common.strategy.IFleaStrategy;

/**
 * 集群模式Redis客户端 策略
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class RedisClusterClientStrategy implements IFleaStrategy<RedisClient, String> {

    @Override
    public RedisClient execute(String poolName) throws FleaStrategyException {
        RedisClient originRedisClient;
        // 新建一个Flea Redis集群客户端类实例
        if (CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME.equals(poolName)) {
            originRedisClient = new FleaRedisClusterClient.Builder().build();
        } else {
            originRedisClient = new FleaRedisClusterClient.Builder(poolName).build();
        }
        return originRedisClient;
    }
}
