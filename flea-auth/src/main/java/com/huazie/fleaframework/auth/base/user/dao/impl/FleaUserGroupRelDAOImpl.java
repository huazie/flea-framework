package com.huazie.fleaframework.auth.base.user.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaUserGroupRelDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserGroupRel;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea用户组关联（角色，角色组）DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaUserGroupRelDAO")
public class FleaUserGroupRelDAOImpl extends FleaAuthDAOImpl<FleaUserGroupRel> implements IFleaUserGroupRelDAO {

    @Override
    public List<FleaUserGroupRel> getUserGroupRelList(Long userGroupId, Long relId, String authRelType) throws CommonException {
        return this.getQuery(null)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_USER_GROUP_ID, userGroupId)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_ID, relId)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_TYPE, authRelType)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();
    }
}