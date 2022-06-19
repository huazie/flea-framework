package com.huazie.fleaframework.auth.common.service.interfaces;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserModuleData;
import com.huazie.fleaframework.common.exception.CommonException;

import java.util.List;

/**
 * Flea 授权服务接口类，对外提供可缓存的授权数据查询API。
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaAuthSV {

    /**
     * 根据账户编号获取用户模块数据，包含用户，账户，
     * 用户扩展属性，账户扩展属性，用户实名信息。
     *
     * @param accountId 账户编号
     * @return 用户模块数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaUserModuleData getFleaUserModuleData(Long accountId) throws CommonException;

    /**
     * 获取指定用户下的角色编号集
     *
     * @param userId 用户编号
     * @return 角色编号集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<Long> getUserRoles(Long userId) throws CommonException;

    /**
     * 获取指定用户下的权限编号集
     *
     * @param userId 用户编号
     * @return 权限编号集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<Long> getUserPrivileges(Long userId) throws CommonException;

    /**
     * 获取指定操作账户可以访问的指定系统账户下的所有的菜单。
     *
     * @param accountId       操作账户编号
     * @param systemAccountId 系统帐户编号
     * @return 所有可以访问的菜单
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenu> queryAllAccessibleMenus(Long accountId, Long systemAccountId) throws CommonException;

    /**
     * 校验资源授权，如果允许指定操作账号，调用指定系统账户下的资源，
     * 则校验通过，返回true；否则校验失败，返回false。
     *
     * @param accountId       账户编号
     * @param systemAccountId 系统账户编号
     * @param resourceCode    资源编码
     * @return true：允许调用该资源  false：不允许调用该资源
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    boolean checkResourceAuth(Long accountId, Long systemAccountId, String resourceCode) throws CommonException;

}
