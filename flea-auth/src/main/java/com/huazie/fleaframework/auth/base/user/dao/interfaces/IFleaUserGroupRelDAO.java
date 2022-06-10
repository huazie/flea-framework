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
     * 获取指定用户组编号【userGroupId】关联的指定授权关联类型【authRelType】的用户组关联信息
     *
     * @param userGroupId 用户组编号
     * @param authRelType 授权关联类型
     * @return 用户组关联信息
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaUserGroupRel> getUserGroupRelList(Long userGroupId, String authRelType) throws CommonException;
}