package com.huazie.fleaframework.auth.base.user.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaAccountDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaAccount;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.auth.common.UserStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Flea账户信息DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaAccountDAO")
public class FleaAccountDAOImpl extends FleaAuthDAOImpl<FleaAccount> implements IFleaAccountDAO {

    @Override
    public FleaAccount queryValidAccount(Long accountId) throws CommonException {
        List<FleaAccount> accountList = this.getJPAQuery()
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_ID, accountId)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_STATE, UserStateEnum.IN_USE.getState())
                .getResultList();

        return CollectionUtils.getFirstElement(accountList, FleaAccount.class);
    }

    @Override
    public FleaAccount queryValidAccount(String accountCode, String accountPwd) throws CommonException {
        List<FleaAccount> accountList = this.getJPAQuery()
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_CODE, accountCode)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_PWD, accountPwd)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_STATE, UserStateEnum.IN_USE.getState())
                .getResultList();

        return CollectionUtils.getFirstElement(accountList, FleaAccount.class);
    }

    @Override
    public FleaAccount queryValidAccount(String accountCode) throws CommonException {
        List<Integer> accountStateList = new ArrayList<>();
        accountStateList.add(UserStateEnum.IN_USE.getState());
        accountStateList.add(UserStateEnum.IN_AUDITING.getState());

        List<FleaAccount> accountList = this.getJPAQuery()
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_CODE, accountCode)
                .in(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_STATE, accountStateList)
                .getResultList();

        return CollectionUtils.getFirstElement(accountList, FleaAccount.class);
    }

    /**
     * 获取Flea JPA查询对象
     *
     * @return Flea JPA查询对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaJPAQuery getJPAQuery() throws CommonException {
        Date currentDate = DateUtils.getCurrentTime();

        return this.getQuery(null)
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate);
    }
}