package com.huazie.frame.core.base.cfgdata;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.AbstractSpringCacheManager;
import com.huazie.frame.cache.common.CacheEnum;
import com.huazie.frame.cache.common.FleaCacheManagerFactory;
import com.huazie.frame.cache.memcached.manager.MemCachedSpringCacheManager;
import com.huazie.frame.cache.redis.manager.RedisShardedSpringCacheManager;
import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.frame.core.base.cfgdata.entity.FleaConfigData;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaConfigDataSV;
import com.huazie.frame.db.common.DBSystemEnum;
import com.huazie.frame.db.jdbc.FleaJDBCHelper;
import com.huazie.frame.db.jdbc.config.FleaJDBCConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class FleaConfigDataSVImplTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaConfigDataSVImplTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void getConfigDataById() throws Exception {
        IFleaConfigDataSV sv = (IFleaConfigDataSV) applicationContext.getBean("fleaConfigDataSV");

        FleaConfigData fleaConfigData = sv.query(1L);
        LOGGER.debug("FleaConfigData = {}", fleaConfigData);
    }

    @Test
    public void getConfigDatas() {
        IFleaConfigDataSV sv = (IFleaConfigDataSV) applicationContext.getBean("fleaConfigDataSV");
        try {
            sv.getConfigDatas("huazie", "");
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void getConfigData() {
        IFleaConfigDataSV sv = (IFleaConfigDataSV) applicationContext.getBean("fleaConfigDataSV");
        try {
            sv.getConfigData("huazie", "huazie");
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testMemCachedFleaCache() {
        try {
            AbstractFleaCacheManager manager = FleaCacheManagerFactory.getFleaCacheManager(CacheEnum.MemCached.getName());
            LOGGER.debug("MemCachedCacheManager={}", manager);

            AbstractFleaCache cache = manager.getCache("fleaconfigdata");
            LOGGER.debug("Cache={}", cache);

            //#### 复杂配置参数
//            cache.get("FLEA_RES_STATE");
//            cache.get("FLEAER_CERT_TYPE_1");
            LOGGER.debug("CacheKey={}", cache.getCacheKey());
            cache.delete("FLEA_RES_STATE");
            LOGGER.debug(cache.getCacheName() + ">>>" + cache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testRedisShardedFleaCache() {
        try {
            // 分片模式下Flea缓存管理类
            AbstractFleaCacheManager manager = FleaCacheManagerFactory.getFleaCacheManager(CacheEnum.RedisSharded.getName());
            LOGGER.debug("RedisCacheManager={}", manager);
            AbstractFleaCache cache = manager.getCache("fleajerseyresclient");
            LOGGER.debug("Cache={}", cache);

//            cache.delete("FLEA_CLIENT_FILE_DOWNLOAD");
//            cache.clear();
            //#### 复杂配置参数
            Set<String> cacheKey = cache.getCacheKey();
            LOGGER.debug("CacheKey = {}", cacheKey);
//            cache.get("FLEA_CLIENT_UPLOAD_AUTH");
//            cache.get("FLEAER_CERT_TYPE_1");
//            cache.delete("FLEA_RES_STATE");

//            cache.clear();
            LOGGER.debug(cache.getCacheName() + ">>>" + cache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testMemCachedSpringCache() {
        try {
            AbstractSpringCacheManager manager = (MemCachedSpringCacheManager) applicationContext.getBean("memCachedSpringCacheManager");
            LOGGER.debug("MemCachedCacheManager={}", manager);

            AbstractSpringCache cache = manager.getCache("fleaconfigdata");
            LOGGER.debug("Cache={}", cache);

            Set<String> cacheKey = cache.getCacheKey();
            LOGGER.debug("CacheKey = {}", cacheKey);

//            cache.clear();
            //#### 复杂配置参数
//            cache.get("FLEA_RES_STATE");
//            cache.get("FLEAER_CERT_TYPE_1");
//            cache.delete("FLEA_RES_STATE");

            //#### 1.  简单字符串
//			cache.put("menu1", "huazie");
//            cache.get("menu1");
//            cache.get("menu1", String.class);

            //#### 2.  简单对象(要是可以序列化的对象)
//			String user = new String("huazie");
//			cache.put("user", user);
//			LOGGER.debug(cache.get("user", String.class));

            //#### 3.  List塞对象
//			List<String> userList = new ArrayList<>();
//			userList.add("huazie");
//			userList.add("lgh");
//			cache.put("user_list", userList);

//			LOGGER.debug(cache.get("user_list",userList.getClass()).toString());

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testRedisShardedSpringCache() {
        try {
            // 分片模式下Spring缓存管理类
            AbstractSpringCacheManager manager = (RedisShardedSpringCacheManager) applicationContext.getBean("redisShardedSpringCacheManager");
            LOGGER.debug("RedisCacheManager={}", manager);

            AbstractSpringCache cache = manager.getCache("fleaconfigdata");
            LOGGER.debug("Cache={}", cache);

            Set<String> cacheKey = cache.getCacheKey();
            LOGGER.debug("CacheKey = {}", cacheKey);

            //#### 1.  简单字符串
//			cache.put("menu1", "huazie");
//            cache.get("menu1");
//            cache.get("menu1", String.class);

            //#### 2.  简单对象(要是可以序列化的对象)
//			String user = new String("huazie");
//			cache.put("user", user);
//			LOGGER.debug(cache.get("user", String.class));
//            cache.get("FLEA_RES_STATE");
//            cache.clear();

            //#### 3.  List塞对象
//			List<String> userList = new ArrayList<>();
//			userList.add("huazie");
//			userList.add("lgh");
//			cache.put("user_list", userList);

//			LOGGER.debug(cache.get("user_list",userList.getClass()).toString());

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaJPAQuery() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        IFleaConfigDataSV sv = (IFleaConfigDataSV) applicationContext.getBean("fleaConfigDataSV");
        try {
            FleaConfigData fleaConfigData = sv.query(2L);
            LOGGER.debug("row = {}", fleaConfigData);
            List<FleaConfigData> list = sv.queryAll();
            LOGGER.debug("list = {}", list);
            long count = sv.queryCount();
            LOGGER.debug("count = {}", count);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaJPASqlTemplateQuery() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        IFleaConfigDataSV sv = (IFleaConfigDataSV) applicationContext.getBean("fleaConfigDataSV");
        FleaConfigData fleaConfigData = new FleaConfigData();
        fleaConfigData.setConfigType("huazie");
        fleaConfigData.setConfigCode("huazie");
        try {
            List<FleaConfigData> list = sv.query("select", fleaConfigData);
            LOGGER.debug("list={}", list);
            LOGGER.debug("count={}", list.size());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaJPASqlTemplateInsert() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        IFleaConfigDataSV sv = (IFleaConfigDataSV) applicationContext.getBean("fleaConfigDataSV");
        FleaConfigData fleaConfigData = new FleaConfigData();
        fleaConfigData.setConfigId(1L);
        fleaConfigData.setConfigType("huazie");
        fleaConfigData.setConfigCode("huazie");
        fleaConfigData.setConfigName("这是测试INSERT SQL 模板");
        fleaConfigData.setConfigDesc("Test");
        fleaConfigData.setConfigState(EntityStateEnum.IN_USE.getState());
        fleaConfigData.setData1("hello world");

        try {
            int ret = sv.insert("insert", fleaConfigData);
            LOGGER.debug("ret = {}", ret);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaJPASqlTemplateUpdate() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        IFleaConfigDataSV sv = (IFleaConfigDataSV) applicationContext.getBean("fleaConfigDataSV");
        FleaConfigData fleaConfigData = new FleaConfigData();
        fleaConfigData.setConfigId(1L);
        fleaConfigData.setConfigType("huazie1");
        fleaConfigData.setConfigCode("huazie1");
        try {
            int ret = sv.update("update", fleaConfigData);
            LOGGER.debug("ret = {}", ret);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaJPASqlTemplateDelete() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        IFleaConfigDataSV sv = (IFleaConfigDataSV) applicationContext.getBean("fleaConfigDataSV");
        FleaConfigData fleaConfigData = new FleaConfigData();
        fleaConfigData.setConfigId(1L);
        fleaConfigData.setConfigState(EntityStateEnum.IN_USE.getState());
        try {
            int ret = sv.delete("delete", fleaConfigData);
            LOGGER.debug("ret = {}", ret);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testJDBCSqlTemplateQuery() {
        // 测试 JDBC 接入 SELECT SQL 模板
        FleaJDBCConfig.init(DBSystemEnum.MySQL.getName(), "fleaconfig");
        FleaConfigData fleaConfigData = new FleaConfigData();
        fleaConfigData.setConfigType("huazie");
        fleaConfigData.setConfigCode("huazie");
        try {
            List<Map<String, Object>> results = FleaJDBCHelper.query("select", fleaConfigData);
            Assert.assertNotNull(results);
            LOGGER.debug(results.toString());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testJDBCSqlTemplateInsert() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        // 测试 JDBC 接入 SELECT SQL 模板
        FleaJDBCConfig.init(DBSystemEnum.MySQL.getName(), "fleaconfig");
        FleaConfigData fleaConfigData = new FleaConfigData();
        fleaConfigData.setConfigId(1L);
        fleaConfigData.setConfigType("huazie");
        fleaConfigData.setConfigCode("huazie");
        fleaConfigData.setConfigName("这是测试INSERT SQL 模板");
        fleaConfigData.setConfigDesc("Test");
        fleaConfigData.setConfigState(EntityStateEnum.IN_USE.getState());
        fleaConfigData.setData1("hello world");
        try {
            int ret = FleaJDBCHelper.insert("insert", fleaConfigData);
            LOGGER.debug("ret={}", ret);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testJDBCSqlTemplateUpdate() {
//        FleaFrameManager.getManager().setLocale(Locale.US);
        // 初始化
        FleaJDBCConfig.init(DBSystemEnum.MySQL.getName(), "fleaconfig");
        FleaConfigData fleaConfigData = new FleaConfigData();
        fleaConfigData.setConfigId(1L);
        fleaConfigData.setConfigType("huazie1");
        fleaConfigData.setConfigCode("huazie1");
        try {
            int ret = FleaJDBCHelper.update("update", fleaConfigData);
            LOGGER.debug("ret={}", ret);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testJDBCSqlTemplateDelete() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        FleaJDBCConfig.init(DBSystemEnum.MySQL.getName(), "fleaconfig");
        FleaConfigData fleaConfigData = new FleaConfigData();
        fleaConfigData.setConfigId(1L);
        fleaConfigData.setConfigState(EntityStateEnum.IN_USE.getState());
        try {
            int ret = FleaJDBCHelper.delete("delete", fleaConfigData);
            LOGGER.debug("ret={}", ret);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaConfigDataSpringBean() {
        FleaConfigDataSpringBean bean = (FleaConfigDataSpringBean) applicationContext.getBean("fleaConfigDataSpringBean");
        try {
            bean.getConfigDatas("huazie", "huazie");
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }
}