package com.huazie.fleaframework.cache;

import com.huazie.fleaframework.cache.core.manager.CoreSpringCacheManager;
import com.huazie.fleaframework.cache.redis.manager.RedisClusterSpringCacheManager;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Flea 缓存单测类
 *
 * @author huazie
 * @version 1.0.0
 */
public class SpringCacheTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(SpringCacheTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testRedisClusterSpringCache() {
        try {
            // 集群模式下Spring缓存管理类
            AbstractSpringCacheManager manager = (RedisClusterSpringCacheManager) applicationContext.getBean("redisClusterSpringCacheManager");
            AbstractSpringCache cache = manager.getCache("fleamenufavorites");
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
    public void testCoreSpringCacheForRedisCluster() {
        try {
            // 集群模式下Spring缓存管理类
            AbstractSpringCacheManager manager = (CoreSpringCacheManager) applicationContext.getBean("coreSpringCacheManager");
            AbstractSpringCache cache = manager.getCache("fleamenufavorites");
            LOGGER.debug("Cache = {}", cache);

            //#### 1.  简单字符串
//            cache.put("menu1", "huazie");
//            cache.put("menu2", null);
//            cache.get("menu1");
//            LOGGER.debug("Value = {}", cache.get("menu1", String.class));
//            cache.get("menu2");
//            cache.getCacheKey();
            cache.delete("menu1");
            cache.delete("menu2");
//            cache.clear();
            AbstractFleaCache fleaCache = (AbstractFleaCache) cache.getNativeCache();
            LOGGER.debug(fleaCache.getCacheName() + ">>>" + fleaCache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }
}
