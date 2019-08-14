package com.huazie.frame.cache.memcached;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.IFleaCacheBuilder;
import com.huazie.frame.cache.common.CacheConfigManager;
import com.huazie.frame.cache.config.CacheParams;
import com.huazie.frame.cache.config.CacheServer;
import com.huazie.frame.cache.memcached.impl.MemCachedFleaCache;
import com.huazie.frame.common.util.CollectionUtils;
import com.whalin.MemCached.MemCachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p> MemCached的Flea缓存建造者实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemCachedFleaCacheBuilder implements IFleaCacheBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemCachedFleaCacheBuilder.class);

    @Override
    public AbstractFleaCache build(String name, List<CacheServer> cacheServerList, CacheParams cacheParams) {

        if (CollectionUtils.isEmpty(cacheServerList)) {
            return null;
        }
        // 获取失效时长
        long expiry = CacheConfigManager.getExpiry(name);

        String group = cacheServerList.get(0).getGroup();
        // 通过组名来获取
        MemCachedClient memCachedClient = new MemCachedClient(group);
        // 初始化连接池
        MemCachedPool memCachedPool = MemCachedPool.getInstance(group);
        memCachedPool.initialize(cacheServerList, cacheParams);

        AbstractFleaCache fleaCache = new MemCachedFleaCache(name, expiry, memCachedClient);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("MemCachedFleaCacheBuilder#build(String, List<CacheServer>, CacheParams) Pool Name = {}", memCachedPool.getPoolName());
            LOGGER.debug("MemCachedFleaCacheBuilder#build(String, List<CacheServer>, CacheParams) Pool = {}", memCachedPool.getSockIOPool());
        }

        return fleaCache;
    }
}
