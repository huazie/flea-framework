package com.huazie.frame.cache.memcached;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.IFleaCacheBuilder;
import com.huazie.frame.cache.common.CacheConfigManager;
import com.huazie.frame.cache.config.CacheParams;
import com.huazie.frame.cache.config.CacheServer;
import com.huazie.frame.cache.memcached.impl.MemCachedFleaCache;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.CollectionUtils;
import com.whalin.MemCached.MemCachedClient;

import java.util.List;

/**
 * <p> MemCached的Flea缓存建造者实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemCachedFleaCacheBuilder implements IFleaCacheBuilder {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(MemCachedFleaCacheBuilder.class);

    @Override
    public AbstractFleaCache build(String name, List<CacheServer> cacheServerList, CacheParams cacheParams) {

        if (CollectionUtils.isEmpty(cacheServerList)) {
            return null;
        }
        // 获取失效时长
        int expiry = CacheConfigManager.getExpiry(name);
        // 获取MemCached服务器所在组名
        String group = cacheServerList.get(0).getGroup();
        // 通过组名来获取 MemCached客户端类
        MemCachedClient memCachedClient = new MemCachedClient(group);
        // 获取MemCachedPool，并初始化连接池
        MemCachedPool memCachedPool = MemCachedPool.getInstance(group);
        memCachedPool.initialize(cacheServerList, cacheParams);
        // 创建一个MemCached Flea缓存类
        AbstractFleaCache fleaCache = new MemCachedFleaCache(name, expiry, memCachedClient);

        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "Pool Name = {}", memCachedPool.getPoolName());
            LOGGER.debug1(obj, "Pool = {}", memCachedPool.getSockIOPool());
        }

        return fleaCache;
    }
}
