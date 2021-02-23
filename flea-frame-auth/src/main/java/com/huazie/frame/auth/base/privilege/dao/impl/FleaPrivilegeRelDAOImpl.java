package com.huazie.frame.auth.base.privilege.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.privilege.dao.interfaces.IFleaPrivilegeRelDAO;
import com.huazie.frame.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.frame.auth.common.AuthRelTypeEnum;
import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.CollectionUtils;
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

        FleaPrivilegeRel fleaPrivilegeRel = null;
        if (CollectionUtils.isNotEmpty(privilegeRelList)) {
            fleaPrivilegeRel = privilegeRelList.get(0);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("PrivilegeRelMenu = {}", fleaPrivilegeRel);
        }

        return fleaPrivilegeRel;
    }
}