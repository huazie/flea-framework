package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaUserAttrDAO;
import com.huazie.frame.auth.base.user.entity.FleaUserAttr;
import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.auth.common.UserStateEnum;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p> Flea用户属性DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaUserAttrDAO")
@SuppressWarnings(value = "unchecked")
public class FleaUserAttrDAOImpl extends FleaAuthDAOImpl<FleaUserAttr> implements IFleaUserAttrDAO {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaUserAttrDAOImpl.class);

    @Override
    public List<FleaUserAttr> queryValidUserAttrs(Long userId) throws CommonException {

        Date currentDate = DateUtils.getCurrentTime();

        List<FleaUserAttr> fleaUserList = getQuery(null)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_USER_ID, userId)
                .equal(FleaAuthEntityConstants.E_STATE, UserStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate)
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "FleaUserList = {}", fleaUserList);
        }

        return fleaUserList;
    }
}