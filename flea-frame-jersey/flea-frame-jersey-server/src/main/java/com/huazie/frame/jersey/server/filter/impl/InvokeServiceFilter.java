package com.huazie.frame.jersey.server.filter.impl;

import com.huazie.frame.common.util.json.GsonUtils;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.common.data.RequestBusinessData;
import com.huazie.frame.jersey.common.data.RequestPublicData;
import com.huazie.frame.jersey.common.data.ResponseBusinessData;
import com.huazie.frame.jersey.common.exception.FleaJerseyFilterException;
import com.huazie.frame.jersey.server.filter.IFleaJerseyFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Method;

/**
 * <p> 服务调用过滤器 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class InvokeServiceFilter implements IFleaJerseyFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(InvokeServiceFilter.class);

    @Override
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("InvokeServiceFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) Start");
        }

        // 获取响应业务报文
        ResponseBusinessData responseBusinessData = response.getResponseData().getBusinessData();

        // 获取请求公共报文
        RequestPublicData requestPublicData = request.getRequestData().getPublicData();
        RequestBusinessData requestBusinessData = request.getRequestData().getBusinessData();

        // 获取资源编码
        String resourceCode = requestPublicData.getResourceCode();
        // 获取服务编码
        String serviceCode = requestPublicData.getServiceCode();

        // 根据资源编码 和 服务编码 获取 资源服务配置数据

        // 获取资源服务接口
        String serviceInterfaces = "com.huazie.ffs.module.upload.service.interfaces.IFleaUploadSV";
        // 获取资源服务方法
        String serviceMethod = "uploadAuth";
        // 获取资源服务bean值
        String serviceBeanValue = "fleaUploadSVImpl";

        // 获取资源服务业务入参
        String inputParam = "com.huazie.ffs.pojo.upload.input.InputUploadAuthInfo";
        // 获取资源服务业务出参
        String outputParam = "com.huazie.ffs.pojo.upload.output.OutputUploadAuthInfo";

        // 获取Web应用上下文对象
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();

        Object obj = webApplicationContext.getBean(serviceBeanValue);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("InvokeServiceFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) Impl = {}", obj);
        }

        Class inputClazz = Class.forName(inputParam);

        Method method = obj.getClass().getMethod(serviceMethod, inputClazz);

        String inputJson = requestBusinessData.getInput();
        Object inputObj = GsonUtils.toEntity(inputJson, inputClazz);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("InvokeServiceFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) INPUT JSON  = {}", inputJson);
            LOGGER.debug("InvokeServiceFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) INPUT CLASS = {}", inputClazz);
            LOGGER.debug("InvokeServiceFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) INPUT OBJ   = {}", inputObj);
        }

        Object outputObj = method.invoke(obj, inputObj);
        Class outputClazz = Class.forName(outputParam);
        if (!outputClazz.isInstance(outputObj)) {
            // 资源【{0}】下的服务【{1}】请求异常：配置的出参【{2}】与服务方法【{3}】出参【{4}】类型不一致
            throw new FleaJerseyFilterException("");
        }
        String outputJson = GsonUtils.toJsonString(outputObj);
        responseBusinessData.setOutput(outputJson);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("InvokeServiceFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) OUTPUT JSON  = {}", outputJson);
            LOGGER.debug("InvokeServiceFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) OUTPUT CLASS = {}", outputClazz);
            LOGGER.debug("InvokeServiceFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) OUTPUT OBJ   = {}", outputObj);
            LOGGER.debug("InvokeServiceFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) End");
        }
    }

}
