package com.huazie.fleaframework.core.base.cfgdata;

import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResClient;
import com.huazie.fleaframework.core.base.cfgdata.service.interfaces.IFleaJerseyResClientSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * FleaJersey 资源客户端单元测试类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FleaJerseyResClientTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJerseyResClientTest.class);

    @Autowired
    @Qualifier("resClientSV")
    private IFleaJerseyResClientSV fleaJerseyResClientSV;

    @Autowired
    private FleaConfigDataSpringBean bean;

    @Test
    public void testInsertClientUploadAuth() {
        FleaJerseyResClient resClient = new FleaJerseyResClient();
        resClient.setClientCode("FLEA_CLIENT_UPLOAD_AUTH");
        resClient.setResourceUrl("http://localhost:8080/fleafs");
        resClient.setResourceCode("upload");
        resClient.setServiceCode("FLEA_SERVICE_UPLOAD_AUTH");
        resClient.setRequestMode("post");
        resClient.setMediaType("application/xml");
        resClient.setClientInput("com.huazie.fleaframework.jersey.client.resource.pojo.upload.input.InputUploadAuthInfo");
        resClient.setClientOutput("com.huazie.fleaframework.jersey.client.resource.pojo.upload.output.OutputUploadAuthInfo");
        resClient.setState(EntityStateEnum.IN_USE.getState());
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
        FleaJerseyResClient resClient = new FleaJerseyResClient();
        resClient.setClientCode("FLEA_CLIENT_FILE_UPLOAD");
        resClient.setResourceUrl("http://localhost:8080/fleafs");
        resClient.setResourceCode("upload");
        resClient.setServiceCode("FLEA_SERVICE_FILE_UPLOAD");
        resClient.setRequestMode("fpost");
        resClient.setMediaType("multipart/form-data");
        resClient.setClientInput("com.huazie.fleaframework.jersey.client.resource.pojo.upload.input.InputFileUploadInfo");
        resClient.setClientOutput("com.huazie.fleaframework.jersey.client.resource.pojo.upload.output.OutputFileUploadInfo");
        resClient.setState(EntityStateEnum.IN_USE.getState());
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
        FleaJerseyResClient resClient = new FleaJerseyResClient();
        resClient.setClientCode("FLEA_CLIENT_DOWNLOAD_AUTH");
        resClient.setResourceUrl("http://localhost:8080/fleafs");
        resClient.setResourceCode("download");
        resClient.setServiceCode("FLEA_SERVICE_DOWNLOAD_AUTH");
        resClient.setRequestMode("post");
        resClient.setMediaType("application/xml");
        resClient.setClientInput("com.huazie.fleaframework.jersey.client.resource.pojo.download.input.InputDownloadAuthInfo");
        resClient.setClientOutput("com.huazie.fleaframework.jersey.client.resource.pojo.download.output.OutputDownloadAuthInfo");
        resClient.setState(EntityStateEnum.IN_USE.getState());
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
        FleaJerseyResClient resClient = new FleaJerseyResClient();
        resClient.setClientCode("FLEA_CLIENT_FILE_DOWNLOAD");
        resClient.setResourceUrl("http://localhost:8080/fleafs");
        resClient.setResourceCode("download");
        resClient.setServiceCode("FLEA_SERVICE_FILE_DOWNLOAD");
        resClient.setRequestMode("fget");
        resClient.setMediaType("multipart/form-data");
        resClient.setClientInput("com.huazie.fleaframework.jersey.client.resource.pojo.download.input.InputFileDownloadInfo");
        resClient.setClientOutput("com.huazie.fleaframework.jersey.client.resource.pojo.download.output.OutputFileDownloadInfo");
        resClient.setState(EntityStateEnum.IN_USE.getState());
        resClient.setCreateDate(DateUtils.getCurrentTime());
        resClient.setRemarks("文件下载服务");
        try {
            fleaJerseyResClientSV.save(resClient);
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

    @Test
    public void testGetResClient() {
        try {
            bean.getResClient("FLEA_CLIENT_FILE_DOWNLOAD");
        } catch (Exception e) {
            LOGGER.error("Exception:", e);
        }
    }

}
