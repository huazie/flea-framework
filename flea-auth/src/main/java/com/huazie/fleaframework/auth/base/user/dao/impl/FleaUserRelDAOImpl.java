package com.huazie.fleaframework.auth.base.user.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaUserRelDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserRel;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea用户关联（角色，角色组）DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaUserRelDAO")
public class FleaUserRelDAOImpl extends FleaAuthDAOImpl<FleaUserRel> implements IFleaUserRelDAO {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaUserRelDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaUserRel> getUserRelList(Long userId, String authRelType) throws CommonException {

        List<FleaUserRel> fleaUserRelList = getQuery(null)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_USER_ID, userId)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_TYPE, authRelType)
                .equal(FleaAuthEntityConstants.FleaRelEntityConstants.E_REL_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "UserRelList = {}", fleaUserRelList);
        }

        return fleaUserRelList;
    }

}