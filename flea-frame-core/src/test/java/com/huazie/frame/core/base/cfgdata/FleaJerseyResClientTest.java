package com.huazie.frame.core.base.cfgdata;

import com.huazie.frame.common.util.DateUtils;
import com.huazie.frame.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResClient;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaJerseyResClientSV;
import com.huazie.frame.core.common.EntityStateEnum;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyResClientTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaJerseyResClientTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testInsertResClient() {
        IFleaJerseyResClientSV fleaJerseyResClientSV = (IFleaJerseyResClientSV) applicationContext.getBean("resClientSV");
        FleaJerseyResClient resClient = new FleaJerseyResClient();
        resClient.setClientCode("FLEA_CLIENT_UPLOAD_AUTH");
        resClient.setResourceUrl("http://localhost:8080/fleafs");
        resClient.setResourceCode("upload");
        resClient.setServiceCode("FLEA_SERVICE_UPLOAD_AUTH");
        resClient.setRequestMode("post");
        resClient.setMediaType("application/xml");
        resClient.setClientInput("com.huazie.ffs.pojo.upload.input.InputUploadAuthInfo");
        resClient.setClientOutput("com.huazie.ffs.pojo.upload.output.OutputUploadAuthInfo");
        resClient.setState(EntityStateEnum.IN_USE.getValue());
        resClient.setCreateDate(DateUtils.getCurrentTime());
        resClient.setRemarks("第一个资源客户端测试配置");
        try {
            fleaJerseyResClientSV.save(resClient);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaConfigDataSpringBean() {
        FleaConfigDataSpringBean bean = (FleaConfigDataSpringBean) applicationContext.getBean("fleaConfigDataSpringBean");
        try {
            bean.getResClient("FLEA_CLIENT_UPLOAD_AUTH");
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

}
