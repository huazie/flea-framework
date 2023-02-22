package com.huazie.fleaframework.auth.base.role.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.role.dao.interfaces.IFleaRoleGroupRelDAO;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleGroupRel;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea角色组关联（角色）DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaRoleGroupRelDAO")
public class FleaRoleGroupRelDAOImpl extends FleaAuthDAOImpl<FleaRoleGroupRel> implements IFleaRoleGroupRelDAO {

    @Override
    public List<FleaRoleGroupRel> getRoleGroupRelList(Long roleGroupId, String authRelType) throws CommonException {
        return this.getQuery(null)
                .equal(FleaAuthEntityConstants.RoleEntityConstants.E_ROLE_GROUP_ID, roleGroupId)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_TYPE, authRelType)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();
    }
}