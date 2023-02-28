package com.huazie.fleaframework.core.base.cfgdata;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.fleaframework.core.common.pojo.FleaJerseyResServiceLogPOJO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * FleaJersey资源服务日志单元测试类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaJerseyResServiceLogTest {

    @Autowired
    private FleaConfigDataSpringBean bean;

    @Test
    public void testSaveResServiceLog() throws CommonException {
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
    }
}