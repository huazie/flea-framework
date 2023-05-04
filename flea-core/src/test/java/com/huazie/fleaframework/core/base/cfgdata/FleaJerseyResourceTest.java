package com.huazie.fleaframework.core.base.cfgdata;

import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResource;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaJerseyResourceSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * FleaJersey资源单元测试类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaJerseyResourceTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJerseyResServiceTest.class);

    @Autowired
    @Qualifier("resourceSV")
    private IFleaJerseyResourceSV fleaJerseyResourceSV;

    @Autowired
    private FleaConfigDataSpringBean bean;

    @Test
    public void testInsertUploadResource() throws CommonException {
        FleaJerseyResource resource = new FleaJerseyResource();
        resource.setResourceCode("upload");
        resource.setResourceName("上传资源");
        resource.setResourcePackages("com.huazie.ffs.module.upload.request");
        resource.setState(EntityStateEnum.IN_USE.getState());
        resource.setCreateDate(DateUtils.getCurrentTime());
        fleaJerseyResourceSV.save(resource);
    }

    @Test
    public void testInsertDownloadResource() throws CommonException {
        FleaJerseyResource resource = new FleaJerseyResource();
        resource.setResourceCode("download");
        resource.setResourceName("下载资源");
        resource.setResourcePackages("com.huazie.ffs.module.download.request");
        resource.setState(EntityStateEnum.IN_USE.getState());
        resource.setCreateDate(DateUtils.getCurrentTime());
        fleaJerseyResourceSV.save(resource);
    }

    @Test
    public void testGetResource() throws CommonException {
        bean.getResource("upload");
    }

    @Test
    public void testGetResourcePackages() throws CommonException {
        bean.getResourcePackages();
    }
}