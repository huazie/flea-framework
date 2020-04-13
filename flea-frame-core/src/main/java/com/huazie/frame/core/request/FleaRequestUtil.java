package com.huazie.frame.core.request;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.core.common.FleaCoreConstants;
import com.huazie.frame.core.filter.taskchain.FleaFilterTaskChainManager;
import com.huazie.frame.core.request.config.FleaRequestConfig;
import com.huazie.frame.core.request.config.Property;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p> Flea Request工具类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaRequestUtil {

    /**
     * <p> 执行过滤器任务 </p>
     *
     * @param servletRequest  请求对象
     * @param servletResponse 响应对象
     * @since 1.0.0
     */
    public static void doFilterTask(ServletRequest servletRequest, ServletResponse servletResponse) throws CommonException {
        FleaFilterTaskChainManager.getManager().doFilterTask(servletRequest, servletResponse);
    }

    /**
     * <p> 重定向到错误页面 </p>
     *
     * @param servletRequest  请求对象
     * @param servletResponse 响应对象
     * @since 1.0.0
     */
    public static void sendRedirectToErrorPage(ServletRequest servletRequest, ServletResponse servletResponse, Throwable throwable) throws IOException {
        if (ObjectUtils.isEmpty(throwable)) {
            return;
        }
        String errorMsg = "?ERROR_MSG=" + throwable.getMessage();
        sendRedirectToOtherPage(servletRequest, servletResponse, FleaCoreConstants.FleaRequestConfigConstants.REDIRECT_URL_ERROR_KEY, errorMsg);
    }

    /**
     * <p> 重定向到登录页面 </p>
     *
     * @param servletRequest  请求对象
     * @param servletResponse 响应对象
     * @since 1.0.0
     */
    public static void sendRedirectToLoginPage(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        sendRedirectToOtherPage(servletRequest, servletResponse, FleaCoreConstants.FleaRequestConfigConstants.REDIRECT_URL_LOGIN_KEY, null);
    }

    /**
     * <p> 重定向至其他页面 </p>
     *
     * @param servletRequest  请求对象
     * @param servletResponse 响应对象
     * @param redirectUrlKey  重定向URL配置KEY
     * @since 1.0.0
     */
    public static void sendRedirectToOtherPage(ServletRequest servletRequest, ServletResponse servletResponse, String redirectUrlKey, String urlParam) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Property redirectUrlProp = FleaRequestConfig.getFleaUrl().getRedirectUrlProperty(redirectUrlKey);
        if (ObjectUtils.isNotEmpty(response) && ObjectUtils.isNotEmpty(redirectUrlProp) && StringUtils.isNotEmpty(redirectUrlProp.getValue())) {
            if (StringUtils.isNotBlank(urlParam)) {
                response.sendRedirect(request.getContextPath() + redirectUrlProp.getValue() + urlParam);
            } else {
                response.sendRedirect(request.getContextPath() + redirectUrlProp.getValue());
            }
        }
    }

}
