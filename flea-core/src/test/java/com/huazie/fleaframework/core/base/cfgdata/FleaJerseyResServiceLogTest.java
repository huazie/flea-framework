package com.huazie.fleaframework.core.base.cfgdata;

import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.fleaframework.core.common.pojo.FleaJerseyResServiceLogPOJO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FleaJerseyResServiceLogTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJerseyResServiceLogTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testSaveResServiceLog() {

        FleaConfigDataSpringBean bean = (FleaConfigDataSpringBean) applicationContext.getBean("fleaConfigDataSpringBean");

        try {
            FleaJerseyResServiceLogPOJO resServiceLogPOJO = new FleaJerseyResServiceLogPOJO();
            resServiceLogPOJO.setResourceCode("upload");
            resServiceLogPOJO.setServiceCode("FLEA_SERVICE_UPLOAD_AUTH");
            resServiceLogPOJO.setInput("XXXX");
            resServiceLogPOJO.setOutput("XXXX");
            resServiceLogPOJO.setResultCode("0");
            resServiceLogPOJO.setResultMess("success");
            resServiceLogPOJO.setAccountId(10000L);
            resServiceLogPOJO.setSystemAccountId(1000L);
            resServiceLogPOJO.setRemarks("测试");

            bean.saveResServiceLog(resServiceLogPOJO);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }

    }
}