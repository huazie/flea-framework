package com.huazie.fleaframework.jersey.server.filter.impl;

import com.huazie.fleaframework.auth.cache.bean.FleaAuthCache;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserModuleData;
import com.huazie.fleaframework.auth.util.FleaAuthCheck;
import com.huazie.fleaframework.auth.util.FleaAuthManager;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.jersey.common.FleaJerseyConfig;
import com.huazie.fleaframework.jersey.common.FleaUserImplObjectFactory;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyRequest;
import com.huazie.fleaframework.jersey.common.data.FleaJerseyResponse;
import com.huazie.fleaframework.jersey.common.data.RequestPublicData;
import com.huazie.fleaframework.jersey.server.filter.IFleaJerseyFilter;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 授权校验过滤器，主要用于系统账户授权校验、操作账户授权校验、
 * 系统账户的资源授权校验 和 操作账户的资源授权校验。
 *
 * <p> 上述授权校验如果出现异常，直接抛出异常，由异常过滤器处理并返回；
 * 如果授权校验通过，则使用Flea授权管理器，初始化操作用户相关的信息。
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class AuthCheckFilter implements IFleaJerseyFilter {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(AuthCheckFilter.class);

    @Override
    public void doFilter(FleaJerseyRequest request, FleaJerseyResponse response) throws CommonException {
        Object obj = new Object() {};
        LOGGER.debug1(obj, "Auth Check, Start");

        RequestPublicData requestPublicData = request.getRequestData().getPublicData();
        // 系统账户编号
        String systemAccountId = requestPublicData.getSystemAccountId();
        // 操作账户编号
        String accountId = requestPublicData.getAccountId();

        // 获取Web应用上下文对象
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        // 获取Flea授权缓存对象
        FleaAuthCache fleaAuthCache = webApplicationContext.getBean(FleaAuthCache.class);

        // #1. 系统账户授权校验
        // 获取系统用户模块信息
        Long sysId = Long.valueOf(systemAccountId);
        FleaUserModuleData systemUser = fleaAuthCache.getFleaUserModuleData(Long.valueOf(systemAccountId));
        // 校验系统用户模块信息
        FleaAuthCheck.checkFleaUserModuleData(systemUser, systemAccountId);

        // #2. 操作账户授权校验
        // 获取操作用户模块信息
        Long aId = Long.valueOf(accountId);
        FleaUserModuleData operationUser = fleaAuthCache.getFleaUserModuleData(Long.valueOf(accountId));
        // 校验操作用户模块信息
        FleaAuthCheck.checkFleaUserModuleData(operationUser, accountId);

        // #3. 资源授权校验
        // 资源编码
        String resourceCode = requestPublicData.getResourceCode();
        // Flea Jersey接口服务提供方的系统编号
        Long resServiceSysId = FleaJerseyConfig.getSystemAccountId(Long.class);
        // 校验【resServiceSysId】系统下的资源是否赋权给了调用资源的系统账户【sysId】
        boolean checkFlag1 = fleaAuthCache.checkResourceAuth(sysId, resServiceSysId, resourceCode);
        FleaAuthCheck.checkResourceAuth(checkFlag1, resServiceSysId, sysId, resourceCode);

        // 校验【resServiceSysId】系统下的资源是否赋权给了调用资源的操作账户【aId】
        boolean checkFlag2 = fleaAuthCache.checkResourceAuth(aId, resServiceSysId, resourceCode);
        FleaAuthCheck.checkResourceAuth(checkFlag2, resServiceSysId, aId, resourceCode);

        // 初始化用户信息
        FleaAuthManager.initUserInfo(aId, operationUser, sysId, systemUser, null, new FleaUserImplObjectFactory());

        LOGGER.debug1(obj,"Auth Check, End");
    }

}
