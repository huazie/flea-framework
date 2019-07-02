package com.huazie.frame.jersey.server.filter.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.i18n.FleaI18nHelper;
import com.huazie.frame.common.i18n.FleaI18nResEnum;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.core.base.cfgdata.bean.FleaConfigDataSpringBean;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyI18nErrorMapping;
import com.huazie.frame.jersey.common.FleaJerseyConstants;
import com.huazie.frame.jersey.common.data.FleaJerseyRequest;
import com.huazie.frame.jersey.common.data.FleaJerseyResponse;
import com.huazie.frame.jersey.common.data.RequestPublicData;
import com.huazie.frame.jersey.common.data.ResponsePublicData;
import com.huazie.frame.jersey.server.filter.IFleaJerseyErrorFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * <p> 异常过滤器实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ErrorFilter implements IFleaJerseyErrorFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response, Throwable throwable) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ErrorFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) Start");
            LOGGER.debug("ErrorFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) Exception : ", throwable);
        }

        ResponsePublicData responsePublicData = response.getResponseData().getPublicData();

        // 获取异常描述，并设置响应返回信息
        String errMsg = ObjectUtils.isEmpty(throwable.getCause()) ? throwable.getMessage() : throwable.getCause().getMessage();
        responsePublicData.setResultMess(errMsg);

        if (throwable instanceof CommonException) { // 自定义异常
            CommonException exception = (CommonException) throwable;
            // 异常国际码
            String i18nKey = exception.getKey();
            dealCommonException(request, responsePublicData, i18nKey, errMsg);
        } else { // 其他异常
            responsePublicData.setResultCode(FleaJerseyConstants.ResponseResultConstants.RESULT_CODE_OTHER);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ErrorFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) End");
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
    private void dealCommonException(FleaJerseyRequest request, ResponsePublicData responsePublicData, String i18nKey, String errMsg) {

        try {
            // 获取Web应用上下文对象
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            FleaConfigDataSpringBean fleaConfigDataSpringBean = webApplicationContext.getBean(FleaConfigDataSpringBean.class);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("ErrorFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) FleaConfigDataSpringBean = {}", fleaConfigDataSpringBean);
            }

            // 首先获取过滤器国际码和错误码映射配置
            FleaJerseyI18nErrorMapping mapping = fleaConfigDataSpringBean.getMapping(FleaJerseyConstants.JerseyFilterConstants.RESOURCE_CODE_FILTER,
                    FleaJerseyConstants.JerseyFilterConstants.SERVICE_CODE_FILTER, i18nKey);
            if (ObjectUtils.isNotEmpty(mapping)) {
                responsePublicData.setResultCode(mapping.getErrorCode());
            } else {
                // 过滤器中的国际码和错误码映射，应该由上面分支获取，如果上面没有查到，可以认为 错误码未配置
                if (ObjectUtils.isNotEmpty(i18nKey) && i18nKey.startsWith(FleaJerseyConstants.JerseyFilterConstants.PREFIX_ERROR_JERSEY_FILTER)) {
                    responsePublicData.setResultCode(FleaJerseyConstants.ResponseResultConstants.RESULT_CODE_NOT_CONFIG);
                    responsePublicData.setResultMess(FleaI18nHelper.i18n("ERROR-JERSEY-FILTER0000000007", new String[]{errMsg}, FleaI18nResEnum.ERROR_JERSEY.getResName()));
                    return;
                }
                // 获取请求公共报文
                RequestPublicData requestPublicData = request.getRequestData().getPublicData();
                // 获取资源编码
                String resourceCode = requestPublicData.getResourceCode();
                // 获取服务编码
                String serviceCode = requestPublicData.getServiceCode();
                // 获取资源服务下的国际码和错误码映射配置
                mapping = fleaConfigDataSpringBean.getMapping(resourceCode, serviceCode, i18nKey);
                if (ObjectUtils.isEmpty(mapping)) {
                    responsePublicData.setResultCode(FleaJerseyConstants.ResponseResultConstants.RESULT_CODE_NOT_CONFIG);
                    responsePublicData.setResultMess(FleaI18nHelper.i18n("ERROR-JERSEY-FILTER0000000007", new String[]{errMsg}, FleaI18nResEnum.ERROR_JERSEY.getResName()));
                } else {
                    responsePublicData.setResultCode(mapping.getErrorCode());
                }
            }
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("ErrorFilter##doFilter(FleaJerseyRequest, FleaJerseyResponse) Exception : ", ObjectUtils.isEmpty(e.getCause()) ? e.getMessage() : e.getCause().getMessage());
            }
            responsePublicData.setResultCode(FleaJerseyConstants.ResponseResultConstants.RESULT_CODE_OTHER);
        }
    }

}