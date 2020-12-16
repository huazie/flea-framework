package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserDAO;
import com.huazie.frame.auth.base.user.entity.FleaUser;
import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.auth.common.UserStateEnum;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p> Flea用户DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaUserDAO")
@SuppressWarnings(value = "unchecked")
public class FleaUserDAOImpl extends FleaAuthDAOImpl<FleaUser> implements IFleaUserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaUserDAOImpl.class);

    @Override
    public FleaUser queryValidUser(Long userId) throws CommonException {

        Date currentDate = DateUtils.getCurrentTime();

        List<FleaUser> fleaUserList = getQuery(null)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_USER_ID, userId)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_USER_STATE, UserStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate)
                .getResultList();

        FleaUser fleaUser = null;

        if (CollectionUtils.isNotEmpty(fleaUserList)) {
            fleaUser = fleaUserList.get(0);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaUser = {}", fleaUser);
        }

        return fleaUser;
    }
}