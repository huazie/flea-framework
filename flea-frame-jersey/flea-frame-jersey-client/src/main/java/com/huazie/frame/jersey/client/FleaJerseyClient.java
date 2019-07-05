package com.huazie.frame.jersey.client;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.common.util.json.GsonUtils;
import com.huazie.frame.jersey.client.response.ResponseResult;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyRequestData;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.common.data.FleaJerseyResponseData;
import com.huazie.frame.jersey.common.data.RequestBusinessData;
import com.huazie.frame.jersey.common.data.RequestPublicData;
import com.huazie.frame.jersey.common.data.ResponseBusinessData;
import com.huazie.frame.jersey.common.data.ResponsePublicData;
import com.huazie.frame.jersey.common.exception.FleaJerseyClientException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * <p> Flea Jersey 客户端 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaJerseyClient {

    /**
     * <p> Flea Jersey客户端调用 </p>
     *
     * @param clientCode  客户端编码
     * @param input       业务入参
     * @param outputClazz 业务出参类对象
     * @param <T>         业务出参
     * @return 响应结果
     * @throws Exception
     * @since 1.0.0
     */
    public static <T> ResponseResult<T> invoke(String clientCode, Object input, Class<T> outputClazz) throws Exception {

        if (StringUtils.isBlank(clientCode)) {
            // 客户端编码不能为空
            throw new FleaJerseyClientException("ERROR-JERSEY-CLIENT0000000001");
        }

        if (ObjectUtils.isEmpty(input)) {
            // 业务入参不能为空
            throw new FleaJerseyClientException("ERROR-JERSEY-CLIENT0000000002");
        }

        if (ObjectUtils.isEmpty(outputClazz)) {
            // 业务出参类不能为空
            throw new FleaJerseyClientException("ERROR-JERSEY-CLIENT0000000003");
        }

        // 获取Jersey客户端配置
        // 资源地址
        String resourceUrl = "http://localhost:8080/fleafs";
        // 资源编码
        String resourceCode = "upload";
        // 服务编码
        String serviceCode = "FLEA_SERVICE_UPLOAD_AUTH";

        String inputParam = "com.huazie.ffs.pojo.upload.input.InputUploadAuthInfo";
        String outputParam = "com.huazie.ffs.pojo.upload.output.OutputUploadAuthInfo";

        Class inputClazz = Class.forName(inputParam);
        if (!inputClazz.isInstance(input)) {
            // 请检查客户端配置【client_code = {0}】：配置的入参【{1}】类型和实际传入的入参【{2}】类型不一致
            throw new FleaJerseyClientException("ERROR-JERSEY-CLIENT0000000004", clientCode, inputParam, input.getClass().getName());
        }

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(resourceUrl);

        FleaJerseyRequest request = createFleaJerseyRequest(resourceCode, serviceCode, input);

        Entity<FleaJerseyRequest> entity = Entity.entity(request, MediaType.APPLICATION_XML_TYPE);
        FleaJerseyResponse response = target.path(resourceCode).request().post(entity, FleaJerseyResponse.class);

        if (ObjectUtils.isEmpty(response)) {
            // 资源服务请求异常：响应报文为空
            throw new FleaJerseyClientException("ERROR-JERSEY-CLIENT0000000005");
        }

        FleaJerseyResponseData responseData = response.getResponseData();
        if (ObjectUtils.isEmpty(responseData)) {
            // 资源服务请求异常：响应报文为空
            throw new FleaJerseyClientException("ERROR-JERSEY-CLIENT0000000005");
        }

        ResponsePublicData responsePublicData = responseData.getPublicData();
        if (ObjectUtils.isEmpty(responsePublicData)) {
            // 资源服务请求异常：响应公共报文为空
            throw new FleaJerseyClientException("ERROR-JERSEY-CLIENT0000000006");
        }

        ResponseResult<T> responseResult = new ResponseResult<T>();
        T output = null;

        // 判断资源服务是否请求成功
        if (responsePublicData.isSuccess()) {
            // 获取资源服务响应业务报文
            ResponseBusinessData businessData = responseData.getBusinessData();
            if (ObjectUtils.isNotEmpty(businessData)) {
                output = GsonUtils.toEntity(businessData.getOutput(), outputClazz);
            }
        } else {
            // 错误码
            responseResult.setRetCode(responsePublicData.getResultCode());
            // 错误信息
            responseResult.setRetMess(responsePublicData.getResultMess());
        }

        Class mClazz = Class.forName(outputParam);
        if (ObjectUtils.isNotEmpty(output) && !mClazz.isInstance(output)) {
            // 请检查客户端配置【client_code = {0}】：配置的出参【{1}】类型和实际需要返回的出参【{2}】类型不一致
            throw new FleaJerseyClientException("ERROR-JERSEY-CLIENT0000000007", clientCode, outputParam, output.getClass().getName());
        }

        // 设置业务出参
        responseResult.setOutput(output);

        return responseResult;
    }

    private static FleaJerseyRequest createFleaJerseyRequest(String resourceCode, String serviceCode, Object input) {
        FleaJerseyRequest request = new FleaJerseyRequest();

        FleaJerseyRequestData requestData = new FleaJerseyRequestData();

        RequestPublicData publicData = createRequestPublicData(resourceCode, serviceCode);
        RequestBusinessData businessData = createRequestBusinessData(input);

        requestData.setPublicData(publicData);
        requestData.setBusinessData(businessData);

        request.setRequestData(requestData);
        return request;
    }

    private static RequestPublicData createRequestPublicData(String resourceCode, String serviceCode) {
        RequestPublicData publicData = new RequestPublicData();
        publicData.setSystemUserId("1000");
        publicData.setSystemUserPassword("asd123");
        publicData.setResourceCode(resourceCode);
        publicData.setServiceCode(serviceCode);
        return publicData;
    }

    private static RequestBusinessData createRequestBusinessData(Object input) {
        RequestBusinessData businessData = new RequestBusinessData();
        String inputJson = GsonUtils.toJsonString(input);
        businessData.setInput(inputJson);
        return businessData;
    }

}
