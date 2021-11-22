package com.huazie.fleaframework.auth.base.user.dao.impl;

import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaRealNameInfoDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaRealNameInfo;
import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.auth.common.UserStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p> Flea实名信息DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaRealNameInfoDAO")
@SuppressWarnings(value = "unchecked")
public class FleaRealNameInfoDAOImpl extends FleaAuthDAOImpl<FleaRealNameInfo> implements IFleaRealNameInfoDAO {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaRealNameInfoDAOImpl.class);

    @Override
    public FleaRealNameInfo queryValidRealNameInfo(Long realNameId) throws CommonException {

        Date currentDate = DateUtils.getCurrentTime();

        List<FleaRealNameInfo> realNameInfoList = getQuery(null)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_REAL_NAME_ID, realNameId)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_REAL_NAME_STATE, UserStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate)
                .getResultList();

        FleaRealNameInfo fleaRealNameInfo = null;

        if (CollectionUtils.isNotEmpty(realNameInfoList)) {
            fleaRealNameInfo = realNameInfoList.get(0);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "FleaRealNameInfo = {}", fleaRealNameInfo);
        }

        return fleaRealNameInfo;
    }

}