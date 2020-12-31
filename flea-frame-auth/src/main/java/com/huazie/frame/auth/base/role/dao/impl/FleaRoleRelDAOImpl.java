package com.huazie.frame.auth.base.role.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.role.dao.interfaces.IFleaRoleRelDAO;
import com.huazie.frame.auth.base.role.entity.FleaRoleRel;
import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> Flea角色关联（角色， 权限， 权限组）DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaRoleRelDAO")
public class FleaRoleRelDAOImpl extends FleaAuthDAOImpl<FleaRoleRel> implements IFleaRoleRelDAO {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaRoleRelDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaRoleRel> getRoleRelList(Long roleId, String authRelType) throws CommonException {

        List<FleaRoleRel> fleaRoleRelList = getQuery(null)
                .equal(FleaAuthEntityConstants.RoleEntityConstants.E_ROLE_ID, roleId)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_TYPE, authRelType)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "RoleRelList = {}", fleaRoleRelList);
        }

        return fleaRoleRelList;
    }
}