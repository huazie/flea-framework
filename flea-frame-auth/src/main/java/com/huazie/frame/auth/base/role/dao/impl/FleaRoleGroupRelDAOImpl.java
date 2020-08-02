package com.huazie.frame.auth.base.role.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.role.dao.interfaces.IFleaRoleGroupRelDAO;
import com.huazie.frame.auth.base.role.entity.FleaRoleGroupRel;
import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> Flea角色组关联（角色）DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaRoleGroupRelDAO")
public class FleaRoleGroupRelDAOImpl extends FleaAuthDAOImpl<FleaRoleGroupRel> implements IFleaRoleGroupRelDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaRoleGroupRelDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaRoleGroupRel> getRoleGroupRelList(Long roleGroupId, String authRelType) throws CommonException {

        List<FleaRoleGroupRel> fleaRoleGroupRelList = getQuery(null)
                .equal(FleaAuthEntityConstants.RoleEntityConstants.E_ROLE_GROUP_ID, roleGroupId)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_TYPE, authRelType)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaRoleGroupRelDAOImpl##getRoleGroupRelList(Long, String) RoleGroupRelList = {}", fleaRoleGroupRelList);
        }

        return fleaRoleGroupRelList;
    }
}