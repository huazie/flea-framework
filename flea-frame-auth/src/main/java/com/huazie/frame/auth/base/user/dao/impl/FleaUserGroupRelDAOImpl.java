package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserGroupRelDAO;
import com.huazie.frame.auth.base.user.entity.FleaUserGroupRel;
import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> Flea用户组关联（角色，角色组）DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaUserGroupRelDAO")
public class FleaUserGroupRelDAOImpl extends FleaAuthDAOImpl<FleaUserGroupRel> implements IFleaUserGroupRelDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaUserGroupRelDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaUserGroupRel> getUserGroupRelList(Long userGroupId, String authRelType) throws CommonException {

        List<FleaUserGroupRel> fleaUserGroupRelList = getQuery(null)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_USER_GROUP_ID, userGroupId)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_TYPE, authRelType)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("UserGroupRelList = {}", fleaUserGroupRelList);
        }

        return fleaUserGroupRelList;
    }
}