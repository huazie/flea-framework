package com.huazie.frame.core.filter.task.impl;

import com.huazie.frame.common.FleaSessionManager;
import com.huazie.frame.common.IFleaUser;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.common.util.TimeUtil;
import com.huazie.frame.core.filter.task.IFilterTask;
import com.huazie.frame.core.filter.taskchain.IFilterTaskChain;
import com.huazie.frame.core.request.FleaRequestContext;
import com.huazie.frame.core.request.FleaRequestUtil;
import com.huazie.frame.core.request.config.FleaSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public void doFilterTask(FleaRequestContext fleaRequestContext, IFilterTaskChain filterTaskChain) throws CommonException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SessionCheckFilterTask##doFilterTask(ServletRequest, ServletResponse, IFilterTaskChain) Start");
        }

        // 获取用户SESSION信息键
        String userSessionKey = FleaRequestUtil.getUserSessionKey();
        if (ObjectUtils.isEmpty(userSessionKey)) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("SessionCheckFilterTask##doFilterTask(FleaRequestContext, IFilterTaskChain) <user-session-key> is not configured or is Empty");
            }
            filterTaskChain.doFilterTask(fleaRequestContext);
            return;
        }

        HttpServletRequest request = (HttpServletRequest) fleaRequestContext.getServletRequest();
        if (ObjectUtils.isNotEmpty(request)) {
            String uri = request.getRequestURI();
            HttpSession httpSession = request.getSession();
            // 业务请求
            if (ObjectUtils.isNotEmpty(httpSession) && (FleaRequestUtil.isBusinessUrl(uri) || FleaRequestUtil.isPageUrl(uri))) {
                // 用户没有登录
                if (!isLogin(httpSession, userSessionKey)) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("UrlCheckFilterTask##doFilterTask(FleaRequestContext, IFilterTaskChain) Business Request And User Not Login, Redirect to Login Page");
                    }
                    // 重定向到登录页面
                    FleaRequestUtil.sendRedirectToLoginPage(fleaRequestContext);
                    return;
                }
                // 用户登录已失效
                if (isLoginExpired(httpSession, userSessionKey)) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("UrlCheckFilterTask##doFilterTask(FleaRequestContext, IFilterTaskChain) Business Request And User Session Has Expired, Redirect to Login Page");
                    }
                    // 重定向到登录页面
                    FleaRequestUtil.sendRedirectToLoginPage(fleaRequestContext);
                    return;
                }
            }
        }

        filterTaskChain.doFilterTask(fleaRequestContext);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("SessionCheckFilterTask##doFilterTask(FleaRequestContext, IFilterTaskChain) End");
        }
    }

    /**
     * <p> 判断用户是否登录 </p>
     *
     * @param session        HttpSession对象
     * @param userSessionKey 用户SESSION信息键
     * @return true：已登录 , false: 未登录
     * @since 1.0.0
     */
    private boolean isLogin(HttpSession session, String userSessionKey) {
        boolean isLogin = false;
        Object sessionObj = session.getAttribute(userSessionKey);
        if (ObjectUtils.isNotEmpty(sessionObj)) {
            isLogin = true;
        }
        if (LOGGER.isDebugEnabled()) {
            if (isLogin) {
                LOGGER.debug("SessionCheckFilterTask##isLogin(HttpSession, String) Login");
            } else {
                LOGGER.debug("SessionCheckFilterTask##isLogin(HttpSession, String) Not Login");
            }
        }
        return isLogin;
    }

    /**
     * <p> 判断用户Session是否已经失效 </p>
     *
     * @param session HttpSession对象
     * @return true：已失效 , false: 未失效
     */
    private boolean isLoginExpired(HttpSession session, String userSessionKey) throws CommonException {
        boolean isExpired = false;
        // 获取当前时间
        long currentTime = TimeUtil.getSystemCurrentTimeForLong();
        // 上一次激活时间
        String oldActiveTimeStr = StringUtils.valueOf(session.getAttribute(FleaSession.SESSION_ACTIVE_TIME));
        if (StringUtils.isNotBlank(oldActiveTimeStr)) {
            // Session上一次激活时间
            Long oldActiveTime = Long.valueOf(oldActiveTimeStr);
            // 获取Session空闲时间配置（单位：秒）
            Long idleTime = Long.valueOf(FleaRequestUtil.getIdleTime());
            // Session实际空闲时长
            long realIdleTime = currentTime - oldActiveTime;
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("*********************************************************************************");
                LOGGER.debug("SessionCheckFilterTask##isLoginExpired(HttpSession, String) IdleTime     = {}s", idleTime);
                LOGGER.debug("SessionCheckFilterTask##isLoginExpired(HttpSession, String) RealIdleTime = {}s", realIdleTime / 1000);
                LOGGER.debug("*********************************************************************************");
            }

            if (currentTime - oldActiveTime > idleTime * 1000) {
                // 用户Session已经失效
                isExpired = true;
                // 去除上一次激活时间属性
                session.removeAttribute(FleaSession.SESSION_ACTIVE_TIME);
            } else {
                // 激活用户Session信息
                activeUserSession(session, userSessionKey, currentTime);
            }
        } else {
            // 激活用户Session信息
            activeUserSession(session, userSessionKey, currentTime);
        }

        return isExpired;
    }

    /**
     * <p> 激活用户Session信息 </p>
     *
     * @param session HttpSession对象
     * @param userSessionKey 用户Session信息键
     * @param activeTime 用户Session激活时间
     * @since 1.0.0
     */
    private void activeUserSession(HttpSession session, String userSessionKey, long activeTime) {
        // 重新设置Session激活时间为当前时间
        session.setAttribute(FleaSession.SESSION_ACTIVE_TIME, activeTime);
        Object sessionObj = session.getAttribute(userSessionKey);
        if (ObjectUtils.isNotEmpty(sessionObj)) {
            if (sessionObj instanceof IFleaUser) {
                FleaSessionManager.setUserInfo((IFleaUser) sessionObj);
            }
        }
    }
}
