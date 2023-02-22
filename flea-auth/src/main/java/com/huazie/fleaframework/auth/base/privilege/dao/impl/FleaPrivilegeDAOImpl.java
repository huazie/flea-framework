package com.huazie.fleaframework.auth.base.privilege.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.privilege.dao.interfaces.IFleaPrivilegeDAO;
import com.huazie.fleaframework.auth.base.privilege.entity.FleaPrivilege;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea权限DAO层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Repository("fleaPrivilegeDAO")
public class FleaPrivilegeDAOImpl extends FleaAuthDAOImpl<FleaPrivilege> implements IFleaPrivilegeDAO {

    @Override
    public FleaPrivilege queryPrivilegeInUse(Long privilegeId) throws CommonException {
        List<FleaPrivilege> fleaPrivilegeList = this.getQuery(null)
                .equal(FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_ID, privilegeId)
                .equal(FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        return CollectionUtils.getFirstElement(fleaPrivilegeList, FleaPrivilege.class);
    }

    @Override
    public List<FleaPrivilege> queryPrivilegesInUse4Page(String privilegeName, Long groupId, int pageNum, int pageCount) throws CommonException {
        return this.getJPAQuery(null, privilegeName, groupId).getResultList4Page(pageNum, pageCount);
    }

    @Override
    public long queryPrivilegesInUseCount(String privilegeName, Long groupId) throws CommonException {
        return this.getJPAQuery(Long.class, privilegeName, groupId).count().getSingleResult();
    }

    /**
     * 获取Flea JPA查询对象
     *
     * @return Flea JPA查询对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaJPAQuery getJPAQuery(Class result, String privilegeName, Long groupId) throws CommonException {
        return this.getQuery(result)
                .like(FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_NAME, privilegeName)
                .equal(FleaAuthEntityConstants.E_GROUP_ID, groupId)
                .equal(FleaAuthEntityConstants.PrivilegeEntityConstants.E_PRIVILEGE_STATE, EntityStateEnum.IN_USE.getState());
    }
}