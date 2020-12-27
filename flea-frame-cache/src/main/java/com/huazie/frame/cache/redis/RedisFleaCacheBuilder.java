package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.IFleaCacheBuilder;
import com.huazie.frame.cache.common.CacheConfigManager;
import com.huazie.frame.cache.config.CacheParams;
import com.huazie.frame.cache.config.CacheServer;
import com.huazie.frame.cache.redis.impl.RedisClientProxy;
import com.huazie.frame.cache.redis.impl.RedisFleaCache;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.CollectionUtils;

import java.util.List;

/**
 * <p> Redis的Flea缓存建造者实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisFleaCacheBuilder implements IFleaCacheBuilder {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(RedisFleaCacheBuilder.class);

    @Override
    public AbstractFleaCache build(String name, List<CacheServer> cacheServerList, CacheParams cacheParams) {
        if (CollectionUtils.isEmpty(cacheServerList)) {
            return null;
        }
        // 获取失效时长
        long expiry = CacheConfigManager.getExpiry(name);
        // 获取缓存组名
        String group = cacheServerList.get(0).getGroup();
        // 初始化连接池
        RedisPool.getInstance(group).initialize(cacheServerList, cacheParams);
        // 获取Redis客户端代理类
        RedisClient redisClient = RedisClientProxy.getProxyInstance(group);
        // 创建一个Redis Flea缓存
        AbstractFleaCache fleaCache = new RedisFleaCache(name, expiry, redisClient);

        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "Pool Name = {}", RedisPool.getInstance(group).getPoolName());
            LOGGER.debug1(obj, "Pool = {}", RedisPool.getInstance(group).getJedisPool());
        }

        return fleaCache;
    }
}
