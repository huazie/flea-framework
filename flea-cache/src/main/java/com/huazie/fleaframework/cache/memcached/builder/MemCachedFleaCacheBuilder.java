package com.huazie.fleaframework.cache.memcached.builder;

import com.huazie.fleaframework.cache.AbstractFleaCache;
import com.huazie.fleaframework.cache.IFleaCacheBuilder;
import com.huazie.fleaframework.cache.common.CacheConfigUtils;
import com.huazie.fleaframework.cache.common.CacheConstants;
import com.huazie.fleaframework.cache.common.EmptyFleaCache;
import com.huazie.fleaframework.cache.config.CacheServer;
import com.huazie.fleaframework.cache.exceptions.FleaCacheConfigException;
import com.huazie.fleaframework.cache.memcached.MemCachedPool;
import com.huazie.fleaframework.cache.memcached.impl.MemCachedFleaCache;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.whalin.MemCached.MemCachedClient;

import java.util.List;

/**
 * MemCached Flea缓存建造者实现类，用于整合各类缓存接入时创建MemCached Flea缓存。
 *
 * <p> 缓存定义文件【flea-cache.xml】中，每一个缓存定义配置都对应缓存配置文件
 * 【flea-cache-config.xml】中的一类缓存数据，每类缓存数据都归属一个缓存组，
 * 每个缓存组都映射着具体的缓存实现名，而整合各类缓存接入时，
 * 每个具体的缓存实现名都配置了Flea缓存建造着实现类。
 *
 * <p> 可查看Flea缓存配置文件【flea-cache-config.xml】，获取
 * MemCached Flea缓存建造者配置项【{@code <cache-item key="MemCached">}】
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.0.0
 */
public class MemCachedFleaCacheBuilder implements IFleaCacheBuilder {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(MemCachedFleaCacheBuilder.class);

    @Override
    public AbstractFleaCache build(String name, List<CacheServer> cacheServerList) {
        if (CollectionUtils.isEmpty(cacheServerList)) {
            ExceptionUtils.throwFleaException(FleaCacheConfigException.class, "无法初始化MemCached Flea缓存，缓存服务器列表【cacheServerList】为空");
        }
        // 获取缓存数据有效期（单位：s）
        int expiry = CacheConfigUtils.getExpiry(name);
        // 获取空缓存数据有效期（单位：s）
        int nullCacheExpiry = CacheConfigUtils.getNullCacheExpiry();
        // 获取 MemCached配置开关（1：开启 0：关闭），如果不配置也默认开启
        boolean isSwitchOpen = CacheConfigUtils.isSwitchOpen(CacheConstants.MemCachedConfigConstants.MEMCACHED_CONFIG_SWITCH);

        AbstractFleaCache fleaCache;
        if (isSwitchOpen) { // 开关启用，按实际缓存处理
            // 获取MemCached服务器所在组名
            String group = cacheServerList.get(0).getGroup();
            // 通过组名来获取 MemCached客户端类
            MemCachedClient memCachedClient = new MemCachedClient(group);
            // 获取MemCachedPool，并初始化连接池
            MemCachedPool.getInstance(group).initialize(cacheServerList);
            // 创建一个MemCached Flea缓存类
            fleaCache = new MemCachedFleaCache(name, expiry, nullCacheExpiry, memCachedClient);

            if (LOGGER.isDebugEnabled()) {
                Object obj = new Object() {};
                LOGGER.debug1(obj, "Pool Name = {}", MemCachedPool.getInstance(group).getPoolName());
                LOGGER.debug1(obj, "Pool = {}", MemCachedPool.getInstance(group).getSockIOPool());
            }
        } else { // 开关关闭，默认返回空缓存实现
            fleaCache = new EmptyFleaCache(name, expiry, nullCacheExpiry);
        }

        return fleaCache;
    }
}
