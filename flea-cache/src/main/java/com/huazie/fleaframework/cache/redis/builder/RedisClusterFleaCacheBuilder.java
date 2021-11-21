package com.huazie.fleaframework.cache.redis.builder;

import com.huazie.fleaframework.cache.common.CacheConfigUtils;
import com.huazie.fleaframework.cache.common.CacheModeEnum;
import com.huazie.fleaframework.cache.common.FleaCacheFactory;
import com.huazie.fleaframework.cache.config.CacheServer;
import com.huazie.fleaframework.cache.exceptions.FleaCacheConfigException;
import com.huazie.fleaframework.cache.redis.RedisClient;
import com.huazie.fleaframework.cache.redis.RedisClientFactory;
import com.huazie.fleaframework.cache.redis.RedisClusterPool;
import com.huazie.fleaframework.cache.redis.impl.RedisFleaCache;
import com.huazie.fleaframework.cache.AbstractFleaCache;
import com.huazie.fleaframework.cache.IFleaCacheBuilder;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.CollectionUtils;

import java.util.List;

/**
 * Redis集群模式Flea缓存建造者实现类，用于整合各类缓存接入时创建Redis Flea缓存。
 *
 * <p> 缓存定义文件【flea-cache.xml】中，每一个缓存定义配置都对应缓存配置文件
 * 【flea-cache-config.xml】中的一类缓存数据，每类缓存数据都归属一个缓存组，
 * 每个缓存组都映射着具体的缓存实现名，而整合各类缓存接入时，
 * 每个具体的缓存实现名都配置了Flea缓存建造着实现类。
 *
 * <p> 可查看Flea缓存配置文件【flea-cache-config.xml】，
 * 获取Redis Flea缓存建造者配置项【{@code <cache-item key="RedisCluster">}】
 *
 * @author huazie
 * @version 1.1.0
 * @see FleaCacheFactory
 * @since 1.1.0
 */
public class RedisClusterFleaCacheBuilder implements IFleaCacheBuilder {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(RedisClusterFleaCacheBuilder.class);

    @Override
    public AbstractFleaCache build(String name, List<CacheServer> cacheServerList) {
        if (CollectionUtils.isEmpty(cacheServerList)) {
            throw new FleaCacheConfigException("无法初始化集群模式下Redis Flea缓存，缓存服务器列表【cacheServerList】为空");

        }
        // 获取缓存数据有效期（单位：s）
        int expiry = CacheConfigUtils.getExpiry(name);
        // 获取空缓存数据有效期（单位：s）
        int nullCacheExpiry = CacheConfigUtils.getNullCacheExpiry();
        // 获取缓存组名
        String group = cacheServerList.get(0).getGroup();
        // 初始化指定连接池名【group】的Redis集群模式连接池
        RedisClusterPool.getInstance(group).initialize(cacheServerList);
        // 获取集群模式下的指定连接池名【group】的Redis客户端类
        RedisClient redisClient = RedisClientFactory.getInstance(group, CacheModeEnum.CLUSTER);
        // 创建一个Redis Flea缓存
        AbstractFleaCache fleaCache = new RedisFleaCache(name, expiry, nullCacheExpiry, CacheModeEnum.CLUSTER, redisClient);

        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "Pool Name = {}", RedisClusterPool.getInstance(group).getPoolName());
            LOGGER.debug1(obj, "JedisCluster = {}", RedisClusterPool.getInstance(group).getJedisCluster());
        }

        return fleaCache;
    }
}
