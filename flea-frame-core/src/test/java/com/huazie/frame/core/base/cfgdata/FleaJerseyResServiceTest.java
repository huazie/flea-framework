package com.huazie.frame.core.base.cfgdata;

import com.huazie.frame.common.util.DateUtils;
import com.huazie.frame.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyI18nErrorMapping;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaJerseyI18nErrorMappingSV;
import com.huazie.frame.core.base.cfgdata.service.interfaces.IFleaJerseyResServiceSV;
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
public class FleaJerseyResServiceTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaJerseyResServiceTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testInsertResService() {
        IFleaJerseyResServiceSV fleaJerseyResServiceSV = (IFleaJerseyResServiceSV) applicationContext.getBean("resServiceSV");
        FleaJerseyResService resService = new FleaJerseyResService();
        resService.setServiceCode("FLEA_SERVICE_UPLOAD_AUTH");
        resService.setResourceCode("upload");
        resService.setServiceName("上传鉴权服务");
        resService.setServiceInterfaces("com.huazie.ffs.module.upload.service.interfaces.IFleaUploadSV");
        resService.setServiceMethod("uploadAuth");
        resService.setServiceInput("com.huazie.ffs.pojo.upload.input.InputUploadAuthInfo");
        resService.setServiceOutput("com.huazie.ffs.pojo.upload.output.OutputUploadAuthInfo");
        resService.setState(EntityStateEnum.IN_USE.getValue());
        resService.setCreateDate(DateUtils.getCurrentTime());
        try {
            fleaJerseyResServiceSV.save(resService);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testFleaConfigDataSpringBean() {
        FleaConfigDataSpringBean bean = (FleaConfigDataSpringBean) applicationContext.getBean("fleaConfigDataSpringBean");
        try {
            bean.getResService("FLEA_SERVICE_UPLOAD_AUTH", "upload");
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

}
