package com.huazie.frame.auth.base.privilege.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.privilege.dao.interfaces.IFleaPrivilegeGroupRelDAO;
import com.huazie.frame.auth.base.privilege.entity.FleaPrivilegeGroupRel;
import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> Flea权限组关联（权限）DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaPrivilegeGroupRelDAO")
public class FleaPrivilegeGroupRelDAOImpl extends FleaAuthDAOImpl<FleaPrivilegeGroupRel> implements IFleaPrivilegeGroupRelDAO {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaPrivilegeGroupRelDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaPrivilegeGroupRel> getPrivilegeGroupRelList(Long privilegeGroupId, String authRelType) throws CommonException {

        List<FleaPrivilegeGroupRel> privilegeGroupRelList = getQuery(null)
                .equal(FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_GROUP_ID, privilegeGroupId)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_TYPE, authRelType)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "PrivilegeGroupRelList = {}", privilegeGroupRelList);
        }

        return privilegeGroupRelList;
    }
}