package com.huazie.fleaframework.auth.base.user.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaRealNameInfoDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaRealNameInfo;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Flea实名信息DAO层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Repository("fleaRealNameInfoDAO")
public class FleaRealNameInfoDAOImpl extends FleaAuthDAOImpl<FleaRealNameInfo> implements IFleaRealNameInfoDAO {

    @Override
    public FleaRealNameInfo queryValidRealNameInfo(Long realNameId) throws CommonException {
        Date currentDate = DateUtils.getCurrentTime();

        List<FleaRealNameInfo> realNameInfoList = this.getQuery(null)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_REAL_NAME_ID, realNameId)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_REAL_NAME_STATE, EntityStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate)
                .getResultList();

        return CollectionUtils.getFirstElement(realNameInfoList, FleaRealNameInfo.class);
    }

}