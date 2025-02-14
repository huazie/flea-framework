package com.huazie.fleaframework.cache;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Flea 缓存单测类
 *
 * @author huazie
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringCacheTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(SpringCacheTest.class);

    @Autowired
    @Qualifier("redisClusterSpringCacheManager")
    private AbstractSpringCacheManager redisClusterSpringCacheManager;

    @Autowired
    @Qualifier("redisSentinelSpringCacheManager")
    private AbstractSpringCacheManager redisSentinelSpringCacheManager;

    @Autowired
    @Qualifier("coreSpringCacheManager")
    private AbstractSpringCacheManager coreSpringCacheManager;

    @Test
    public void testRedisClusterSpringCache() {
        try {
            // 集群模式下Spring缓存管理类
            AbstractSpringCache cache = redisClusterSpringCacheManager.getCache("fleamenufavorites");
            LOGGER.debug("Cache = {}", cache);

            //#### 1.  简单字符串
//            cache.put("menu1", "huazie");
//            cache.put("menu2", null);
//            cache.get("menu1");
//            LOGGER.debug("Value = {}", cache.get("menu1", String.class));
//            cache.get("menu2");
//            cache.getCacheKey();
//            cache.delete("menu1");
//            cache.delete("menu2");
//            cache.clear();
            AbstractFleaCache fleaCache = (AbstractFleaCache) cache.getNativeCache();
            LOGGER.debug(fleaCache.getCacheName() + ">>>" + fleaCache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testRedisSentinelSpringCache() {
        try {
            // 哨兵模式下Spring缓存管理类
            AbstractSpringCache cache = redisSentinelSpringCacheManager.getCache("fleajerseyresource");
            LOGGER.debug("Cache = {}", cache);

            //#### 1.  简单字符串
            cache.put("menu1", "huazie");
            cache.put("menu2", null);
//            cache.get("menu1");
//            cache.get("menu2");
//            cache.delete("menu1");
//            cache.delete("menu2");
//            cache.clear();
            cache.getCacheKey();
            AbstractFleaCache fleaCache = (AbstractFleaCache) cache.getNativeCache();
            LOGGER.debug(fleaCache.getCacheName() + ">>>" + fleaCache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testCoreSpringCacheForRedisCluster() {
        try {
            // 集群模式下Spring缓存管理类
            AbstractSpringCache cache = coreSpringCacheManager.getCache("fleamenufavorites");
            LOGGER.debug("Cache = {}", cache);

            //#### 1.  简单字符串
//            cache.put("menu1", "huazie");
//            cache.put("menu2", null);
//            cache.get("menu1");
//            LOGGER.debug("Value = {}", cache.get("menu1", String.class));
//            cache.get("menu2");
//            cache.getCacheKey();
//            cache.delete("menu1");
//            cache.delete("menu2");
//            cache.clear();
            AbstractFleaCache fleaCache = (AbstractFleaCache) cache.getNativeCache();
            LOGGER.debug(fleaCache.getCacheName() + ">>>" + fleaCache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testCoreSpringCacheForRedisSentinel() {
        try {
            // 集群模式下Spring缓存管理类
            AbstractSpringCache cache = coreSpringCacheManager.getCache("fleajerseyresource");
            LOGGER.debug("Cache = {}", cache);

            //#### 1.  简单字符串
//            cache.put("menu1", "huazie");
//            cache.put("menu2", null);
//            cache.get("menu1");
//            cache.get("menu2");
            cache.delete("menu1");
            cache.delete("menu2");
//            cache.clear();
            cache.getCacheKey();
            AbstractFleaCache fleaCache = (AbstractFleaCache) cache.getNativeCache();
            LOGGER.debug(fleaCache.getCacheName() + ">>>" + fleaCache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }
}
