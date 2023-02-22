package com.huazie.fleaframework.auth.base.role.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.role.dao.interfaces.IFleaRoleDAO;
import com.huazie.fleaframework.auth.base.role.entity.FleaRole;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea角色DAO层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Repository("fleaRoleDAO")
public class FleaRoleDAOImpl extends FleaAuthDAOImpl<FleaRole> implements IFleaRoleDAO {

    @Override
    public FleaRole queryRoleInUse(Long roleId) throws CommonException {
        List<FleaRole> fleaRoleList = this.getQuery(null)
                .equal(FleaAuthEntityConstants.RoleEntityConstants.E_ROLE_ID, roleId)
                .equal(FleaAuthEntityConstants.RoleEntityConstants.E_ROLE_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        return CollectionUtils.getFirstElement(fleaRoleList, FleaRole.class);
    }

    @Override
    public List<FleaRole> queryRolesInUse4Page(String roleName, Long groupId, int pageNum, int pageCount) throws CommonException {
        return getJPAQuery(null, roleName, groupId).getResultList4Page(pageNum, pageCount);
    }

    @Override
    public long queryRolesInUseCount(String roleName, Long groupId) throws CommonException {
        return getJPAQuery(Long.class, roleName, groupId).count().getSingleResult();
    }

    /**
     * 获取Flea JPA查询对象
     *
     * @return Flea JPA查询对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaJPAQuery getJPAQuery(Class result, String roleName, Long groupId) throws CommonException {
        return this.getQuery(result)
                .like(FleaAuthEntityConstants.RoleEntityConstants.E_ROLE_NAME, roleName)
                .equal(FleaAuthEntityConstants.E_GROUP_ID, groupId)
                .equal(FleaAuthEntityConstants.RoleEntityConstants.E_ROLE_STATE, EntityStateEnum.IN_USE.getState());
    }
}