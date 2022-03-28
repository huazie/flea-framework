package com.huazie.fleaframework.auth.base.user.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaUserAttrDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaUserAttr;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.auth.common.UserStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Flea用户属性DAO层实现类
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

        List<FleaUserAttr> fleaUserAttrList = getQuery(null)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_USER_ID, userId)
                .equal(FleaAuthEntityConstants.E_STATE, UserStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate)
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "FleaUserAttrList = {}", fleaUserAttrList);
        }

        return fleaUserAttrList;
    }
}