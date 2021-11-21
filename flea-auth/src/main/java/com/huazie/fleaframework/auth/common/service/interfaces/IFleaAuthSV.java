package com.huazie.fleaframework.auth.common.service.interfaces;

import com.huazie.fleaframework.auth.base.function.entity.FleaMenu;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserModuleData;
import com.huazie.fleaframework.common.exception.CommonException;

import java.util.List;

/**
 * <p> Flea 授权服务接口类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaAuthSV {

    /**
     * <p> 获取所有可以访问的菜单 </p>
     *
     * @param accountId    操作账户编号
     * @param systemAccountId 系统帐户编号
     * @return 所有可以访问的菜单
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaMenu> queryAllAccessibleMenus(Long accountId, Long systemAccountId) throws CommonException;

    /**
     * <p> 根据账户编号获取用户模块数据 </p>
     *
     * @param accountId 账户编号
     * @return 用户模块数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaUserModuleData getFleaUserModuleData(Long accountId) throws CommonException;
}
