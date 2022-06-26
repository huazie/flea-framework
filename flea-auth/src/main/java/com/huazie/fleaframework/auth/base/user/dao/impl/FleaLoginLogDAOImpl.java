package com.huazie.fleaframework.auth.base.user.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaLoginLogDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaLoginLog;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants.UserEntityConstants;
import com.huazie.fleaframework.auth.common.LoginStateEnum;
import com.huazie.fleaframework.common.FleaSessionManager;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.db.common.DBConstants;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea登录日志DAO层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Repository("fleaLoginLogDAO")
public class FleaLoginLogDAOImpl extends FleaAuthDAOImpl<FleaLoginLog> implements IFleaLoginLogDAO {

    @Override
    public FleaLoginLog queryLastUserLoginLog(Long accountId) throws CommonException {
        FleaLoginLog fleaLoginLogEntity = new FleaLoginLog();
        fleaLoginLogEntity.setAccountId(accountId); // 账户编号
        fleaLoginLogEntity.setSystemAccountId(FleaSessionManager.getSystemAccountId()); // 系统账户编号
        fleaLoginLogEntity.setLoginState(LoginStateEnum.LOGINING.getState()); // 登录中
        fleaLoginLogEntity.setCreateDate(DateUtils.getCurrentTime());

        List<FleaLoginLog> fleaLoginLogList = this.getQuery(null)
                .initQueryEntity(fleaLoginLogEntity)
                .equal(UserEntityConstants.E_ACCOUNT_ID)
                .equal(UserEntityConstants.E_SYSTEM_ACCOUNT_ID)
                .equal(UserEntityConstants.E_LOGIN_STATE)
                .addOrderBy(UserEntityConstants.E_LOGIN_TIME, DBConstants.SQLConstants.SQL_ORDER_DESC)
                .getResultList(0, 1);

        return CollectionUtils.getFirstElement(fleaLoginLogList, FleaLoginLog.class);
    }
}