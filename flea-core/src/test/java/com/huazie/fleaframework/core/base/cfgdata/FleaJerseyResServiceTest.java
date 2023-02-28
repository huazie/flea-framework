package com.huazie.fleaframework.core.base.cfgdata;

import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaJerseyResServiceSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * FleaJersey资源服务单元测试类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaJerseyResServiceTest {

    @Autowired
    @Qualifier("resServiceSV")
    private IFleaJerseyResServiceSV fleaJerseyResServiceSV;

    @Autowired
    private FleaConfigDataSpringBean bean;

    @Test
    public void testInsertUploadAuth() throws CommonException {
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
        fleaJerseyResServiceSV.save(resService);
    }

    @Test
    public void testInsertFileUpload() throws CommonException {
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
        fleaJerseyResServiceSV.save(resService);
    }

    @Test
    public void testInsertFileDownload() throws CommonException {
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
        fleaJerseyResServiceSV.save(resService);
    }

    @Test
    public void testInsertDownloadAuth() throws CommonException {
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
        fleaJerseyResServiceSV.save(resService);
    }

    @Test
    public void testFleaConfigDataSpringBean() throws CommonException {
        bean.getResService("download", "FLEA_SERVICE_FILE_DOWNLOAD");
    }

}
