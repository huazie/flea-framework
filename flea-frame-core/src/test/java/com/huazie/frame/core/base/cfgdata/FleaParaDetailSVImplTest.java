package com.huazie.frame.core.base.cfgdata;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.memcached.MemcachedFleaCacheManager;
import com.huazie.frame.cache.memcached.MemcachedSpringCacheManager;
import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaParaDetailSV;
import com.huazie.frame.core.common.FleaEntityConstants;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Locale;

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
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void getParaDetail() {
        IFleaParaDetailSV sv = (IFleaParaDetailSV) applicationContext.getBean("fleaParaDetailSVImpl");
        try {
            sv.getParaDetail("FLEAER_CERT_TYPE", "1");
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaCache() {
        try {
            MemcachedFleaCacheManager manager = MemcachedFleaCacheManager.getInstance();

            AbstractFleaCache cache = manager.getCache("fleaparadetail");
            LOGGER.debug("Cache={}", cache);

            //#### 复杂配置参数
//		    cache.put("menu1", "huazie");
//            cache.get("menu1");
            cache.get("FLEAER_CERT_TYPE_1");
//            cache.get("FLEA_RES_STATE");
            LOGGER.debug(cache.getCacheName() + ">>>" + cache.getCacheDesc());
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testSpringCache() {
        try {
            MemcachedSpringCacheManager memcachedCacheManager = (MemcachedSpringCacheManager) applicationContext.getBean("cacheManager");
            LOGGER.debug("MemcachedCacheManager={}", memcachedCacheManager);

            AbstractSpringCache cache = memcachedCacheManager.getCache("fleaparadetail");
            LOGGER.debug("Cache={}", cache);

            //#### 1.  简单字符串
//			cache.put("menu1", "huazie");
            cache.get("menu1");
//            cache.get("menu1", String.class);

            //#### 2.  简单对象(要是可以序列化的对象)
//			String user = new String("huazie");
//			cache.put("user", user);
//			TestCache.LOGGER.debug(cache.get("user", String.class));

            //#### 3.  List塞对象
//			List<String> userList = new ArrayList<String>();
//			userList.add("huazie");
//			userList.add("lgh");
//			cache.put("user_list", userList);

//			TestCache.LOGGER.debug(cache.get("user_list",userList.getClass()).toString());

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
        fleaParaDetail.setParaType("FLEA_RES_STATE");
        fleaParaDetail.setParaName("物品状态");
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
            sv.save("insert", fleaParaDetail);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

}