package com.huazie.fleaframework.auth.base.role.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.role.dao.interfaces.IFleaRoleRelDAO;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleRel;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea角色关联（角色， 权限， 权限组）DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaRoleRelDAO")
public class FleaRoleRelDAOImpl extends FleaAuthDAOImpl<FleaRoleRel> implements IFleaRoleRelDAO {

    @Override
    public List<FleaRoleRel> getRoleRelList(Long roleId, String authRelType) throws CommonException {
        return this.getQuery(null)
                .equal(FleaAuthEntityConstants.RoleEntityConstants.E_ROLE_ID, roleId)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_TYPE, authRelType)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();
    }
}