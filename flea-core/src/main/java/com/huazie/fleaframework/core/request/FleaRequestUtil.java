package com.huazie.fleaframework.core.request;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.core.common.FleaCoreCommonException;
import com.huazie.fleaframework.core.filter.taskchain.FleaFilterTaskChainManager;
import com.huazie.fleaframework.core.request.config.FleaRequestConfig;
import com.huazie.fleaframework.core.request.config.FleaSession;
import com.huazie.fleaframework.core.request.config.FleaUrl;
import com.huazie.fleaframework.core.request.config.Property;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Flea 请求工具类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaRequestUtil {

    private FleaRequestUtil() {
    }

    /**
     * 执行过滤器任务
     *
     * @param fleaRequestContext Flea请求上下文
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void doFilterTask(FleaRequestContext fleaRequestContext) throws CommonException {
        FleaFilterTaskChainManager.getManager().doFilterTask(fleaRequestContext);
    }

    /**
     * 重定向到错误页面
     *
     * @param fleaRequestContext Flea请求上下文
     * @param throwable          异常信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void sendRedirectToErrorPage(FleaRequestContext fleaRequestContext, Throwable throwable) throws CommonException {
        if (ObjectUtils.isEmpty(throwable)) {
            return;
        }
        String errorMsg = "?ERROR_MSG=" + throwable.getMessage();
        sendRedirectToOtherPage(fleaRequestContext, FleaUrl.REDIRECT_URL_ERROR_KEY, errorMsg);
    }

    /**
     * 重定向到登录页面
     *
     * @param fleaRequestContext Flea请求上下文
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void sendRedirectToLoginPage(FleaRequestContext fleaRequestContext) throws CommonException {
        sendRedirectToOtherPage(fleaRequestContext, FleaUrl.REDIRECT_URL_LOGIN_KEY, null);
    }

    /**
     * 重定向至其他页面
     *
     * @param fleaRequestContext Flea请求上下文
     * @param redirectUrlKey     重定向URL配置KEY
     * @param urlParam           URL参数
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static void sendRedirectToOtherPage(FleaRequestContext fleaRequestContext, String redirectUrlKey, String urlParam) throws CommonException {
        HttpServletRequest request = (HttpServletRequest) fleaRequestContext.getServletRequest();
        HttpServletResponse response = (HttpServletResponse) fleaRequestContext.getServletResponse();
        Property redirectUrlProp = getFleaUrl().getRedirectUrlProperty(redirectUrlKey);
        if (ObjectUtils.isNotEmpty(response) && ObjectUtils.isNotEmpty(redirectUrlProp) && StringUtils.isNotEmpty(redirectUrlProp.getValue())) {
            try {
                String redirectUrl = redirectUrlProp.getValue();
                if (StringUtils.isNotBlank(urlParam)) {
                    redirectUrl += urlParam;
                }
                // ajax请求的重定向让前台跳转处理
                if (isAjaxRequest(request)) {
                    sendRedirectToOtherPageForAjax(response, redirectUrl);
                } else { // 非ajax请求的重定向可以直接处理
                    response.sendRedirect(redirectUrl);
                }
                // 设置重定向标识，过滤器将不执行后续逻辑
                fleaRequestContext.put(FleaRequestContext.REDIRECT_FLAG, redirectUrlKey);
            } catch (IOException e) {
                // {0}
                ExceptionUtils.throwCommonException(FleaCoreCommonException.class, "ERROR-CORE-COMMON0000000000", e.getMessage());
            }
        }
    }

    /**
     * ajax 请求重定向页面
     *
     * @param response    HTTP响应对象
     * @param redirectUrl 重定向地址
     */
    private static void sendRedirectToOtherPageForAjax(HttpServletResponse response, String redirectUrl) throws IOException {
        // 设置跳转地址，是用来给前端进行跳转时获取
        response.setHeader(FleaRequestContext.REDIRECT_URL, redirectUrl);
        response.flushBuffer();
    }

    /**
     * 获取用户 SESSION 信息键
     *
     * @return 用户 SESSION 信息键
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static String getUserSessionKey() throws CommonException {
        return getFleaSession().getUserSessionKey();
    }

    /**
     * 获取用户 SESSION 空闲保持时间（单位：s）
     *
     * @return 用户 SESSION 空闲保持时间
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static String getIdleTime() throws CommonException {
        return getFleaSession().getIdleTime();
    }

    /**
     * 获取 Flea Session 对象
     *
     * @return Flea Session 对象，如果对象为空，则抛出异常
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private static FleaSession getFleaSession() throws CommonException {
        FleaSession fleaSession = FleaRequestConfig.getFleaSession();
        // 【{0}】不能为空，请检查
        ObjectUtils.checkEmpty(fleaSession, FleaCoreCommonException.class, "ERROR-CORE-COMMON0000000001", "【FleaSession】");
        return fleaSession;
    }

    /**
     * 获取URL非法字符
     *
     * @return URL非法字符
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static String getUrlIllegalChar() throws CommonException {
        return getFleaUrl().getUrlIllegalChar();
    }

    /**
     * 判断是否是 不需要校验的URL
     *
     * @param url 待校验的URL
     * @return true: 是 false: 不是
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static boolean isUnCheckUrl(String url) throws CommonException {
        boolean isUnCheckUrl = false;
        if (getFleaUrl().contains(url, FleaUrl.UNCHECK_URL)) {
            isUnCheckUrl = true;
        }
        return isUnCheckUrl;
    }

    /**
     * 判断是否是 需要校验的URL
     *
     * @param url 待校验的URL
     * @return true: 是 false: 不是
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static boolean isCheckUrl(String url) throws CommonException {
        boolean isCheckUrl = false;
        if (getFleaUrl().contains(url, FleaUrl.CHECK_URL)) {
            isCheckUrl = true;
        }
        return isCheckUrl;
    }

    /**
     * 判断是否是 业务URL
     *
     * @param url 待校验的URL
     * @return true: 是 false: 不是
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static boolean isBusinessUrl(String url) throws CommonException {
        boolean isBusinessUrl = false;
        if (getFleaUrl().containsUrlPrefix(url, FleaUrl.URL_PREFIX_BUSINESS_KEY)) {
            isBusinessUrl = true;
        }
        return isBusinessUrl;
    }

    /**
     * 判断是否是 页面跳转URL
     *
     * @param url 待校验的URL
     * @return true: 是 false: 不是
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    public static boolean isPageUrl(String url) throws CommonException {
        boolean isPageUrl = false;
        if (getFleaUrl().containsUrlPrefix(url, FleaUrl.URL_PREFIX_PAGE_KEY)) {
            isPageUrl = true;
        }
        return isPageUrl;
    }

    /**
     * 获取 Flea Url 对象
     *
     * @return FleaUrl对象，如果对象为空，则抛出异常
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    private static FleaUrl getFleaUrl() throws CommonException {
        FleaUrl fleaUrl = FleaRequestConfig.getFleaUrl();
        // 【{0}】不能为空，请检查
        ObjectUtils.checkEmpty(fleaUrl, FleaCoreCommonException.class, "ERROR-CORE-COMMON0000000001", "【FleaUrl】");
        return fleaUrl;
    }

    /**
     * 判断是否是 ajax 请求
     *
     * @param request HTTP请求对象
     * @return true: 是  false: 不是
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        boolean isAjaxRequest = false;
        String xRequestWith = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(xRequestWith)) {
            isAjaxRequest = true;
        }
        return isAjaxRequest;
    }

}
