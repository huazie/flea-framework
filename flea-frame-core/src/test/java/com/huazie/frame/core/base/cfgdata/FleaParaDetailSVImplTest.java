package com.huazie.frame.core.base.cfgdata;

import com.huazie.frame.cache.AbstractFleaCache;
import com.huazie.frame.cache.memcached.MemcachedFleaCacheManager;
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
    public void init(){
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void getParaDetails() {
        IFleaParaDetailSV sv = (IFleaParaDetailSV) applicationContext.getBean("fleaParaDetailSVImpl");
        try {
            sv.getParaDetails("FLEA_RES_STATE","");
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }

    @Test
    public void getParaDetail() {
        IFleaParaDetailSV sv = (IFleaParaDetailSV) applicationContext.getBean("fleaParaDetailSVImpl");
        try {
            sv.getParaDetail("FLEAER_CERT_TYPE","1");
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }

    @Test
    public void testFleaCache() {

        MemcachedFleaCacheManager manager = MemcachedFleaCacheManager.getInstance();

        AbstractFleaCache cache = manager.getCache("fleaparadetail");
        LOGGER.debug("Cache={}", cache);

        //#### 1.  简单字符串
//		cache.put("menu1", "huazie");
        LOGGER.debug(cache.get("FLEAER_CERT_TYPE") + "");
        LOGGER.debug(cache.getCacheName() + ">>>" + cache.getCacheDesc());
    }

}