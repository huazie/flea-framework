package com.huazie.fleaframework.cache;

import com.huazie.fleaframework.cache.config.Cache;
import com.huazie.fleaframework.cache.config.CacheData;
import com.huazie.fleaframework.cache.config.CacheItem;
import com.huazie.fleaframework.cache.config.CacheParams;
import com.huazie.fleaframework.cache.config.FleaCache;
import com.huazie.fleaframework.cache.config.FleaCacheConfig;
import com.huazie.fleaframework.cache.common.CacheConfigUtils;
import com.huazie.fleaframework.cache.common.CacheConstants;
import com.huazie.fleaframework.cache.common.CacheXmlDigesterHelper;
import com.huazie.fleaframework.cache.config.CacheGroup;
import com.huazie.fleaframework.cache.config.CacheServer;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;

import java.util.List;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaCacheConfigTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaCacheConfigTest.class);

    @Test
    public void testGetFleaCache() {
        Cache cache = CacheConfigUtils.getCache("fleaconfigdata");
        LOGGER.debug("Cache={}", cache);
        CacheData cacheData = CacheConfigUtils.getCacheData(cache.getType());
        LOGGER.debug("CacheData={}", cacheData);
        CacheGroup cacheGroup = CacheConfigUtils.getCacheGroup(cacheData.getGroup());
        LOGGER.debug("CacheGroup={}", cacheGroup);
        CacheParams cacheParams = CacheConfigUtils.getCacheParams();
        LOGGER.debug("CacheParams={}", cacheParams);
        List<CacheServer> cacheServers = CacheConfigUtils.getCacheServer(cacheGroup.getGroup());
        LOGGER.debug("CacheServers={}", cacheServers);
    }

    @Test
    public void testGetCacheItem() {
        CacheItem cacheItem = CacheConfigUtils.getCacheItem(CacheConstants.FleaCacheConfigConstants.FLEA_CACHE_INIT, CacheConstants.FleaCacheConfigConstants.SYSTEM_NAME);
        LOGGER.debug("CacheItem={}", cacheItem);
    }

    @Test
    public void testCacheFiles() {
        FleaCache fleaCache = CacheXmlDigesterHelper.getInstance().getFleaCache();
        LOGGER.debug("CacheFiles={}", fleaCache.getCacheFiles());
    }

    @Test
    public void testCacheServers() {
        FleaCacheConfig fleaCacheConfig = CacheXmlDigesterHelper.getInstance().getFleaCacheConfig();
        LOGGER.debug("CacheServers={}", fleaCacheConfig.getCacheServers());
    }
}
