package com.huazie.fleaframework.auth.base.privilege.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.privilege.dao.interfaces.IFleaPrivilegeRelDAO;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeRel;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea权限关联功能【菜单、操作、元素和资源】DAO层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Repository("fleaPrivilegeRelDAO")
public class FleaPrivilegeRelDAOImpl extends FleaAuthDAOImpl<FleaPrivilegeRel> implements IFleaPrivilegeRelDAO {

    @Override
    public List<FleaPrivilegeRel> getPrivilegeRelList(Long privilegeId, String authRelType) throws CommonException {
        return this.getJPAQuery(authRelType)
                .equal(FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_ID, privilegeId)
                .getResultList();
    }

    @Override
    public List<FleaPrivilegeRel> getPrivilegeRelList(List<Long> privilegeIdList, String authRelType) throws CommonException {
        return this.getJPAQuery(authRelType)
                .in(FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_ID, privilegeIdList)
                .getResultList();
    }

    @Override
    public FleaPrivilegeRel getPrivilegeRelFunction(Long functionId, String authRelType) throws CommonException {
        List<FleaPrivilegeRel> privilegeRelList = this.getJPAQuery(authRelType)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_ID, functionId)
                .getResultList();

        return CollectionUtils.getFirstElement(privilegeRelList, FleaPrivilegeRel.class);
    }

    /**
     * 获取Flea JPA查询对象
     *
     * @param authRelType 权限关联类型
     * @return Flea JPA查询对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaJPAQuery getJPAQuery(String authRelType) throws CommonException {
        return this.getQuery(null)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_TYPE, authRelType)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_STATE, EntityStateEnum.IN_USE.getState());
    }
}