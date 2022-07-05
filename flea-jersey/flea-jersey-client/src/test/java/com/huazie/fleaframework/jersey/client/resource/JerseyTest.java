package com.huazie.fleaframework.jersey.client.resource;

import com.huazie.ffs.pojo.download.input.InputDownloadAuthInfo;
import com.huazie.ffs.pojo.download.input.InputFileDownloadInfo;
import com.huazie.ffs.pojo.download.output.OutputDownloadAuthInfo;
import com.huazie.ffs.pojo.download.output.OutputFileDownloadInfo;
import com.huazie.ffs.pojo.upload.input.InputFileUploadInfo;
import com.huazie.ffs.pojo.upload.input.InputUploadAuthInfo;
import com.huazie.ffs.pojo.upload.output.OutputFileUploadInfo;
import com.huazie.ffs.pojo.upload.output.OutputUploadAuthInfo;
import com.huazie.fleaframework.common.DateFormatEnum;
import com.huazie.fleaframework.common.FleaSessionManager;
import com.huazie.fleaframework.common.IFleaUser;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.IOUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.RandomCode;
import com.huazie.fleaframework.common.util.json.GsonUtils;
import com.huazie.fleaframework.common.util.xml.JABXUtils;
import com.huazie.fleaframework.jersey.client.bean.FleaJerseyClient;
import com.huazie.fleaframework.jersey.client.request.RequestModeEnum;
import com.huazie.fleaframework.jersey.client.response.Response;
import com.huazie.fleaframework.jersey.common.FleaJerseyConstants;
import com.huazie.fleaframework.jersey.common.FleaJerseyManager;
import com.huazie.fleaframework.jersey.common.FleaUserImpl;
import com.huazie.fleaframework.jersey.common.data.FleaFileObject;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequestData;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import com.huazie.fleaframework.jersey.common.data.RequestBusinessData;
import com.huazie.fleaframework.jersey.common.data.RequestPublicData;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class JerseyTest {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(JerseyTest.class);

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        IFleaUser fleaUser = new FleaUserImpl();
        fleaUser.setAccountId(10000L);
        fleaUser.set("ACCOUNT_CODE", "13218010892");
        FleaSessionManager.setUserInfo(fleaUser);
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.debug("ApplicationContext={}", applicationContext);
    }

    @Test
    public void testMediaType() {
        String mediaTypeStr = "application/xml";
        MediaType mediaType = MediaType.valueOf(mediaTypeStr);
        LOGGER.debug("MediaType = {}", mediaType);
    }

    @Test
    public void testEnum() {
        RequestModeEnum modeEnum = RequestModeEnum.valueOf("GET1");
        LOGGER.debug("RequestModeEnum = {}", modeEnum.getMode());
    }

    @Test
    public void testGetFleaRequest() {
        FleaJerseyRequest request = new FleaJerseyRequest();
        FleaJerseyRequestData requestData = new FleaJerseyRequestData();

        RequestPublicData publicData = new RequestPublicData();
        publicData.setSystemAccountId("1000");
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
    public void testUploadFileOrigin() {
        FleaJerseyRequest request = new FleaJerseyRequest();
        FleaJerseyRequestData requestData = new FleaJerseyRequestData();

        RequestPublicData publicData = new RequestPublicData();
        publicData.setSystemAccountId("1000");
        publicData.setAccountId("11000");
        publicData.setResourceCode("upload");
        publicData.setServiceCode("FLEA_SERVICE_FILE_UPLOAD");

        RequestBusinessData businessData = new RequestBusinessData();

        InputFileUploadInfo input = new InputFileUploadInfo();
        input.setToken(RandomCode.toUUID());

        String inputJson = GsonUtils.toJsonString(input);
        businessData.setInput(inputJson);

        requestData.setPublicData(publicData);
        requestData.setBusinessData(businessData);

        request.setRequestData(requestData);

        String inputStr = JABXUtils.toXml(request, false);

        String resourceUrl = "http://localhost:8080/fleafs";

        WebTarget target = ClientBuilder.newClient()
                .register(MultiPartFeature.class)
                .target(resourceUrl)
                .path("upload")
                .path("fileUpload");

        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();

        File file = new File("E:\\IMG.jpg");
        FileDataBodyPart fileDataBodyPart = new FileDataBodyPart(FleaJerseyConstants.FormDataConstants.FORM_DATA_KEY_FILE, file);
        FormDataBodyPart formDataBodyPart = new FormDataBodyPart(FleaJerseyConstants.FormDataConstants.FORM_DATA_KEY_REQUEST, inputStr);
        formDataMultiPart.bodyPart(fileDataBodyPart);
        formDataMultiPart.bodyPart(formDataBodyPart);

        Entity<FormDataMultiPart> entity = Entity.entity(formDataMultiPart, MediaType.MULTIPART_FORM_DATA);
        FleaJerseyResponse response = target.request().post(entity, FleaJerseyResponse.class);

        LOGGER.debug("FleaJerseyResponse = {}", response);
    }

    @Test
    public void testDownloadFileOrigin() throws Exception {

        FleaJerseyRequest request = new FleaJerseyRequest();
        FleaJerseyRequestData requestData = new FleaJerseyRequestData();

        RequestPublicData publicData = new RequestPublicData();
        publicData.setSystemAccountId("1000");
        publicData.setAccountId("11000");
        publicData.setResourceCode("download");
        publicData.setServiceCode("FLEA_SERVICE_FILE_DOWNLOAD");

        RequestBusinessData businessData = new RequestBusinessData();

        InputFileDownloadInfo input = new InputFileDownloadInfo();
        input.setToken(RandomCode.toUUID());

        String inputJson = GsonUtils.toJsonString(input);
        if (ObjectUtils.isNotEmpty(inputJson)) {
            inputJson = URLEncoder.encode(inputJson, StandardCharsets.UTF_8.displayName());
        }
        businessData.setInput(inputJson);

        requestData.setPublicData(publicData);
        requestData.setBusinessData(businessData);

        request.setRequestData(requestData);

        String inputStr = JABXUtils.toXml(request, false);

        String resourceUrl = "http://localhost:8080/fleafs";

        WebTarget target = ClientBuilder.newClient()
                .register(MultiPartFeature.class)
                .target(resourceUrl)
                .path("download")
                .path("fileDownload");

        FormDataMultiPart formDataMultiPart = target.queryParam("REQUEST", inputStr).request().get(FormDataMultiPart.class);

        FleaJerseyManager.getManager().getFileContext().setFormDataMultiPart(formDataMultiPart);

        // 获取响应报文
        FormDataBodyPart responseFormData = FleaJerseyManager.getManager().getFormDataBodyPart(FleaJerseyConstants.FormDataConstants.FORM_DATA_KEY_RESPONSE);
        String responseData = responseFormData.getValue();

        FleaJerseyResponse response = JABXUtils.fromXml(responseData, FleaJerseyResponse.class);

        LOGGER.debug("Response = {}", response);

        // 获取文件信息
        FleaFileObject fileObject = FleaJerseyManager.getManager().getFileObject();
        String fileName = fileObject.getFileName();
        File downloadFile = fileObject.getFile();

        String fileId = DateUtils.date2String(null, DateFormatEnum.YYYYMMDD) + RandomCode.toUUID();
        if (downloadFile.exists()) {
            IOUtils.toFile(new FileInputStream(downloadFile), "E:\\" + fileId + "_" + fileName);
        }

    }

    @Test
    public void testUploadAuth() throws CommonException {
        String clientCode = "FLEA_CLIENT_UPLOAD_AUTH";

        InputUploadAuthInfo uploadAuthInfo = new InputUploadAuthInfo();
        uploadAuthInfo.setFileName("美丽的风景.png");

        FleaJerseyClient client = applicationContext.getBean(FleaJerseyClient.class);

        Response<OutputUploadAuthInfo> response = client.invoke(clientCode, uploadAuthInfo, OutputUploadAuthInfo.class);

        LOGGER.debug("result = {}", response);
    }

    @Test
    public void testDownloadAuth() throws CommonException {
        String clientCode = "FLEA_CLIENT_DOWNLOAD_AUTH";

        InputDownloadAuthInfo downloadAuthInfo = new InputDownloadAuthInfo();
        downloadAuthInfo.setFileId("123123123123123123123");

        FleaJerseyClient client = applicationContext.getBean(FleaJerseyClient.class);

        Response<OutputDownloadAuthInfo> response = client.invoke(clientCode, downloadAuthInfo, OutputDownloadAuthInfo.class);

        LOGGER.debug("result = {}", response);
    }

    @Test
    public void testUploadFile() throws CommonException {
        String clientCode = "FLEA_CLIENT_FILE_UPLOAD";

        InputFileUploadInfo input = new InputFileUploadInfo();
        input.setToken(RandomCode.toUUID());

        File file = new File("E:\\IMG.jpg");
        FleaJerseyManager.getManager().addFileDataBodyPart(file);

        FleaJerseyClient client = applicationContext.getBean(FleaJerseyClient.class);

        Response<OutputFileUploadInfo> response = client.invoke(clientCode, input, OutputFileUploadInfo.class);

        LOGGER.debug("result = {}", response);
    }

    @Test
    public void testDownloadFile() throws FileNotFoundException, CommonException {
        String clientCode = "FLEA_CLIENT_FILE_DOWNLOAD";

        InputFileDownloadInfo input = new InputFileDownloadInfo();
        input.setToken(RandomCode.toUUID());

        FleaJerseyClient client = applicationContext.getBean(FleaJerseyClient.class);

        Response<OutputFileDownloadInfo> response = client.invoke(clientCode, input, OutputFileDownloadInfo.class);

        LOGGER.debug("result = {}", response);

        OutputFileDownloadInfo output = response.getOutput();

        // 获取文件信息
        FleaFileObject fileObject = FleaJerseyManager.getManager().getFileObject();
        String fileName = fileObject.getFileName();
        File downloadFile = fileObject.getFile();

        String uploadSystemAccountId = output.getUploadSystemAcctId();
        String uploadAccountId = output.getUploadAcctId();
        String uploadDate = output.getUploadDate();

        if (downloadFile.exists()) {
            IOUtils.toFile(new FileInputStream(downloadFile), "E:\\" + uploadDate + "_" + uploadSystemAccountId + "_" + uploadAccountId + "_" + fileName);
        }
    }
}
