package com.huazie.fleaframework.cache;

import com.huazie.fleaframework.cache.common.CacheConfigUtils;
import com.huazie.fleaframework.cache.common.CacheModeEnum;
import com.huazie.fleaframework.cache.common.RedisClientStrategyContext;
import com.huazie.fleaframework.cache.config.CacheServer;
import com.huazie.fleaframework.cache.redis.RedisClient;
import com.huazie.fleaframework.cache.redis.RedisClusterPool;
import com.huazie.fleaframework.cache.redis.RedisShardedPool;
import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.strategy.FleaStrategyFacade;
import org.junit.Test;

import java.util.List;

/**
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class RedisClientStrategyTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(RedisClientStrategyTest.class);

    @Test
    public void testRedisShardedClientStrategy() {
        String poolName = CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME;
        // 初始化默认连接池
        RedisShardedPool.getInstance().initialize();

        RedisClientStrategyContext context = new RedisClientStrategyContext(poolName);
        RedisClient redisClient = FleaStrategyFacade.invoke(CacheModeEnum.SHARDED.name(), context);
        LOGGER.debug("FleaRedisShardedClient = {}", redisClient);
        LOGGER.debug("PoolName = {}", redisClient.getPoolName());
    }

    @Test
    public void testRedisShardedClientStrategy1() {
        String poolName = "dynamicGroup";
        // 初始化指定连接池
        List<CacheServer> cacheServerList = CacheConfigUtils.getCacheServer(poolName);
        RedisShardedPool.getInstance(poolName).initialize(cacheServerList);

        RedisClientStrategyContext context = new RedisClientStrategyContext(poolName);
        RedisClient redisClient = FleaStrategyFacade.invoke(CacheModeEnum.SHARDED.name(), context);
        LOGGER.debug("FleaRedisShardedClient = {}", redisClient);
        LOGGER.debug("PoolName = {}", redisClient.getPoolName());
    }

    @Test
    public void testRedisClusterClientStrategy() {
        String poolName = CommonConstants.FleaPoolConstants.DEFAULT_POOL_NAME;
        // 初始化默认连接池
        RedisClusterPool.getInstance().initialize();

        RedisClientStrategyContext context = new RedisClientStrategyContext(poolName);
        RedisClient redisClient = FleaStrategyFacade.invoke(CacheModeEnum.CLUSTER.name(), context);
        LOGGER.debug("FleaRedisClusterClient = {}", redisClient);
        LOGGER.debug("PoolName = {}", redisClient.getPoolName());
    }

    @Test
    public void testRedisClusterClientStrategy1() {
        String poolName = "dynamicGroup";
        // 初始化指定连接池
        List<CacheServer> cacheServerList = CacheConfigUtils.getCacheServer(poolName);
        RedisClusterPool.getInstance(poolName).initialize(cacheServerList);

        RedisClientStrategyContext context = new RedisClientStrategyContext(poolName);
        RedisClient redisClient = FleaStrategyFacade.invoke(CacheModeEnum.CLUSTER.name(), context);
        LOGGER.debug("FleaRedisClusterClient = {}", redisClient);
        LOGGER.debug("PoolName = {}", redisClient.getPoolName());
    }
}
