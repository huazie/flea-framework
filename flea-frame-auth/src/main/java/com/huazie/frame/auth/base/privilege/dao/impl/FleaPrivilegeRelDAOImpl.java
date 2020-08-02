package com.huazie.frame.auth.base.privilege.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.privilege.dao.interfaces.IFleaPrivilegeRelDAO;
import com.huazie.frame.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> Flea权限关联（菜单， 操作， 元素）DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaPrivilegeRelDAO")
public class FleaPrivilegeRelDAOImpl extends FleaAuthDAOImpl<FleaPrivilegeRel> implements IFleaPrivilegeRelDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaPrivilegeRelDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaPrivilegeRel> getPrivilegeRelList(Long privilegeId, String authRelType) throws CommonException {

        List<FleaPrivilegeRel> privilegeRelList = getQuery(null)
                .equal(FleaAuthEntityConstants.PriviilegeEntityConstants.E_PRIVILEGE_ID, privilegeId)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_TYPE, authRelType)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaPrivilegeRelDAOImpl##getPrivilegeRelList(Long, String) PrivilegeRelList = {}", privilegeRelList);
        }

        return privilegeRelList;
    }
}