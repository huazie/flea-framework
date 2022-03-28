package com.huazie.fleaframework.auth.common.service.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaAccount;
import com.huazie.fleaframework.auth.common.pojo.user.login.FleaUserLoginPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.register.FleaUserRegisterPOJO;
import com.huazie.fleaframework.common.IFleaUser;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.object.FleaObjectFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Flea用户管理服务接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUserModuleSV {

    /**
     * 初始化用户信息
     *
     * @param accountId       账户编号
     * @param systemAccountId 系统账户编号
     * @param otherAttrs      其他属性
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    void initUserInfo(Long accountId, Long systemAccountId, Map<String, Object> otherAttrs, FleaObjectFactory<IFleaUser> fleaObjectFactory) throws CommonException;

    /**
     * 用户登录验证
     *
     * @param fleaUserLoginPOJO Flea用户登录信息
     * @return 账户信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaAccount login(FleaUserLoginPOJO fleaUserLoginPOJO) throws CommonException;

    /**
     * 用户注册
     *
     * @param fleaUserRegisterPOJO 用户注册信息
     * @return 账户信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaAccount register(FleaUserRegisterPOJO fleaUserRegisterPOJO) throws CommonException;

    /**
     * 保存用户登录日志
     *
     * @param accountId 账户编号
     * @param request   HTTP请求对象
     * @since 1.0.0
     */
    void saveLoginLog(Long accountId, HttpServletRequest request);

    /**
     * 保存用户退出日志
     *
     * @param accountId 账户编号
     * @since 1.0.0
     */
    void saveQuitLog(Long accountId);
}
