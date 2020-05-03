package com.huazie.frame.core.filter.task.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.core.filter.task.IFilterTask;
import com.huazie.frame.core.filter.taskchain.IFilterTaskChain;
import com.huazie.frame.core.request.FleaRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p> SESSION信息校验过滤器任务 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class SessionCheckFilterTask implements IFilterTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionCheckFilterTask.class);

    @Override
    public void doFilterTask(ServletRequest servletRequest, ServletResponse servletResponse, IFilterTaskChain filterTaskChain) throws CommonException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SessionCheckFilterTask##doFilterTask(ServletRequest, ServletResponse, IFilterTaskChain) Start");
        }

        // 获取用户SESSION信息键
        String userSessionKey = FleaRequestUtil.getUserSessionKey();
        if (ObjectUtils.isEmpty(userSessionKey)) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("SessionCheckFilterTask##doFilterTask(ServletRequest, ServletResponse, IFilterTaskChain) <user-session-key> is not configured or is Empty");
            }
            filterTaskChain.doFilterTask(servletRequest, servletResponse);
            return;
        }

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (ObjectUtils.isNotEmpty(request)) {
            String uri = request.getRequestURI();
            HttpSession httpSession = request.getSession();
            // 如果是业务请求并且用户没有登录(或登录失效)
            if (FleaRequestUtil.isBusinessUrl(uri) && isNotLogin(httpSession, userSessionKey)) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("UrlCheckFilterTask##doFilterTask(ServletRequest, ServletResponse, IFilterTaskChain) Business Request And User Session Has Expired, Redirect to Login Page");
                }
                // 重定向到登录页面
                FleaRequestUtil.sendRedirectToLoginPage(servletRequest, servletResponse);
                return;
            }
        }

        filterTaskChain.doFilterTask(servletRequest, servletResponse);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SessionCheckFilterTask##doFilterTask(ServletRequest, ServletResponse, IFilterTaskChain) End");
        }
    }

    /**
     * <p> 根据Session判断用户是否已经登录 </p>
     *
     * @param session        HttpSession对象
     * @param userSessionKey 用户SESSION信息键
     * @return true：未登录 , false: 已登录
     * @since 1.0.0
     */
    private boolean isNotLogin(HttpSession session, String userSessionKey) {
        boolean isNotLogin = false;
        Object sessionObj = session.getAttribute(userSessionKey);

        // TODO session空闲时间

        if (ObjectUtils.isEmpty(sessionObj)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("SessionCheckFilterTask##isNotLogin(HttpSession, String) Not Login");
            }
            isNotLogin = true;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SessionCheckFilterTask##isNotLogin(HttpSession, String) Login");
        }
        return isNotLogin;
    }
}
