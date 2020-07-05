package com.huazie.frame.auth.base.user.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.user.dao.interfaces.IFleaLoginLogDAO;
import com.huazie.frame.auth.base.user.entity.FleaLoginLog;
import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.DateUtils;
import com.huazie.frame.db.common.DBConstants;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> Flea登录日志DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaLoginLogDAO")
public class FleaLoginLogDAOImpl extends FleaAuthDAOImpl<FleaLoginLog> implements IFleaLoginLogDAO {

    @Override
    @SuppressWarnings(value = "unchecked")
    public FleaLoginLog queryLastUserLoginLog(Long accountId) throws CommonException {

        FleaLoginLog fleaLoginLogEntity = new FleaLoginLog();
        fleaLoginLogEntity.setAccountId(accountId);
        fleaLoginLogEntity.setLoginState(FleaLoginLog.LOGIN_STATE_1);
        fleaLoginLogEntity.setCreateDate(DateUtils.getCurrentTime());

        List<FleaLoginLog> fleaLoginLogList = getQuery(null)
                .initQueryEntity(fleaLoginLogEntity)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_ID)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_LOGIN_STATE)
                .addOrderby(FleaAuthEntityConstants.UserEntityConstants.E_LOGIN_TIME, DBConstants.SQLConstants.SQL_ORDER_DESC)
                .getResultList(0, 1);

        if (CollectionUtils.isNotEmpty(fleaLoginLogList)) {
            return fleaLoginLogList.get(0);
        } else {
            return null;
        }
    }
}