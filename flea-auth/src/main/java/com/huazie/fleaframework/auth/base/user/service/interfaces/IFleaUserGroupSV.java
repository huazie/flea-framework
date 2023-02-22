package com.huazie.fleaframework.auth.base.user.service.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaUserGroup;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserGroupPOJO;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * Flea用户组SV层接口定义
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaUserGroupSV extends IAbstractFleaJPASV<FleaUserGroup> {

    /**
     * 根据用户组编号，查询在用状态的用户组数据
     *
     * @param userGroupId 用户组编号
     * @return 在用的用户组数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaUserGroup queryUserGroupInUse(Long userGroupId) throws CommonException;

    /**
     * 保存Flea用户组
     *
     * @param fleaUserGroupPOJO Flea用户组POJO对象
     * @return Flea用户组数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaUserGroup saveUserGroup(FleaUserGroupPOJO fleaUserGroupPOJO) throws CommonException;

}