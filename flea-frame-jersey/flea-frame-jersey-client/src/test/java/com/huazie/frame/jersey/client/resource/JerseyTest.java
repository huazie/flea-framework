package com.huazie.frame.jersey.client.resource;

import com.huazie.ffs.pojo.download.input.InputDownloadAuthInfo;
import com.huazie.ffs.pojo.download.input.InputFileDownloadInfo;
import com.huazie.ffs.pojo.download.output.OutputDownloadAuthInfo;
import com.huazie.ffs.pojo.download.output.OutputFileDownloadInfo;
import com.huazie.ffs.pojo.upload.input.InputUploadAuthInfo;
import com.huazie.ffs.pojo.upload.output.OutputUploadAuthInfo;
import com.huazie.frame.common.FleaFrameManager;
import com.huazie.frame.common.i18n.FleaI18nHelper;
import com.huazie.frame.common.util.IOUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.RandomCode;
import com.huazie.frame.common.util.json.GsonUtils;
import com.huazie.frame.common.util.xml.JABXUtils;
import com.huazie.frame.jersey.client.core.FleaJerseyClient;
import com.huazie.frame.jersey.client.request.RequestModeEnum;
import com.huazie.frame.jersey.client.response.Response;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyRequestData;
import com.huazie.frame.jersey.common.data.RequestBusinessData;
import com.huazie.frame.jersey.common.data.RequestPublicData;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.core.MediaType;
import java.util.Locale;

/**
 * <p>  </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class JerseyTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(JerseyTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testUploadAuth() {
        try {
            String clientCode = "FLEA_CLIENT_UPLOAD_AUTH";

            InputUploadAuthInfo uploadAuthInfo = new InputUploadAuthInfo();
            uploadAuthInfo.setFileName("美丽的风景.png");

            FleaJerseyClient client = applicationContext.getBean(FleaJerseyClient.class);

            Response<OutputUploadAuthInfo> response = client.invoke(clientCode, uploadAuthInfo, OutputUploadAuthInfo.class);

            LOGGER.debug("result = {}", response);
        } catch (Exception e) {
            LOGGER.debug("Exception = ", e);
        }
    }

    @Test
    public void testDownloadAuth() {
        try {
            String clientCode = "FLEA_CLIENT_DOWNLOAD_AUTH";

            InputDownloadAuthInfo downloadAuthInfo = new InputDownloadAuthInfo();
            downloadAuthInfo.setFileId("123123123123123123123");

            FleaJerseyClient client = applicationContext.getBean(FleaJerseyClient.class);

            Response<OutputDownloadAuthInfo> response = client.invoke(clientCode, downloadAuthInfo, OutputDownloadAuthInfo.class);

            LOGGER.debug("result = {}", response);
        } catch (Exception e) {
            LOGGER.debug("Exception = ", e);
        }
    }

    @Test
    public void testFileDownload() {
        try {
            String clientCode = "FLEA_CLIENT_FILE_DOWNLOAD";

            InputFileDownloadInfo input = new InputFileDownloadInfo();
            input.setToken(RandomCode.toUUID());

            FleaJerseyClient client = applicationContext.getBean(FleaJerseyClient.class);

            Response<OutputFileDownloadInfo> response = client.invoke(clientCode, input, OutputFileDownloadInfo.class);

            if(ObjectUtils.isNotEmpty(response.getOutput())) {
                String fileName = response.getOutput().getFileName();
                String fileInput = response.getOutput().getFileInput();
                LOGGER.debug("FILE_NAME = {}", fileName);
                LOGGER.debug("FILE = \n{}", fileInput);
                IOUtils.toFile(fileInput, "E:\\" + fileName);
            }

        } catch (Exception e) {
            LOGGER.debug("Exception = ", e);
        }
    }

    @Test
    public void testGetFleaRequest() throws Exception {
        FleaJerseyRequest request = new FleaJerseyRequest();
        FleaJerseyRequestData requestData = new FleaJerseyRequestData();

        RequestPublicData publicData = new RequestPublicData();
        publicData.setSystemUserId("1000");
        publicData.setSystemUserPassword("asd123");
        publicData.setResourceCode("upload");
        publicData.setServiceCode("FLEA_SERVICE_UPLOAD_AUTH");

        RequestBusinessData businessData = new RequestBusinessData();

        InputFileDownloadInfo input = new InputFileDownloadInfo();
        input.setToken("39b6b9cfd3da4675a01c7d159517bb1d");

        String inputJson = GsonUtils.toJsonString(input);
        businessData.setInput(inputJson);

        requestData.setPublicData(publicData);
        requestData.setBusinessData(businessData);

        request.setRequestData(requestData);

        String inputStr = JABXUtils.toXml(request, false);
        LOGGER.debug("Input = {}", inputStr);

        FleaJerseyRequest request1 = JABXUtils.fromXml(inputStr, FleaJerseyRequest.class);
        LOGGER.debug("FleaJerseyRequest = {}", request1);
    }

    @Test
    public void testMediaType() {

        String mediaTypeStr = "xml";

        MediaType mediaType = MediaType.valueOf(mediaTypeStr);

        LOGGER.debug("MediaType = {}", mediaType);

    }

    @Test
    public void testEnum() {

        RequestModeEnum modeEnum = RequestModeEnum.valueOf("GET1");

        LOGGER.debug("RequestModeEnum = {}", modeEnum.getMode());
    }

    @Test
    public void fleaI18NHelperTest() {
        FleaFrameManager.getManager().setLocale(Locale.CHINESE);
        try {
            FleaI18nHelper.i18n("ERROR0000000001", "error");
            FleaI18nHelper.i18n("ERROR-JERSEY-CLIENT0000000000", "error_jersey");
        } catch (Exception e) {
            LOGGER.error("Exception={}", e);
        }
    }
}
