package com.huazie.fleaframework.auth.base.privilege.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.privilege.dao.interfaces.IFleaPrivilegeGroupDAO;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilegeGroup;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants.FunctionEntityConstants;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants.PrivilegeEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea权限组DAO层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Repository("fleaPrivilegeGroupDAO")
public class FleaPrivilegeGroupDAOImpl extends FleaAuthDAOImpl<FleaPrivilegeGroup> implements IFleaPrivilegeGroupDAO {

    @Override
    public FleaPrivilegeGroup queryPrivilegeGroupInUse(Long privilegeGroupId) throws CommonException {
        List<FleaPrivilegeGroup> fleaPrivilegeGroupList = this.getQuery(null)
                .equal(PrivilegeEntityConstants.E_PRIVILEGE_GROUP_ID, privilegeGroupId)
                .equal(PrivilegeEntityConstants.E_PRIVILEGE_GROUP_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        return CollectionUtils.getFirstElement(fleaPrivilegeGroupList, FleaPrivilegeGroup.class);
    }

    @Override
    public List<FleaPrivilegeGroup> queryPrivilegeGroupsInUse4Page(String privilegeGroupName, Integer isMain, String functionType, int pageNum, int pageCount) throws CommonException {
        return this.getJPAQuery(null, privilegeGroupName, isMain, functionType).getResultList4Page(pageNum, pageCount);
    }

    @Override
    public long queryPrivilegeGroupsInUseCount(String privilegeGroupName, Integer isMain, String functionType) throws CommonException {
        return this.getJPAQuery(Long.class, privilegeGroupName, isMain, functionType).count().getSingleResult();
    }

    /**
     * 获取Flea JPA查询对象
     *
     * @return Flea JPA查询对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaJPAQuery getJPAQuery(Class result, String privilegeGroupName, Integer isMain, String functionType) throws CommonException {
        return this.getQuery(result)
                .like(PrivilegeEntityConstants.E_PRIVILEGE_GROUP_NAME, privilegeGroupName)
                .equal(PrivilegeEntityConstants.E_PRIVILEGE_GROUP_STATE, EntityStateEnum.IN_USE.getState())
                .equal(PrivilegeEntityConstants.E_IS_MAIN, isMain)
                .equal(FunctionEntityConstants.E_FUNCTION_TYPE, functionType);
    }
}