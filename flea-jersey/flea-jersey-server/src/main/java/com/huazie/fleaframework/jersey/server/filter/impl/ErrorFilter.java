package com.huazie.fleaframework.jersey.server.filter.impl;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.i18n.FleaI18nHelper;
import com.huazie.fleaframework.common.i18n.FleaI18nResEnum;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyI18nErrorMapping;
import com.huazie.fleaframework.jersey.common.FleaJerseyConstants;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponseData;
import com.huazie.fleaframework.jersey.common.data.RequestPublicData;
import com.huazie.fleaframework.jersey.common.data.ResponsePublicData;
import com.huazie.fleaframework.jersey.common.filter.config.FleaJerseyFilterConfig;
import com.huazie.fleaframework.jersey.common.filter.config.I18nErrorMapping;
import com.huazie.fleaframework.jersey.server.filter.IFleaJerseyErrorFilter;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Flea Jersey 接口的异常过滤器实现，用于将资源服务调用过程中
 * 产生的各种异常映射为对应的错误码并添加到公共报文中返回。
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class ErrorFilter extends JerseyLoggerFilter implements IFleaJerseyErrorFilter {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(ErrorFilter.class);

    @Override
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response, Throwable throwable) {
        Object obj = new Object() {};
        LOGGER.debug1(obj, "Catch Exception, Start");
        LOGGER.debug1(obj, "Exception : ", throwable);

        FleaJerseyResponseData responseData = response.getResponseData();
        if (ObjectUtils.isEmpty(responseData)) {
            responseData = new FleaJerseyResponseData();
            response.setResponseData(responseData);
        }

        ResponsePublicData responsePublicData = responseData.getPublicData();
        if (ObjectUtils.isEmpty(responsePublicData)) {
            responsePublicData = new ResponsePublicData();
            responseData.setPublicData(responsePublicData);
        }

        // 获取异常描述，并设置响应返回信息
        String errMsg = ObjectUtils.isEmpty(throwable.getCause()) ? throwable.getMessage() : throwable.getCause().getMessage();
        responsePublicData.setResultMess(errMsg);

        if (throwable instanceof CommonException) { // 自定义异常
            CommonException exception = (CommonException) throwable;
            // 异常国际码
            String i18nKey = exception.getKey();
            dealCommonException(request, responsePublicData, i18nKey, errMsg, exception);
        } else { // 其他异常
            responsePublicData.setResultCode(FleaJerseyConstants.ResponseResultConstants.RESULT_CODE_OTHER);
        }

        LOGGER.debug1(obj, "Catch Exception, End");

        try {
            if (ObjectUtils.isNotEmpty(request)) {
                super.doFilter(request, response); // 保存异常调用日志
            }
        } catch (CommonException e) {
            LOGGER.error1(obj, "保存异常调用日志出错：\n", e);
        }
    }

    /**
     * <p> 处理自定义异常，设置响应公共报文信息 </p>
     *
     * @param request            请求报文
     * @param responsePublicData 响应公共报文
     * @param i18nKey            国际码键
     * @param errMsg             错误信息
     * @since 1.0.0
     */
    private void dealCommonException(FleaJerseyRequest request, ResponsePublicData responsePublicData, String i18nKey, String errMsg, CommonException exception) {

        try {
            // 获取Web应用上下文对象
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            FleaConfigDataSpringBean fleaConfigDataSpringBean = webApplicationContext.getBean(FleaConfigDataSpringBean.class);

            LOGGER.debug1(new Object() {}, "FleaConfigDataSpringBean = {}", fleaConfigDataSpringBean);

            // 首先获取过滤器国际码和错误码映射配置
            I18nErrorMapping i18nErrorMapping = FleaJerseyFilterConfig.getI18nErrorMapping(i18nKey);
            if (ObjectUtils.isNotEmpty(i18nErrorMapping)) {
                responsePublicData.setResultCode(i18nErrorMapping.getErrorCode());
                setPublicResultMess(responsePublicData, i18nErrorMapping.getReturnMess(), exception);
            } else {
                // 过滤器中的国际码和错误码映射，应该由上面分支获取，如果上面没有查到，可以认为 错误码未配置
                if (ObjectUtils.isNotEmpty(i18nKey) && i18nKey.startsWith(FleaJerseyConstants.JerseyFilterConstants.PREFIX_ERROR_JERSEY_FILTER)) {
                    setResponsePublicDataNotConfig(responsePublicData, errMsg);
                    return;
                }
                // 获取请求公共报文
                RequestPublicData requestPublicData = request.getRequestData().getPublicData();
                // 获取资源编码
                String resourceCode = requestPublicData.getResourceCode();
                // 获取服务编码
                String serviceCode = requestPublicData.getServiceCode();
                // 获取资源服务下的国际码和错误码映射配置
                FleaJerseyI18nErrorMapping mapping = fleaConfigDataSpringBean.getMapping(resourceCode, serviceCode, i18nKey);
                if (ObjectUtils.isEmpty(mapping)) {
                    setResponsePublicDataNotConfig(responsePublicData, errMsg);
                } else {
                    responsePublicData.setResultCode(mapping.getErrorCode());
                    setPublicResultMess(responsePublicData, mapping.getReturnMess(), exception);
                }
            }
        } catch (Exception e) {
            LOGGER.error1(new Object() {}, "Exception : \n", e);
            responsePublicData.setResultCode(FleaJerseyConstants.ResponseResultConstants.RESULT_CODE_OTHER);
        }
    }

    private void setPublicResultMess(ResponsePublicData responsePublicData, String returnMess, CommonException exception) {
        // 如果配置的返回信息不为空，则响应公共报文中RESULT_MESS字段，依照配置的返回信息进行返回。
        if (StringUtils.isNotBlank(returnMess)) {
            responsePublicData.setResultMess(StringUtils.getRealValue(returnMess, exception.getValues()));
        }
    }

    private void setResponsePublicDataNotConfig(ResponsePublicData responsePublicData, String errMsg) {
        responsePublicData.setResultCode(FleaJerseyConstants.ResponseResultConstants.RESULT_CODE_NOT_CONFIG);
        responsePublicData.setResultMess(FleaI18nHelper.i18n("ERROR-JERSEY-FILTER0000000007", new String[]{errMsg}, FleaI18nResEnum.ERROR_JERSEY.getResName()));
    }

}