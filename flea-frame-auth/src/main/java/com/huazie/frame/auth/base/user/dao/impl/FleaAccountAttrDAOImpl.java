package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaAccountAttrDAO;
import com.huazie.frame.auth.base.user.entity.FleaAccountAttr;
import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.auth.common.UserStateEnum;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaAccountAttrDAOImpl.class);

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
            LOGGER.debug("FleaAccountAttrDAOImpl##queryValidAccountAttrs(Long) FleaAccountList = {}", fleaAccountList);
        }

        return fleaAccountList;
    }
}