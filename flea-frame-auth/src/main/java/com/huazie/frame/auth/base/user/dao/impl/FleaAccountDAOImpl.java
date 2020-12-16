package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaAccountDAO;
import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.auth.common.UserStateEnum;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p> Flea账户信息DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaAccountDAO")
@SuppressWarnings(value = "unchecked")
public class FleaAccountDAOImpl extends FleaAuthDAOImpl<FleaAccount> implements IFleaAccountDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaAccountDAOImpl.class);

    @Override
    public FleaAccount queryAccount(String accountCode, String accountPwd) throws CommonException {

        Date currentDate = DateUtils.getCurrentTime();

        List<FleaAccount> accountList = getQuery(null)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_CODE, accountCode)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_PWD, accountPwd)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_STATE, UserStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate)
                .getResultList();

        FleaAccount fleaAccount = null;

        if (CollectionUtils.isNotEmpty(accountList)) {
            fleaAccount = accountList.get(0);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaAccount = {}", fleaAccount);
        }

        return fleaAccount;
    }

    @Override
    public FleaAccount queryValidAccount(String accountCode) throws CommonException {

        Date currentDate = DateUtils.getCurrentTime();

        List<Integer> accountStateList = new ArrayList<Integer>();
        accountStateList.add(UserStateEnum.IN_USE.getState());
        accountStateList.add(UserStateEnum.IN_AUDITING.getState());

        List<FleaAccount> accountList = getQuery(null)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_CODE, accountCode)
                .in(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_STATE, accountStateList)
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate)
                .getResultList();

        FleaAccount fleaAccount = null;

        if (CollectionUtils.isNotEmpty(accountList)) {
            fleaAccount = accountList.get(0);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaAccount = {}", fleaAccount);
        }

        return fleaAccount;
    }

    @Override
    public FleaAccount queryValidAccount(Long accountId) throws CommonException {

        Date currentDate = DateUtils.getCurrentTime();

        List<FleaAccount> accountList = getQuery(null)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_ID, accountId)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_STATE, UserStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate)
                .getResultList();

        FleaAccount fleaAccount = null;

        if (CollectionUtils.isNotEmpty(accountList)) {
            fleaAccount = accountList.get(0);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaAccount = {}", fleaAccount);
        }

        return fleaAccount;
    }
}