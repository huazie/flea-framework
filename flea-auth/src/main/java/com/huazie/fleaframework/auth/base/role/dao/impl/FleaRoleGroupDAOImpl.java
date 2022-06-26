package com.huazie.fleaframework.auth.base.role.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.role.dao.interfaces.IFleaRoleGroupDAO;
import com.huazie.fleaframework.auth.base.role.entity.FleaRoleGroup;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants.RoleEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea角色组DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaRoleGroupDAO")
public class FleaRoleGroupDAOImpl extends FleaAuthDAOImpl<FleaRoleGroup> implements IFleaRoleGroupDAO {

    @Override
    public FleaRoleGroup queryRoleGroupInUse(Long roleGroupId) throws CommonException {
        List<FleaRoleGroup> fleaRoleGroupList = this.getQuery(null)
                .equal(RoleEntityConstants.E_ROLE_GROUP_ID, roleGroupId)
                .equal(RoleEntityConstants.E_ROLE_GROUP_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        return CollectionUtils.getFirstElement(fleaRoleGroupList, FleaRoleGroup.class);
    }

    @Override
    public List<FleaRoleGroup> queryRoleGroupsInUse4Page(String roleGroupName, int pageNum, int pageCount) throws CommonException {
        return this.getJPAQuery(null, roleGroupName).getResultList4Page(pageNum, pageCount);
    }

    @Override
    public long queryRoleGroupsInUseCount(String roleGroupName) throws CommonException {
        return this.getJPAQuery(Long.class, roleGroupName).count().getSingleResult();
    }

    /**
     * 获取Flea JPA查询对象
     *
     * @return Flea JPA查询对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaJPAQuery getJPAQuery(Class result, String roleGroupName) throws CommonException {
        return this.getQuery(result)
                .like(RoleEntityConstants.E_ROLE_GROUP_NAME, roleGroupName)
                .equal(RoleEntityConstants.E_ROLE_GROUP_STATE, EntityStateEnum.IN_USE.getState());
    }
}