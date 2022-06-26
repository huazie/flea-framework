package com.huazie.fleaframework.auth.base.user.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaUserGroupDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserGroup;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants.UserEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea用户组DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaUserGroupDAO")
public class FleaUserGroupDAOImpl extends FleaAuthDAOImpl<FleaUserGroup> implements IFleaUserGroupDAO {

    @Override
    public FleaUserGroup queryUserGroupInUse(Long userGroupId) throws CommonException {
        List<FleaUserGroup> fleaUserGroupList = this.getQuery(null)
                .equal(UserEntityConstants.E_USER_GROUP_ID, userGroupId)
                .equal(UserEntityConstants.E_USER_GROUP_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        return CollectionUtils.getFirstElement(fleaUserGroupList, FleaUserGroup.class);
    }
}