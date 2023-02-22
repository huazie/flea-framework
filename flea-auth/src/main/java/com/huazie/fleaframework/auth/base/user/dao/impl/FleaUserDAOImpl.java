package com.huazie.fleaframework.auth.base.user.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaUserDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaUser;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.auth.common.UserStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Flea用户DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaUserDAO")
public class FleaUserDAOImpl extends FleaAuthDAOImpl<FleaUser> implements IFleaUserDAO {

    @Override
    public FleaUser queryValidUser(Long userId) throws CommonException {
        Date currentDate = DateUtils.getCurrentTime();

        List<FleaUser> fleaUserList = this.getQuery(null)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_USER_ID, userId)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_USER_STATE, UserStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate)
                .getResultList();

        return CollectionUtils.getFirstElement(fleaUserList, FleaUser.class);
    }
}