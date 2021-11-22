package com.huazie.fleaframework.core.base.cfgdata;

import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResource;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaJerseyResourceSV;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FleaJerseyResourceTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJerseyResServiceTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testInsertUploadResource() {
        IFleaJerseyResourceSV fleaJerseyResourceSV = (IFleaJerseyResourceSV) applicationContext.getBean("resourceSV");
        FleaJerseyResource resource = new FleaJerseyResource();
        resource.setResourceCode("upload");
        resource.setResourceName("上传资源");
        resource.setResourcePackages("com.huazie.ffs.module.upload.request");
        resource.setState(EntityStateEnum.IN_USE.getState());
        resource.setCreateDate(DateUtils.getCurrentTime());
        try {
            fleaJerseyResourceSV.save(resource);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testInsertDownloadResource() {
        IFleaJerseyResourceSV fleaJerseyResourceSV = (IFleaJerseyResourceSV) applicationContext.getBean("resourceSV");
        FleaJerseyResource resource = new FleaJerseyResource();
        resource.setResourceCode("download");
        resource.setResourceName("下载资源");
        resource.setResourcePackages("com.huazie.ffs.module.download.request");
        resource.setState(EntityStateEnum.IN_USE.getState());
        resource.setCreateDate(DateUtils.getCurrentTime());
        try {
            fleaJerseyResourceSV.save(resource);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testGetResource() {
        FleaConfigDataSpringBean bean = (FleaConfigDataSpringBean) applicationContext.getBean("fleaConfigDataSpringBean");
        try {
            bean.getResource("upload");
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testGetResourcePackages() {
        FleaConfigDataSpringBean bean = (FleaConfigDataSpringBean) applicationContext.getBean("fleaConfigDataSpringBean");
        try {
            bean.getResourcePackages();
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }
}