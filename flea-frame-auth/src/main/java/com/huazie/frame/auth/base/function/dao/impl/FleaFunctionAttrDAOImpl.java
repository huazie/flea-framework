package com.huazie.frame.auth.base.function.dao.impl;

import com.huazie.frame.auth.base.FleaAuthDAOImpl;
import com.huazie.frame.auth.base.function.dao.interfaces.IFleaFunctionAttrDAO;
import com.huazie.frame.auth.base.function.entity.FleaFunctionAttr;
import com.huazie.frame.auth.common.FleaAuthEntityConstants;
import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p> Flea功能扩展属性DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaFunctionAttrDAO")
public class FleaFunctionAttrDAOImpl extends FleaAuthDAOImpl<FleaFunctionAttr> implements IFleaFunctionAttrDAO {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaFunctionAttrDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaFunctionAttr> getFunctionAttrList(Long functionId, String functionType, String attrCode) throws CommonException {

        Date currentDate = DateUtils.getCurrentTime();

        List<FleaFunctionAttr> fleaFunctionAttrList = getQuery(null)
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_FUNCTION_ID, functionId)
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_FUNCTION_TYPE, functionType)
                .equal(FleaAuthEntityConstants.E_ATTR_CODE, attrCode)
                .equal(FleaAuthEntityConstants.E_STATE, EntityStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate)
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "FunctionAttrList = {}", fleaFunctionAttrList);
        }

        return fleaFunctionAttrList;
    }
}