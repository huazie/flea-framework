package com.huazie.fleaframework.auth.base.user.dao.interfaces;

import com.huazie.fleaframework.auth.base.user.entity.FleaUserGroupRel;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea用户组关联（角色，角色组）DAO层接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaUserGroupRelDAO extends IAbstractFleaJPADAO<FleaUserGroupRel> {

    /**
     * 根据用户组编号、关联编号和授权关联类型，查询用户组关联数据
     *
     * @param userGroupId 用户组编号
     * @param relId       关联编号
     * @param authRelType 授权关联类型
     * @return 用户组关联数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaUserGroupRel> getUserGroupRelList(Long userGroupId, Long relId, String authRelType) throws CommonException;
}