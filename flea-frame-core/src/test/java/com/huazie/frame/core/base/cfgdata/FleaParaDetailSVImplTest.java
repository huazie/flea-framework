package com.huazie.frame.core.base.cfgdata;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.AbstractSpringCache;
import com.huazie.frame.cache.memcached.MemcachedFleaCacheManager;
import com.huazie.frame.cache.memcached.MemcachedSpringCacheManager;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaParaDetailSV;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FleaParaDetailSVImplTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaParaDetailSVImplTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
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
            LOGGER.debug(cache.get("menu1") + "");
            LOGGER.debug(cache.get("FLEAER_CERT_TYPE_1") + "");
            LOGGER.debug(cache.get("FLEA_RES_STATE") + "");
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
            LOGGER.debug(cache.get("menu1") + "");

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

}