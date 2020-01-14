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

import java.util.Date;
import java.util.List;

/**
 * <p> Flea帐户信息DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaAccountDAO")
public class FleaAccountDAOImpl extends FleaAuthDAOImpl<FleaAccount> implements IFleaAccountDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaAccountDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public FleaAccount queryAccount(String accountCode, String accountPwd) throws CommonException {

        Date currentDate = DateUtils.getCurrentTime();

        List<FleaAccount> accountList = getQuery(null)
                .equal(FleaAuthEntityConstants.AccountEntityConstants.ACCOUNT_CODE, accountCode)
                .equal(FleaAuthEntityConstants.AccountEntityConstants.ACCOUNT_PWD, accountPwd)
                .equal(FleaAuthEntityConstants.AccountEntityConstants.ACCOUNT_STATE, UserStateEnum.IN_USE.getValue())
                .lessThan(FleaAuthEntityConstants.EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.EXPIRY_DATE, currentDate)
                .getResultList();

        FleaAccount fleaAccount = null;

        if (CollectionUtils.isNotEmpty(accountList)) {
            fleaAccount = accountList.get(0);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaAccountDAOImpl##queryAccount(String, String) FleaAccount={}", fleaAccount);
        }

        return fleaAccount;

    }
}