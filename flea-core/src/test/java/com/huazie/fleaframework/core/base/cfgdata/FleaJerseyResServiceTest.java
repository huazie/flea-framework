package com.huazie.fleaframework.core.base.cfgdata;

import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaJerseyResServiceSV;
import org.junit.Before;
import org.junit.Test;
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

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJerseyResServiceTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testInsertUploadAuth() {
        IFleaJerseyResServiceSV fleaJerseyResServiceSV = (IFleaJerseyResServiceSV) applicationContext.getBean("resServiceSV");
        FleaJerseyResService resService = new FleaJerseyResService();
        resService.setServiceCode("FLEA_SERVICE_UPLOAD_AUTH");
        resService.setResourceCode("upload");
        resService.setServiceName("上传鉴权服务");
        resService.setServiceInterfaces("com.huazie.ffs.module.upload.service.interfaces.IFleaUploadSV");
        resService.setServiceMethod("uploadAuth");
        resService.setServiceInput("com.huazie.ffs.pojo.upload.input.InputUploadAuthInfo");
        resService.setServiceOutput("com.huazie.ffs.pojo.upload.output.OutputUploadAuthInfo");
        resService.setState(EntityStateEnum.IN_USE.getState());
        resService.setCreateDate(DateUtils.getCurrentTime());
        try {
            fleaJerseyResServiceSV.save(resService);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testInsertFileUpload() {
        IFleaJerseyResServiceSV fleaJerseyResServiceSV = (IFleaJerseyResServiceSV) applicationContext.getBean("resServiceSV");
        FleaJerseyResService resService = new FleaJerseyResService();
        resService.setServiceCode("FLEA_SERVICE_FILE_UPLOAD");
        resService.setResourceCode("upload");
        resService.setServiceName("文件上传服务");
        resService.setServiceInterfaces("com.huazie.ffs.module.upload.service.interfaces.IFleaUploadSV");
        resService.setServiceMethod("fileUpload");
        resService.setServiceInput("com.huazie.ffs.pojo.upload.input.InputFileUploadInfo");
        resService.setServiceOutput("com.huazie.ffs.pojo.upload.output.OutputFileUploadInfo");
        resService.setState(EntityStateEnum.IN_USE.getState());
        resService.setCreateDate(DateUtils.getCurrentTime());
        try {
            fleaJerseyResServiceSV.save(resService);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testInsertFileDownload() {
        IFleaJerseyResServiceSV fleaJerseyResServiceSV = (IFleaJerseyResServiceSV) applicationContext.getBean("resServiceSV");
        FleaJerseyResService resService = new FleaJerseyResService();
        resService.setServiceCode("FLEA_SERVICE_FILE_DOWNLOAD");
        resService.setResourceCode("download");
        resService.setServiceName("文件下载服务");
        resService.setServiceInterfaces("com.huazie.ffs.module.download.service.interfaces.IFleaDownloadSV");
        resService.setServiceMethod("fileDownload");
        resService.setServiceInput("com.huazie.ffs.pojo.download.input.InputFileDownloadInfo");
        resService.setServiceOutput("com.huazie.ffs.pojo.download.output.OutputFileDownloadInfo");
        resService.setState(EntityStateEnum.IN_USE.getState());
        resService.setCreateDate(DateUtils.getCurrentTime());
        try {
            fleaJerseyResServiceSV.save(resService);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testInsertDownloadAuth() {
        IFleaJerseyResServiceSV fleaJerseyResServiceSV = (IFleaJerseyResServiceSV) applicationContext.getBean("resServiceSV");
        FleaJerseyResService resService = new FleaJerseyResService();
        resService.setServiceCode("FLEA_SERVICE_DOWNLOAD_AUTH");
        resService.setResourceCode("download");
        resService.setServiceName("下载鉴权服务");
        resService.setServiceInterfaces("com.huazie.ffs.module.download.service.interfaces.IFleaDownloadSV");
        resService.setServiceMethod("downloadAuth");
        resService.setServiceInput("com.huazie.ffs.pojo.download.input.InputDownloadAuthInfo");
        resService.setServiceOutput("com.huazie.ffs.pojo.download.output.OutputDownloadAuthInfo");
        resService.setState(EntityStateEnum.IN_USE.getState());
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
            bean.getResService("FLEA_SERVICE_FILE_DOWNLOAD", "download");
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

}
