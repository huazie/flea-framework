package com.huazie.frame.core.request;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.core.common.FleaCoreCommonException;
import com.huazie.frame.core.filter.taskchain.FleaFilterTaskChainManager;
import com.huazie.frame.core.request.config.FleaRequestConfig;
import com.huazie.frame.core.request.config.FleaSession;
import com.huazie.frame.core.request.config.FleaUrl;
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
    public static void sendRedirectToErrorPage(ServletRequest servletRequest, ServletResponse servletResponse, Throwable throwable) throws CommonException {
        if (ObjectUtils.isEmpty(throwable)) {
            return;
        }
        String errorMsg = "?ERROR_MSG=" + throwable.getMessage();
        sendRedirectToOtherPage(servletRequest, servletResponse, FleaUrl.REDIRECT_URL_ERROR_KEY, errorMsg);
    }

    /**
     * <p> 重定向到登录页面 </p>
     *
     * @param servletRequest  请求对象
     * @param servletResponse 响应对象
     * @since 1.0.0
     */
    public static void sendRedirectToLoginPage(ServletRequest servletRequest, ServletResponse servletResponse) throws CommonException {
        sendRedirectToOtherPage(servletRequest, servletResponse, FleaUrl.REDIRECT_URL_LOGIN_KEY, null);
    }

    /**
     * <p> 重定向至其他页面 </p>
     *
     * @param servletRequest  请求对象
     * @param servletResponse 响应对象
     * @param redirectUrlKey  重定向URL配置KEY
     * @since 1.0.0
     */
    public static void sendRedirectToOtherPage(ServletRequest servletRequest, ServletResponse servletResponse, String redirectUrlKey, String urlParam) throws CommonException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Property redirectUrlProp = getFleaUrl().getRedirectUrlProperty(redirectUrlKey);
        if (ObjectUtils.isNotEmpty(response) && ObjectUtils.isNotEmpty(redirectUrlProp) && StringUtils.isNotEmpty(redirectUrlProp.getValue())) {
            try {
                if (StringUtils.isNotBlank(urlParam)) {
                    response.sendRedirect(request.getContextPath() + redirectUrlProp.getValue() + urlParam);
                } else {
                    response.sendRedirect(request.getContextPath() + redirectUrlProp.getValue());
                }
            } catch (IOException e) {
                throw new FleaCoreCommonException("ERROR-CORE-COMMON0000000000", e.getMessage());
            }
        }
    }

    /**
     * <p> 获取用户SESSION信息键 </p>
     *
     * @return 用户SESSION信息键
     * @throws FleaCoreCommonException Flea Core 通用异常类
     * @since 1.0.0
     */
    public static String getUserSessionKey() throws FleaCoreCommonException {
        return getFleaSession().getUserSessionKey();
    }

    /**
     * <p> 获取用户SESSION空闲保持时间（单位：秒） </p>
     *
     * @return 用户SESSION空闲保持时间
     * @throws FleaCoreCommonException Flea Core 通用异常类
     * @since 1.0.0
     */
    public static String getIdleTime() throws FleaCoreCommonException {
        return getFleaSession().getIdleTime();
    }

    /**
     * <p> 获取FleaSession对象 </p>
     *
     * @return FleaSession对象，如果对象为空，则抛出异常
     * @throws FleaCoreCommonException Flea Core 通用异常类
     * @since 1.0.0
     */
    private static FleaSession getFleaSession() throws FleaCoreCommonException {
        FleaSession fleaSession = FleaRequestConfig.getFleaSession();
        if (ObjectUtils.isEmpty(fleaSession)) {
            // {0}不能为空，请检查
            throw new FleaCoreCommonException("ERROR-CORE-COMMON0000000001", "【FleaSession】");
        }
        return fleaSession;
    }

    /**
     * <p> 获取URL非法字符 </p>
     *
     * @return URL非法字符
     * @throws FleaCoreCommonException Flea Core 通用异常类
     * @since 1.0.0
     */
    public static String getUrlIllegalChar() throws FleaCoreCommonException {
        return getFleaUrl().getUrlIllegalChar();
    }

    /**
     * <p> 判断是否是 不需要校验的URL </p>
     *
     * @param url 待校验的URL
     * @return true: 是 false: 不是
     * @throws FleaCoreCommonException Flea Core 通用异常类
     * @since 1.0.0
     */
    public static boolean isUnCheckUrl(String url) throws FleaCoreCommonException {
        boolean isUnCheckUrl = false;
        if (getFleaUrl().contains(url, FleaUrl.UNCHECK_URL)) {
            isUnCheckUrl = true;
        }
        return isUnCheckUrl;
    }

    /**
     * <p> 判断是否是 需要校验的URL </p>
     *
     * @param url 待校验的URL
     * @return true: 是 false: 不是
     * @throws FleaCoreCommonException Flea Core 通用异常类
     * @since 1.0.0
     */
    public static boolean isCheckUrl(String url) throws FleaCoreCommonException {
        boolean isCheckUrl = false;
        if (getFleaUrl().contains(url, FleaUrl.CHECK_URL)) {
            isCheckUrl = true;
        }
        return isCheckUrl;
    }

    /**
     * <p> 判断是否是业务URL </p>
     *
     * @param url 待校验的URL
     * @return true: 是 false: 不是
     * @throws FleaCoreCommonException Flea Core 通用异常类
     * @since 1.0.0
     */
    public static boolean isBusinessUrl(String url) throws FleaCoreCommonException {
        boolean isBusinessUrl = false;
        if (getFleaUrl().containsUrlPrefix(url, FleaUrl.URL_PREFIX_BUSINESS_KEY)) {
            isBusinessUrl = true;
        }
        return isBusinessUrl;
    }

    /**
     * <p> 获取FleaUrl对象 </p>
     *
     * @return FleaUrl对象，如果对象为空，则抛出异常
     * @throws FleaCoreCommonException Flea Core 通用异常类
     * @since 1.0.0
     */
    private static FleaUrl getFleaUrl() throws FleaCoreCommonException {
        FleaUrl fleaUrl = FleaRequestConfig.getFleaUrl();
        if (ObjectUtils.isEmpty(fleaUrl)) {
            // {0}不能为空，请检查
            throw new FleaCoreCommonException("ERROR-CORE-COMMON0000000001", "【FleaUrl】");
        }
        return fleaUrl;
    }

}
