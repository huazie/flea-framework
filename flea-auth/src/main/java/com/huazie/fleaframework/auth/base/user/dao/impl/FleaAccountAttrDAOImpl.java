package com.huazie.fleaframework.auth.base.user.dao.impl;

import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaAccountAttrDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaAccountAttr;
import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
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
 * <p> Flea账户属性DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaAccountAttrDAO")
@SuppressWarnings(value = "unchecked")
public class FleaAccountAttrDAOImpl extends FleaAuthDAOImpl<FleaAccountAttr> implements IFleaAccountAttrDAO {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaAccountAttrDAOImpl.class);

    @Override
    public List<FleaAccountAttr> queryValidAccountAttrs(Long accountId) throws CommonException {

        Date currentDate = DateUtils.getCurrentTime();

        List<FleaAccountAttr> fleaAccountList = getQuery(null)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_ID, accountId)
                .equal(FleaAuthEntityConstants.E_STATE, UserStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate)
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "FleaAccountList = {}", fleaAccountList);
        }

        return fleaAccountList;
    }
}