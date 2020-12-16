package com.huazie.frame.jersey.server.filter.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ExceptionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.ReflectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.common.util.json.GsonUtils;
import com.huazie.frame.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResService;
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

/**
 * <p> 服务调用过滤器 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class InvokeServiceFilter implements IFleaJerseyFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvokeServiceFilter.class);

    @Override
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws CommonException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Start");
        }

        // 获取请求公共报文
        RequestPublicData requestPublicData = request.getRequestData().getPublicData();
        RequestBusinessData requestBusinessData = request.getRequestData().getBusinessData();

        // 获取资源编码
        String resourceCode = requestPublicData.getResourceCode();
        // 获取服务编码
        String serviceCode = requestPublicData.getServiceCode();

        // 获取Web应用上下文对象
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();

        // Flea Config 配置数据Bean
        FleaConfigDataSpringBean fleaConfigDataSpringBean = webApplicationContext.getBean(FleaConfigDataSpringBean.class);
        // 根据资源编码 和 服务编码 获取 资源服务配置数据
        FleaJerseyResService resService = fleaConfigDataSpringBean.getResService(serviceCode, resourceCode);
        // 未能找到指定资源服务配置数据【service_code = {0} , resource_code = {1}】
        ObjectUtils.checkEmpty(resService, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000008", serviceCode, resourceCode);

        // 获取资源服务接口
        String serviceInterfaces = resService.getServiceInterfaces();
        // 获取资源服务方法
        String serviceMethod = resService.getServiceMethod();
        // 获取资源服务业务入参
        String inputParam = resService.getServiceInput();
        // 获取资源服务业务出参
        String outputParam = resService.getServiceOutput();

        Class<?> serviceInterfacesClazz = ReflectUtils.forName(serviceInterfaces);
        // 请检查服务端配置【service_code = {0} , resource_code = {1}】: 【{2} = {3}】非法
        ObjectUtils.checkEmpty(serviceInterfacesClazz, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000009", serviceCode,
                resourceCode, "service_interfaces", serviceInterfaces);

        // 根据服务接口，从Web应用上下文中获取Spring注入的服务
        Object obj = webApplicationContext.getBean(serviceInterfacesClazz);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Impl = {}", obj);
        }

        Class inputClazz = ReflectUtils.forName(inputParam);
        // 请检查服务端配置【service_code = {0} , resource_code = {1}】: 【{2} = {3}】非法
        ObjectUtils.checkEmpty(inputClazz, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000009", serviceCode,
                resourceCode, "service_input", inputParam);

        String inputJson = requestBusinessData.getInput();
        Object inputObj = GsonUtils.toEntity(inputJson, inputClazz);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("INPUT JSON  = {}", inputJson);
            LOGGER.debug("INPUT CLASS = {}", inputClazz);
            LOGGER.debug("INPUT OBJ   = {}", inputObj);
        }

        Object outputObj = ReflectUtils.invoke(obj, serviceMethod, inputObj, inputClazz);

        Class outputClazz = null;
        if (StringUtils.isNotBlank(outputParam)) {
            outputClazz = ReflectUtils.forName(outputParam);
        }

        // 请检查服务端配置【service_code = {0} , resource_code = {1}】: 【{2} = {3}】非法
        ObjectUtils.checkEmpty(outputClazz, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000009", serviceCode,
                resourceCode, "service_output", outputParam);

        if (ObjectUtils.isNotEmpty(outputClazz) && !outputClazz.isInstance(outputObj)) {
            // 资源【{0}】下的服务【{1}】请求异常：配置的出参【{2}】与服务方法【{3}】出参【{4}】类型不一致
            ExceptionUtils.throwCommonException(FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000010", resourceCode,
                    serviceCode, outputParam, serviceMethod, outputObj.getClass().getName());
        }

        // 获取响应业务报文
        ResponseBusinessData responseBusinessData = response.getResponseData().getBusinessData();

        String outputJson = null;
        if (ObjectUtils.isNotEmpty(outputObj)) {
            outputJson = GsonUtils.toJsonString(outputObj);
            responseBusinessData.setOutput(outputJson);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("OUTPUT OBJ   = {}", outputObj);
            LOGGER.debug("OUTPUT CLASS = {}", outputClazz);
            LOGGER.debug("OUTPUT JSON  = {}", outputJson);
            LOGGER.debug("End");
        }
    }

}
