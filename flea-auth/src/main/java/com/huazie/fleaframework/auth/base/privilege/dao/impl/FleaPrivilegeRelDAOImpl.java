package com.huazie.fleaframework.auth.base.privilege.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.privilege.dao.interfaces.IFleaPrivilegeRelDAO;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.fleaframework.auth.common.AuthRelTypeEnum;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea权限关联（菜单， 操作， 元素）DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaPrivilegeRelDAO")
public class FleaPrivilegeRelDAOImpl extends FleaAuthDAOImpl<FleaPrivilegeRel> implements IFleaPrivilegeRelDAO {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaPrivilegeRelDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaPrivilegeRel> getPrivilegeRelList(Long privilegeId, String authRelType) throws CommonException {

        List<FleaPrivilegeRel> privilegeRelList = getQuery(null)
                .equal(FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_ID, privilegeId)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_TYPE, authRelType)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "PrivilegeRelList = {}", privilegeRelList);
        }

        return privilegeRelList;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public FleaPrivilegeRel getPrivilegeRelMenu(Long menuId) throws CommonException {

        List<FleaPrivilegeRel> privilegeRelList = getQuery(null)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_ID, menuId)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_TYPE, AuthRelTypeEnum.PRIVILEGE_REL_MENU.getRelType())
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        return CollectionUtils.getFirstElement(privilegeRelList, FleaPrivilegeRel.class);
    }
}