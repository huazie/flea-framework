package com.huazie.fleaframework.auth.base.user.dao.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaUserGroup;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

/**
 * Flea用户组DAO层接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaUserGroupDAO extends IAbstractFleaJPADAO<FleaUserGroup> {

    /**
     * 根据用户组编号，查询在用状态的用户组数据
     *
     * @param userGroupId 用户组编号
     * @return 在用的用户组数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaUserGroup queryUserGroupInUse(Long userGroupId) throws CommonException;

}