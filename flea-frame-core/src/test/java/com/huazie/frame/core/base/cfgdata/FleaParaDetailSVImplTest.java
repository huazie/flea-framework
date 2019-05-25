package com.huazie.frame.core.base.cfgdata;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractFleaCacheManager;
import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.AbstractSpringCacheManager;
import com.huazie.frame.cache.FleaCacheManagerFactory;
import com.huazie.frame.cache.common.CacheEnum;
import com.huazie.frame.cache.memcached.MemCachedSpringCacheManager;
import com.huazie.frame.cache.redis.RedisSpringCacheManager;
import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaParaDetailSV;
import com.huazie.frame.core.common.FleaEntityConstants;
import com.huazie.frame.db.common.DBSystemEnum;
import com.huazie.frame.db.jdbc.FleaJDBCHelper;
import com.huazie.frame.db.jdbc.config.FleaJDBCConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Time;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FleaParaDetailSVImplTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaParaDetailSVImplTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void getParaDetailById() {
        IFleaParaDetailSV sv = (IFleaParaDetailSV) applicationContext.getBean("fleaParaDetailSVImpl");
        try {
            LOGGER.debug(sv.query(2L).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getParaDetails() {
        IFleaParaDetailSV sv = (IFleaParaDetailSV) applicationContext.getBean("fleaParaDetailSVImpl");
        try {
            sv.getParaDetails("FLEA_RES_STATE", "");
            Thread.sleep(1000000);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void getParaDetail() {
        IFleaParaDetailSV sv = (IFleaParaDetailSV) applicationContext.getBean("fleaParaDetailSVImpl");
        try {
            sv.getParaDetail("FLEAER_CERT_TYPE", "1");
            Thread.sleep(1000000);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testMemCachedFleaCache() {
        try {
            AbstractFleaCacheManager manager = FleaCacheManagerFactory.getFleaCacheManager(CacheEnum.MemCached.getName());
            LOGGER.debug("MemCachedCacheManager={}", manager);

            AbstractFleaCache cache = manager.getCache("fleaparadetail");
            LOGGER.debug("Cache={}", cache);

            //#### 复杂配置参数
            cache.get("FLEA_RES_STATE");
//            cache.delete("FLEA_RES_STATE");
            LOGGER.debug(cache.getCacheName() + ">>>" + cache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testRedisFleaCache() {
        try {
            AbstractFleaCacheManager manager = FleaCacheManagerFactory.getFleaCacheManager(CacheEnum.Redis.getName());
            LOGGER.debug("RedisCacheManager={}", manager);
            AbstractFleaCache cache = manager.getCache("fleaparadetail");
            LOGGER.debug("Cache={}", cache);

            //#### 复杂配置参数
//            cache.get("FLEA_RES_STATE");
            cache.delete("FLEA_RES_STATE");
            cache.delete("FLEAER_CERT_TYPE_1");
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

            AbstractSpringCache cache = manager.getCache("fleaparadetail");
            LOGGER.debug("Cache={}", cache);

            //#### 1.  简单字符串
//			cache.put("menu1", "huazie");
            cache.get("menu1");
//            cache.get("menu1", String.class);

            //#### 2.  简单对象(要是可以序列化的对象)
//			String user = new String("huazie");
//			cache.put("user", user);
//			LOGGER.debug(cache.get("user", String.class));

            //#### 3.  List塞对象
//			List<String> userList = new ArrayList<String>();
//			userList.add("huazie");
//			userList.add("lgh");
//			cache.put("user_list", userList);

//			LOGGER.debug(cache.get("user_list",userList.getClass()).toString());

        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testRedisSpringCache() {
        try {
            AbstractSpringCacheManager manager = (RedisSpringCacheManager) applicationContext.getBean("redisSpringCacheManager");
            LOGGER.debug("RedisCacheManager={}", manager);

            AbstractSpringCache cache = manager.getCache("fleaparadetail");
            LOGGER.debug("Cache={}", cache);

            //#### 1.  简单字符串
//			cache.put("menu1", "huazie");
//            cache.get("menu1");
//            cache.get("menu1", String.class);

            //#### 2.  简单对象(要是可以序列化的对象)
//			String user = new String("huazie");
//			cache.put("user", user);
//			LOGGER.debug(cache.get("user", String.class));
//            cache.get("FLEA_RES_STATE");
            cache.clear();

            //#### 3.  List塞对象
//			List<String> userList = new ArrayList<String>();
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
        IFleaParaDetailSV sv = (IFleaParaDetailSV) applicationContext.getBean("fleaParaDetailSVImpl");
        try {
            FleaParaDetail fleaParaDetail = sv.query(2L);
            LOGGER.debug("row:{}", fleaParaDetail);
            List<FleaParaDetail> list = sv.queryAll();
            LOGGER.debug("list:{}", list);
            long count = sv.queryCount();
            LOGGER.debug("count:{}", count);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaJPASqlTemplateQuery() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        IFleaParaDetailSV sv = (IFleaParaDetailSV) applicationContext.getBean("fleaParaDetailSVImpl");
        FleaParaDetail fleaParaDetail = new FleaParaDetail();
        fleaParaDetail.setParaType("huazie");
        fleaParaDetail.setParaCode("huazie");
        try {
            List<FleaParaDetail> list = sv.query("select", fleaParaDetail);
            LOGGER.debug("list={}", list);
            LOGGER.debug("count={}", list.size());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaJPASqlTemplateInsert() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        IFleaParaDetailSV sv = (IFleaParaDetailSV) applicationContext.getBean("fleaParaDetailSVImpl");
        FleaParaDetail fleaParaDetail = new FleaParaDetail();
        fleaParaDetail.setParaId(13L);
        fleaParaDetail.setParaType("huazie");
        fleaParaDetail.setParaCode("huazie");
        fleaParaDetail.setParaName("这是测试INSERT SQL 模板");
        fleaParaDetail.setPara1("hello world");
        fleaParaDetail.setParaState(FleaEntityConstants.FleaParaDetailConstants.PARA_STATE_IN_USE);
        fleaParaDetail.setParaDesc("Test");
        try {
            int ret = sv.insert("insert", fleaParaDetail);
            LOGGER.debug("ret={}", ret);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaJPASqlTemplateUpdate() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        IFleaParaDetailSV sv = (IFleaParaDetailSV) applicationContext.getBean("fleaParaDetailSVImpl");
        FleaParaDetail fleaParaDetail = new FleaParaDetail();
        fleaParaDetail.setParaId(13L);
        fleaParaDetail.setParaType("huazie1");
        fleaParaDetail.setParaCode("huazie1");
        try {
            int ret = sv.update("update", fleaParaDetail);
            LOGGER.debug("ret={}", ret);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaJPASqlTemplateDelete() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        IFleaParaDetailSV sv = (IFleaParaDetailSV) applicationContext.getBean("fleaParaDetailSVImpl");
        FleaParaDetail fleaParaDetail = new FleaParaDetail();
        fleaParaDetail.setParaId(13L);
        fleaParaDetail.setParaState(FleaEntityConstants.FleaParaDetailConstants.PARA_STATE_IN_USE);
        try {
            int ret = sv.delete("delete", fleaParaDetail);
            LOGGER.debug("ret={}", ret);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testJDBCSqlTemplateQuery() {
        // 测试 JDBC 接入 SELECT SQL 模板
        FleaJDBCConfig.init(DBSystemEnum.MySQL.getName(), "fleaconfig");
        FleaParaDetail fleaParaDetail = new FleaParaDetail();
        fleaParaDetail.setParaType("huazie1");
        fleaParaDetail.setParaCode("huazie1");
        try {
            List<Map<String, Object>> results = FleaJDBCHelper.query("select", fleaParaDetail);
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
        FleaParaDetail fleaParaDetail = new FleaParaDetail();
        fleaParaDetail.setParaId(13L);
        fleaParaDetail.setParaType("huazie");
        fleaParaDetail.setParaCode("huazie");
        fleaParaDetail.setParaName("这是测试INSERT SQL 模板");
        fleaParaDetail.setPara1("hello world");
        fleaParaDetail.setParaState(FleaEntityConstants.FleaParaDetailConstants.PARA_STATE_IN_USE);
        fleaParaDetail.setParaDesc("Test");
        try {
            int ret = FleaJDBCHelper.insert("insert", fleaParaDetail);
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
        FleaParaDetail fleaParaDetail = new FleaParaDetail();
        fleaParaDetail.setParaId(13L);
        fleaParaDetail.setParaType("huazie1");
        fleaParaDetail.setParaCode("huazie1");
        try {
            int ret = FleaJDBCHelper.update("update", fleaParaDetail);
            LOGGER.debug("ret={}", ret);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testJDBCSqlTemplateDelete() {
        FleaFrameManager.getManager().setLocale(Locale.US);
        FleaJDBCConfig.init(DBSystemEnum.MySQL.getName(), "fleaconfig");
        FleaParaDetail fleaParaDetail = new FleaParaDetail();
        fleaParaDetail.setParaId(13L);
        fleaParaDetail.setParaState(FleaEntityConstants.FleaParaDetailConstants.PARA_STATE_IN_USE);
        try {
            int ret = FleaJDBCHelper.delete("delete", fleaParaDetail);
            LOGGER.debug("ret={}", ret);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

}