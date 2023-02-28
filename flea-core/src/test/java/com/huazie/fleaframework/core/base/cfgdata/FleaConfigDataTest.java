package com.huazie.fleaframework.core.base.cfgdata;

import com.huazie.fleaframework.cache.AbstractFleaCache;
import com.huazie.fleaframework.cache.AbstractFleaCacheManager;
import com.huazie.fleaframework.cache.AbstractSpringCache;
import com.huazie.fleaframework.cache.AbstractSpringCacheManager;
import com.huazie.fleaframework.cache.common.CacheEnum;
import com.huazie.fleaframework.cache.common.FleaCacheManagerFactory;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.FleaFrameManager;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaConfigData;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaConfigDataSV;
import com.huazie.fleaframework.db.common.DBSystemEnum;
import com.huazie.fleaframework.db.jdbc.FleaJDBCHelper;
import com.huazie.fleaframework.db.jdbc.config.FleaJDBCConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * 配置数据单元测试类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaConfigDataTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaConfigDataTest.class);

    @Autowired
    @Qualifier("fleaConfigDataSV")
    private IFleaConfigDataSV sv;

    @Autowired
    @Qualifier("memCachedSpringCacheManager")
    private AbstractSpringCacheManager memCachedSpringCacheManager;

    @Autowired
    @Qualifier("redisShardedSpringCacheManager")
    private AbstractSpringCacheManager redisShardedSpringCacheManager;

    @Autowired
    private FleaConfigDataSpringBean bean;

    @Test
    public void getConfigDataById() throws CommonException {
        FleaConfigData fleaConfigData = sv.query(1L);
        LOGGER.debug("FleaConfigData = {}", fleaConfigData);
    }

    @Test
    public void getConfigDatas() throws CommonException {
        sv.getConfigDatas("huazie", "");
    }

    @Test
    public void getConfigData() throws CommonException {
        sv.getConfigData("huazie", "huazie");
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

            //#### 复杂配置参数
            Set<String> cacheKey = cache.getCacheKey();
            LOGGER.debug("CacheKey = {}", cacheKey);
//            cache.get("FLEA_CLIENT_UPLOAD_AUTH");
//            cache.get("FLEAER_CERT_TYPE_1");
//            cache.delete("FLEA_CLIENT_UPLOAD_AUTH");

//            cache.clear();
            LOGGER.debug(cache.getCacheName() + ">>>" + cache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testMemCachedSpringCache() {
        try {
            LOGGER.debug("MemCachedCacheManager={}", memCachedSpringCacheManager);

            AbstractSpringCache cache = memCachedSpringCacheManager.getCache("fleaconfigdata");
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
            LOGGER.debug("RedisCacheManager={}", redisShardedSpringCacheManager);

            AbstractSpringCache cache = redisShardedSpringCacheManager.getCache("fleaconfigdata");
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
    public void testFleaJPAQuery() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        FleaConfigData fleaConfigData = sv.query(1L);
        LOGGER.debug("row = {}", fleaConfigData);
        List<FleaConfigData> list = sv.queryAll();
        LOGGER.debug("list = {}", list);
        long count = sv.queryCount();
        LOGGER.debug("count = {}", count);
    }

    @Test
    public void testFleaJPASqlTemplateQuery() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        FleaConfigData fleaConfigData = new FleaConfigData();
        fleaConfigData.setConfigType("huazie");
        fleaConfigData.setConfigCode("huazie");
        List<FleaConfigData> list = sv.queryAll("select", fleaConfigData);
        LOGGER.debug("list={}", list);
        LOGGER.debug("count={}", list.size());
    }

    @Test
    public void testFleaJPASqlTemplateInsert() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        FleaConfigData fleaConfigData = new FleaConfigData();
        fleaConfigData.setConfigId(1L);
        fleaConfigData.setConfigType("huazie");
        fleaConfigData.setConfigCode("huazie");
        fleaConfigData.setConfigName("这是测试INSERT SQL 模板");
        fleaConfigData.setConfigDesc("Test");
        fleaConfigData.setConfigState(EntityStateEnum.IN_USE.getState());
        fleaConfigData.setData1("hello world");

        int ret = sv.insert("insert", fleaConfigData);
        LOGGER.debug("ret = {}", ret);
    }

    @Test
    public void testFleaJPASqlTemplateUpdate() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        FleaConfigData fleaConfigData = new FleaConfigData();
        fleaConfigData.setConfigId(1L);
        fleaConfigData.setConfigType("huazie1");
        fleaConfigData.setConfigCode("huazie1");
        int ret = sv.update("update", fleaConfigData);
        LOGGER.debug("ret = {}", ret);
    }

    @Test
    public void testFleaJPASqlTemplateDelete() throws CommonException {
        FleaFrameManager.getManager().setLocale(Locale.US);
        FleaConfigData fleaConfigData = new FleaConfigData();
        fleaConfigData.setConfigId(1L);
        fleaConfigData.setConfigState(EntityStateEnum.IN_USE.getState());
        int ret = sv.delete("delete", fleaConfigData);
        LOGGER.debug("ret = {}", ret);
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
    public void testFleaConfigDatas() throws CommonException {
        bean.getConfigDatas("huazie", "huazie");
    }

    @Test
    public void testFleaConfigData() throws CommonException {
        bean.getConfigData("huazie", "huazie");
    }
}