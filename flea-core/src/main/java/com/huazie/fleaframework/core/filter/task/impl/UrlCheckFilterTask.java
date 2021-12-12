package com.huazie.fleaframework.core.filter.task.impl;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ExceptionUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.PatternMatcherUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.core.filter.task.FleaFilterTaskException;
import com.huazie.fleaframework.core.filter.task.IFilterTask;
import com.huazie.fleaframework.core.filter.taskchain.IFilterTaskChain;
import com.huazie.fleaframework.core.request.FleaRequestContext;
import com.huazie.fleaframework.core.request.FleaRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * <p> URL校验过滤器任务 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class UrlCheckFilterTask implements IFilterTask {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(UrlCheckFilterTask.class);

    private static final String DEFAULT_URL_ILLEGAL_CHAR = "<|>|alert|document.cookie|href|script|select|insert|update|delete|truncate|exec|drop";

    @Override
    public void doFilterTask(FleaRequestContext fleaRequestContext, IFilterTaskChain filterTaskChain) throws CommonException {
        Object obj = null;
        if (LOGGER.isDebugEnabled()) {
            obj = new Object() {};
            LOGGER.debug1(obj, "Start");
        }

        String urlIllegalChar = FleaRequestUtil.getUrlIllegalChar();
        if (StringUtils.isBlank(urlIllegalChar)) {
            urlIllegalChar = DEFAULT_URL_ILLEGAL_CHAR;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "URL_ILLEGAL_CHAR = {}", urlIllegalChar);
        }

        HttpServletRequest request = (HttpServletRequest) fleaRequestContext.getServletRequest();
        if (ObjectUtils.isNotEmpty(request)) {
            String queryString = request.getQueryString();
            if (StringUtils.isNotBlank(queryString) && PatternMatcherUtils.matches(urlIllegalChar, queryString, Pattern.CASE_INSENSITIVE)) {
                // 检测到浏览器请求地址栏中存在非法的字符，已限制访问！！！
                ExceptionUtils.throwCommonException(FleaFilterTaskException.class, "ERROR-CORE-FILTER0000000001");
            }

            String uri = request.getRequestURI();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug1(obj, "URI = {}", uri);
            }

            // 不需校验的URL，直接跳过
            if (FleaRequestUtil.isUnCheckUrl(uri)) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug1(obj, "UnCheck URL");
                }
                return;
            }

            // 需要校验的URL【默认重定向到登录页面】
            if (FleaRequestUtil.isCheckUrl(uri)) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug1(obj, "Check URL, Redirect to Login Page");
                }
                // 重定向到登录页面
                FleaRequestUtil.sendRedirectToLoginPage(fleaRequestContext);
                return;
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(obj, "End");
        }

        // 执行下一条过滤器任务
        filterTaskChain.doFilterTask(fleaRequestContext);
    }
}
