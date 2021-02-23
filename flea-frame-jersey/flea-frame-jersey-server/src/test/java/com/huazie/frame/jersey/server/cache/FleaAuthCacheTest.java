package com.huazie.frame.jersey.server.cache;

import com.huazie.frame.cache.common.CacheConfigManager;
import com.huazie.frame.cache.config.Cache;
import com.huazie.frame.cache.config.CacheData;
import com.huazie.frame.cache.config.CacheGroup;
import com.huazie.frame.cache.config.CacheParams;
import com.huazie.frame.cache.config.CacheServer;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;

import java.util.List;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaAuthCacheTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaAuthCacheTest.class);

    @Test
    public void testGetFleaCache() {
        Cache cache = CacheConfigManager.getCache("fleaauthmenu");
        LOGGER.debug("*****************************************Cache = {}", cache);
        CacheData cacheData = CacheConfigManager.getCacheData(cache.getType());
        LOGGER.debug("*****************************************CacheData = {}", cacheData);
        CacheGroup cacheGroup = CacheConfigManager.getCacheGroup(cacheData.getGroup());
        LOGGER.debug("*****************************************CacheGroup = {}", cacheGroup);
        CacheParams cacheParams = CacheConfigManager.getCacheParams(cacheGroup.getCache());
        LOGGER.debug("*****************************************CacheParams = {}", cacheParams);
        List<CacheServer> cacheServers = CacheConfigManager.getCacheServer(cacheGroup.getGroup());
        LOGGER.debug("*****************************************CacheServers = {}", cacheServers);
    }

}
