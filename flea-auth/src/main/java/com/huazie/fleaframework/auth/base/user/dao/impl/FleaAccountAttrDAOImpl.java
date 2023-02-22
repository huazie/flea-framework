package com.huazie.fleaframework.auth.base.user.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.user.dao.interfaces.IFleaAccountAttrDAO;
import com.huazie.fleaframework.auth.base.user.entity.FleaAccountAttr;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.auth.common.UserStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Flea账户扩展属性DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaAccountAttrDAO")
public class FleaAccountAttrDAOImpl extends FleaAuthDAOImpl<FleaAccountAttr> implements IFleaAccountAttrDAO {

    @Override
    public List<FleaAccountAttr> queryValidAccountAttrs(Long accountId) throws CommonException {
        Date currentDate = DateUtils.getCurrentTime();

        return this.getQuery(null)
                .equal(FleaAuthEntityConstants.UserEntityConstants.E_ACCOUNT_ID, accountId)
                .equal(FleaAuthEntityConstants.E_STATE, UserStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate)
                .getResultList();
    }
}