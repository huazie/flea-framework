package com.huazie.fleaframework.jersey.server.filter.impl;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.exceptions.FleaException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.ReflectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.common.util.json.GsonUtils;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import com.huazie.fleaframework.jersey.common.data.RequestBusinessData;
import com.huazie.fleaframework.jersey.common.data.RequestPublicData;
import com.huazie.fleaframework.jersey.common.data.ResponseBusinessData;
import com.huazie.fleaframework.jersey.common.exceptions.FleaJerseyFilterException;
import com.huazie.fleaframework.jersey.server.filter.IFleaJerseyFilter;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 资源服务调用过滤器实现，它是Flea Jersey接口的核心逻辑。
 *
 * <p> 它首先从请求公共报文中获取资源编码【RESOURCE_CODE】 和
 * 服务编码【SERVICE_CODE】，并根据它俩获取相关资源服务配置数据，
 * 其中包括资源服务接口、方法、出入参【由服务提供方约定】等。
 *
 * <p> 然后根据服务接口，从Web应用上下文中获取Spring注入的服务，
 * 取请求业务报文JSON串转换为资源服务方法的入参对象，通过反射调用
 * 对应的资源服务方法，获取业务返回报文，并添加至响应业务报文中。
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class InvokeServiceFilter implements IFleaJerseyFilter {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(InvokeServiceFilter.class);

    @Override
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws CommonException, FleaException {
        Object obj = new Object() {};
        LOGGER.debug1(obj, "Invoke Service, Start");

        // 请求公共报文
        RequestPublicData requestPublicData = request.getRequestData().getPublicData();
        // 请求业务报文
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
        FleaJerseyResService resService = fleaConfigDataSpringBean.getResService(resourceCode, serviceCode);
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
        // 请检查服务端配置【service_code = {0} , resource_code = {1}】:【{2} = {3}】非法
        ObjectUtils.checkEmpty(serviceInterfacesClazz, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000009",
                serviceCode, resourceCode, "service_interfaces", serviceInterfaces);

        // 根据服务接口，从Web应用上下文中获取Spring注入的服务
        Object serviceObj = webApplicationContext.getBean(serviceInterfacesClazz);

        LOGGER.debug1(obj, "Impl = {}", serviceObj);

        Class inputClazz = ReflectUtils.forName(inputParam);
        // 请检查服务端配置【service_code = {0} , resource_code = {1}】:【{2} = {3}】非法
        ObjectUtils.checkEmpty(inputClazz, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000009",
                serviceCode, resourceCode, "service_input", inputParam);

        String inputJson = requestBusinessData.getInput();
        Object inputObj = GsonUtils.toEntity(inputJson, inputClazz);

        LOGGER.debug1(obj, "INPUT JSON  = {}", inputJson);
        LOGGER.debug1(obj, "INPUT CLASS = {}", inputClazz);
        LOGGER.debug1(obj, "INPUT OBJ   = {}", inputObj);

        Object outputObj = ReflectUtils.invoke(serviceObj, serviceMethod, inputObj, inputClazz);

        Class outputClazz = null;
        if (StringUtils.isNotBlank(outputParam)) {
            outputClazz = ReflectUtils.forName(outputParam);
        }

        // 请检查服务端配置【service_code = {0} , resource_code = {1}】:【{2} = {3}】非法
        ObjectUtils.checkEmpty(outputClazz, FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000009",
                serviceCode, resourceCode, "service_output", outputParam);

        if (ObjectUtils.isNotEmpty(outputClazz) && !outputClazz.isInstance(outputObj)) {
            // 资源【{0}】下的服务【{1}】请求异常：配置的出参【{2}】与服务方法【{3}】出参【{4}】类型不一致
            ExceptionUtils.throwCommonException(FleaJerseyFilterException.class, "ERROR-JERSEY-FILTER0000000010",
                    resourceCode, serviceCode, outputParam, serviceMethod, outputObj.getClass().getName());
        }

        // 响应业务报文
        ResponseBusinessData responseBusinessData = response.getResponseData().getBusinessData();

        String outputJson = null;
        if (ObjectUtils.isNotEmpty(outputObj)) {
            outputJson = GsonUtils.toJsonString(outputObj);
            responseBusinessData.setOutput(outputJson);
        }

        LOGGER.debug1(obj, "OUTPUT OBJ   = {}", outputObj);
        LOGGER.debug1(obj, "OUTPUT CLASS = {}", outputClazz);
        LOGGER.debug1(obj, "OUTPUT JSON  = {}", outputJson);
        LOGGER.debug1(obj, "Invoke Service, End");
    }

}
