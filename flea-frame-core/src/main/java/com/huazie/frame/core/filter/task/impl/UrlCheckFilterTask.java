package com.huazie.frame.core.filter.task.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.PatternMatcherUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.core.filter.task.FleaFilterTaskException;
import com.huazie.frame.core.filter.task.IFilterTask;
import com.huazie.frame.core.filter.taskchain.IFilterTaskChain;
import com.huazie.frame.core.request.FleaRequestContext;
import com.huazie.frame.core.request.FleaRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlCheckFilterTask.class);

    private static final String URL_ILLEGAL_CHAR = "<|>|alert|document.cookie|href|script|select|insert|update|delete|truncate|exec|drop";

    @Override
    public void doFilterTask(FleaRequestContext fleaRequestContext, IFilterTaskChain filterTaskChain) throws CommonException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("UrlCheckFilterTask##doFilterTask(ServletRequest, ServletResponse, IFilterTaskChain) Start");
        }

        String urlIllegalChar = FleaRequestUtil.getUrlIllegalChar();
        if (StringUtils.isBlank(urlIllegalChar)) {
            urlIllegalChar = URL_ILLEGAL_CHAR;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("UrlCheckFilterTask##doFilterTask(ServletRequest, ServletResponse, IFilterTaskChain) URL_ILLEGAL_CHAR = {}", urlIllegalChar);
        }

        HttpServletRequest request = (HttpServletRequest) fleaRequestContext.getServletRequest();
        if (ObjectUtils.isNotEmpty(request)) {
            String queryString = request.getQueryString();
            if (StringUtils.isNotBlank(queryString) && PatternMatcherUtils.matches(urlIllegalChar, queryString, Pattern.CASE_INSENSITIVE)) {
                // 检测到浏览器请求地址栏中存在非法的字符，已限制访问！！！
                throw new FleaFilterTaskException("ERROR-CORE-FILTER0000000001");
            }

            String uri = request.getRequestURI();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("UrlCheckFilterTask##doFilterTask(ServletRequest, ServletResponse, IFilterTaskChain) URI = {}", uri);
            }

            // 不需校验的URL，直接跳过
            if (FleaRequestUtil.isUnCheckUrl(uri)) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("UrlCheckFilterTask##doFilterTask(ServletRequest, ServletResponse, IFilterTaskChain) UnCheck URL");
                }
                return;
            }

            // 需要校验的URL【默认重定向到登录页面】
            if (FleaRequestUtil.isCheckUrl(uri)) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("UrlCheckFilterTask##doFilterTask(ServletRequest, ServletResponse, IFilterTaskChain) Check URL, Redirect to Login Page");
                }
                // 重定向到登录页面
                FleaRequestUtil.sendRedirectToLoginPage(fleaRequestContext);
                return;
            }
        }

        // 执行下一条过滤器任务
        filterTaskChain.doFilterTask(fleaRequestContext);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("UrlCheckFilterTask##doFilterTask(ServletRequest, ServletResponse, IFilterTaskChain) End");
        }
    }
}
