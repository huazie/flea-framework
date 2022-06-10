package com.huazie.fleaframework.auth.base.function.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaFunctionAttrDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaFunctionAttr;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.DateUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Flea功能扩展属性DAO层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Repository("fleaFunctionAttrDAO")
public class FleaFunctionAttrDAOImpl extends FleaAuthDAOImpl<FleaFunctionAttr> implements IFleaFunctionAttrDAO {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaFunctionAttrDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaFunctionAttr> getFunctionAttrList(Long functionId, String functionType, String attrCode) throws CommonException {

        Date currentDate = DateUtils.getCurrentTime();

        FleaFunctionAttr fleaFunctionAttr = new FleaFunctionAttr();
        fleaFunctionAttr.setFunctionId(functionId);
        fleaFunctionAttr.setFunctionType(functionType);
        fleaFunctionAttr.setAttrCode(attrCode);
        fleaFunctionAttr.setState(EntityStateEnum.IN_USE.getState());
        fleaFunctionAttr.setEffectiveDate(currentDate);
        fleaFunctionAttr.setExpiryDate(currentDate);
        List<FleaFunctionAttr> fleaFunctionAttrList = getQuery(null).initQueryEntity(fleaFunctionAttr)
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_FUNCTION_ID)
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_FUNCTION_TYPE)
                .equal(FleaAuthEntityConstants.E_ATTR_CODE)
                .equal(FleaAuthEntityConstants.E_STATE)
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE)
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "FunctionAttrList = {}", fleaFunctionAttrList);
        }

        return fleaFunctionAttrList;
    }
}