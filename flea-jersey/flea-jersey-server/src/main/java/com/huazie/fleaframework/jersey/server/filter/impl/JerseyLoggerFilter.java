package com.huazie.fleaframework.jersey.server.filter.impl;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.xml.JABXUtils;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.fleaframework.core.common.pojo.FleaJerseyResServiceLogPOJO;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import com.huazie.fleaframework.jersey.common.data.RequestPublicData;
import com.huazie.fleaframework.jersey.common.data.ResponsePublicData;
import com.huazie.fleaframework.jersey.server.filter.IFleaJerseyFilter;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Flea Jersey 接口资源服务调用日志过滤器
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class JerseyLoggerFilter implements IFleaJerseyFilter {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(JerseyLoggerFilter.class);

    @Override
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws CommonException {
        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "Jersey Logger, Start");
        }

        // 请求公共报文
        RequestPublicData requestPublicData = request.getRequestData().getPublicData();
        // 响应公共报文
        ResponsePublicData responsePublicData = response.getResponseData().getPublicData();

        // 资源服务调用日志
        FleaJerseyResServiceLogPOJO resServiceLogPOJO = new FleaJerseyResServiceLogPOJO();
        // 资源编码
        resServiceLogPOJO.setResourceCode(requestPublicData.getResourceCode());
        // 服务编码
        resServiceLogPOJO.setServiceCode(requestPublicData.getServiceCode());
        // 请求入参
        resServiceLogPOJO.setInput(JABXUtils.toXml(request, false));
        // 响应出参
        resServiceLogPOJO.setOutput(JABXUtils.toXml(response, false));
        // 操作结果码
        resServiceLogPOJO.setResultCode(responsePublicData.getResultCode());
        // 操作结果信息
        resServiceLogPOJO.setResultMess(responsePublicData.getResultMess());
        // 操作账户编号
        resServiceLogPOJO.setAccountId(Long.valueOf(requestPublicData.getAccountId()));
        // 系统账户编号
        resServiceLogPOJO.setSystemAccountId(Long.valueOf(requestPublicData.getSystemAccountId()));

        // 获取Web应用上下文对象
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();

        // Flea Config 配置数据Bean
        FleaConfigDataSpringBean fleaConfigDataSpringBean = webApplicationContext.getBean(FleaConfigDataSpringBean.class);
        // 保存资源服务调用日志
        fleaConfigDataSpringBean.saveResServiceLog(resServiceLogPOJO);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "Jersey Logger, End");
        }
    }

}
