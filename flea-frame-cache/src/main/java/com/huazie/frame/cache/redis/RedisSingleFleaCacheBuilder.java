package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.IFleaCacheBuilder;
import com.huazie.frame.cache.common.CacheConfigUtils;
import com.huazie.frame.cache.common.CacheModeEnum;
import com.huazie.frame.cache.config.CacheServer;
import com.huazie.frame.cache.exceptions.FleaCacheConfigException;
import com.huazie.frame.cache.redis.impl.RedisFleaCache;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.CollectionUtils;

import java.util.List;

/**
 * Redis单机模式Flea缓存建造者实现类，用于整合各类缓存接入时创建Redis Flea缓存。
 *
 * <p> 缓存定义文件【flea-cache.xml】中，每一个缓存定义配置都对应缓存配置文件
 * 【flea-cache-config.xml】中的一类缓存数据，每类缓存数据都归属一个缓存组，
 * 每个缓存组都映射着具体的缓存实现名，而整合各类缓存接入时，
 * 每个具体的缓存实现名都配置了Flea缓存建造着实现类。
 *
 * <p> 可查看Flea缓存配置文件【flea-cache-config.xml】，
 * 获取Redis Flea缓存建造者配置项【{@code <cache-item key="Redis">}】
 *
 * @author huazie
 * @version 1.1.0
 * @see com.huazie.frame.cache.common.FleaCacheFactory
 * @since 1.0.0
 */
public class RedisSingleFleaCacheBuilder implements IFleaCacheBuilder {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(RedisSingleFleaCacheBuilder.class);

    @Override
    public AbstractFleaCache build(String name, List<CacheServer> cacheServerList) {
        if (CollectionUtils.isEmpty(cacheServerList)) {
            throw new FleaCacheConfigException("无法初始化单机模式下Redis Flea缓存，缓存服务器列表【cacheServerList】为空");
        }
        // 获取缓存数据有效期（单位：s）
        int expiry = CacheConfigUtils.getExpiry(name);
        // 获取空缓存数据有效期（单位：s）
        int nullCacheExpiry = CacheConfigUtils.getNullCacheExpiry();
        // 获取缓存组名
        String group = cacheServerList.get(0).getGroup();
        // 初始化指定连接池名【group】的Redis单机模式连接池
        RedisSinglePool.getInstance(group).initialize(cacheServerList);
        // 获取单机模式下的指定连接池名【group】的Redis客户端
        RedisClient redisClient = RedisClientFactory.getInstance(group);
        // 创建一个Redis Flea缓存
        AbstractFleaCache fleaCache = new RedisFleaCache(name, expiry, nullCacheExpiry, CacheModeEnum.SINGLE, redisClient);

        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "Pool Name = {}", RedisSinglePool.getInstance(group).getPoolName());
            LOGGER.debug1(obj, "Pool = {}", RedisSinglePool.getInstance(group).getJedisPool());
        }

        return fleaCache;
    }
}
