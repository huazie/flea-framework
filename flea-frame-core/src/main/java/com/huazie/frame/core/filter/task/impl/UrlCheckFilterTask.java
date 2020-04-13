package com.huazie.frame.core.filter.task.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.PatternMatcherUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.core.filter.task.FleaFilterTaskException;
import com.huazie.frame.core.filter.task.IFilterTask;
import com.huazie.frame.core.filter.taskchain.IFilterTaskChain;
import com.huazie.frame.core.request.config.FleaRequestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
    public void doFilterTask(ServletRequest servletRequest, ServletResponse servletResponse, IFilterTaskChain filterTaskChain) throws CommonException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("UrlCheckFilterTask##doFilterTask(ServletRequest, ServletResponse) Start");
        }

        String illegalChar = FleaRequestConfig.getFleaUrl().getUrlIllegalChar();
        if (StringUtils.isBlank(illegalChar)) {
            illegalChar = URL_ILLEGAL_CHAR;
        }

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String queryString = request.getQueryString();

        if (PatternMatcherUtils.matches(illegalChar, queryString, Pattern.CASE_INSENSITIVE)) {
            // 检测到浏览器请求地址栏中存在非法的字符，已限制访问！！！
            throw new FleaFilterTaskException("ERROR-CORE-FILTER0000000001");
        }

        // 执行下一条过滤器任务
        filterTaskChain.doFilterTask(servletRequest, servletResponse);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("UrlCheckFilterTask##doFilterTask(ServletRequest, ServletResponse) End");
        }
    }
}
