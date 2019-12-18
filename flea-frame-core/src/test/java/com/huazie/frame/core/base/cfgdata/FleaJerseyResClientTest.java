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

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaJerseyResClientTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testInsertClientUploadAuth() {
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
        resClient.setRemarks("上传鉴权服务");
        try {
            fleaJerseyResClientSV.save(resClient);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testInsertClientFileUpload() {
        IFleaJerseyResClientSV fleaJerseyResClientSV = (IFleaJerseyResClientSV) applicationContext.getBean("resClientSV");
        FleaJerseyResClient resClient = new FleaJerseyResClient();
        resClient.setClientCode("FLEA_CLIENT_FILE_UPLOAD");
        resClient.setResourceUrl("http://localhost:8080/fleafs");
        resClient.setResourceCode("upload");
        resClient.setServiceCode("FLEA_SERVICE_FILE_UPLOAD");
        resClient.setRequestMode("fpost");
        resClient.setMediaType("multipart/form-data");
        resClient.setClientInput("com.huazie.ffs.pojo.upload.input.InputFileUploadInfo");
        resClient.setClientOutput("com.huazie.ffs.pojo.upload.output.OutputFileUploadInfo");
        resClient.setState(EntityStateEnum.IN_USE.getValue());
        resClient.setCreateDate(DateUtils.getCurrentTime());
        resClient.setRemarks("文件上传服务");
        try {
            fleaJerseyResClientSV.save(resClient);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testInsertClientDownloadAuth() {
        IFleaJerseyResClientSV fleaJerseyResClientSV = (IFleaJerseyResClientSV) applicationContext.getBean("resClientSV");
        FleaJerseyResClient resClient = new FleaJerseyResClient();
        resClient.setClientCode("FLEA_CLIENT_DOWNLOAD_AUTH");
        resClient.setResourceUrl("http://localhost:8080/fleafs");
        resClient.setResourceCode("download");
        resClient.setServiceCode("FLEA_SERVICE_DOWNLOAD_AUTH");
        resClient.setRequestMode("post");
        resClient.setMediaType("application/xml");
        resClient.setClientInput("com.huazie.ffs.pojo.download.input.InputDownloadAuthInfo");
        resClient.setClientOutput("com.huazie.ffs.pojo.download.output.OutputDownloadAuthInfo");
        resClient.setState(EntityStateEnum.IN_USE.getValue());
        resClient.setCreateDate(DateUtils.getCurrentTime());
        resClient.setRemarks("下载鉴权服务");
        try {
            fleaJerseyResClientSV.save(resClient);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testInsertClientFileDownload() {
        IFleaJerseyResClientSV fleaJerseyResClientSV = (IFleaJerseyResClientSV) applicationContext.getBean("resClientSV");
        FleaJerseyResClient resClient = new FleaJerseyResClient();
        resClient.setClientCode("FLEA_CLIENT_FILE_DOWNLOAD");
        resClient.setResourceUrl("http://localhost:8080/fleafs");
        resClient.setResourceCode("download");
        resClient.setServiceCode("FLEA_SERVICE_FILE_DOWNLOAD");
        resClient.setRequestMode("get");
        resClient.setMediaType("application/xml");
        resClient.setClientInput("com.huazie.ffs.pojo.download.input.InputFileDownloadInfo");
        resClient.setClientOutput("com.huazie.ffs.pojo.download.output.OutputFileDownloadInfo");
        resClient.setState(EntityStateEnum.IN_USE.getValue());
        resClient.setCreateDate(DateUtils.getCurrentTime());
        resClient.setRemarks("文件下载服务");
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
