package com.huazie.frame.cache.redis;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.IFleaCacheBuilder;
import com.huazie.frame.cache.common.CacheConfigManager;
import com.huazie.frame.cache.config.CacheParams;
import com.huazie.frame.cache.config.CacheServer;
import com.huazie.frame.cache.redis.impl.RedisClientProxy;
import com.huazie.frame.cache.redis.impl.RedisFleaCache;
import com.huazie.frame.common.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p> Redis的Flea缓存建造者实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class RedisFleaCacheBuilder implements IFleaCacheBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisFleaCacheBuilder.class);

    @Override
    public AbstractFleaCache build(String name, List<CacheServer> cacheServerList, CacheParams cacheParams) {
        if (CollectionUtils.isEmpty(cacheServerList)) {
            return null;
        }
        // 获取失效时长
        long expiry = CacheConfigManager.getExpiry(name);

        String group = cacheServerList.get(0).getGroup();
        RedisPool redisPool = RedisPool.getInstance(group);
        redisPool.initialize(cacheServerList, cacheParams);

        RedisClient redisClient = RedisClientProxy.getProxyInstance(group);

        AbstractFleaCache fleaCache = new RedisFleaCache(name, expiry, redisClient);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("RedisFleaCacheBuilder#build(String, List<CacheServer>, CacheParams) Pool Name = {}", redisClient.getPoolName());
            LOGGER.debug("RedisFleaCacheBuilder#build(String, List<CacheServer>, CacheParams) Pool = {}", redisClient.getJedisPool());
        }

        return fleaCache;
    }
}
