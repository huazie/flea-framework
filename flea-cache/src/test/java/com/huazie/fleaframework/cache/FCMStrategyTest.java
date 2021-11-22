package com.huazie.fleaframework.cache;

import com.huazie.fleaframework.cache.common.CacheEnum;
import com.huazie.fleaframework.cache.common.FCMStrategyContext;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.strategy.FleaStrategyFacade;
import org.junit.Test;

/**
 * Flea缓存管理者策略单测

 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public class FCMStrategyTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FCMStrategyTest.class);

    @Test
    public void testCoreFCMStrategy() {
        String name = CacheEnum.FleaCore.getName();
        AbstractFleaCacheManager manager = FleaStrategyFacade.invoke(name, new FCMStrategyContext());
        LOGGER.debug("FleaCacheManager = {}", manager);
    }

    @Test
    public void testMemCachedFCMStrategy() {
        String name1 = CacheEnum.MemCached.getName();
        AbstractFleaCacheManager manager1 = FleaStrategyFacade.invoke(name1, new FCMStrategyContext());
        LOGGER.debug("FleaCacheManager = {}", manager1);
    }

    @Test
    public void testRedisShardedFCMStrategy() {
        String name2 = CacheEnum.RedisSharded.getName();
        AbstractFleaCacheManager manager2 = FleaStrategyFacade.invoke(name2, new FCMStrategyContext());
        LOGGER.debug("FleaCacheManager = {}", manager2);
    }

    @Test
    public void testRedisClusterFCMStrategy() {
        String name3 = CacheEnum.RedisCluster.getName();
        AbstractFleaCacheManager manager3 = FleaStrategyFacade.invoke(name3, new FCMStrategyContext());
        LOGGER.debug("FleaCacheManager = {}", manager3);
    }

    @Test
    public void testUnknownFCMStrategy() {
        String name4 = "unknown";
        AbstractFleaCacheManager manager4 = FleaStrategyFacade.invoke(name4, new FCMStrategyContext());
        LOGGER.debug("FleaCacheManager = {}", manager4);
    }
}
