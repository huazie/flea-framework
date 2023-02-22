package com.huazie.fleaframework.auth.base.function.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaFunctionAttrDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaFunctionAttr;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
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

    @Override
    public FleaFunctionAttr queryValidFunctionAttr(Long attrId, String functionType) throws CommonException {
        FleaFunctionAttr fleaFunctionAttr = newFleaFunctionAttr4Query(functionType);
        fleaFunctionAttr.setAttrId(attrId);

        List<FleaFunctionAttr> fleaFunctionAttrList = this.getJPAQuery(fleaFunctionAttr)
                .equal(FleaAuthEntityConstants.E_ATTR_ID)
                .getResultList();

        return CollectionUtils.getFirstElement(fleaFunctionAttrList, FleaFunctionAttr.class);
    }

    @Override
    public List<FleaFunctionAttr> queryValidFunctionAttrs(Long functionId, String functionType, String attrCode, String attrValue, boolean isAttrValueEqual) throws CommonException {
        FleaFunctionAttr fleaFunctionAttr = newFleaFunctionAttr4Query(functionType);
        fleaFunctionAttr.setFunctionId(functionId);
        fleaFunctionAttr.setAttrCode(attrCode);
        fleaFunctionAttr.setAttrValue(attrValue);

        FleaJPAQuery fleaJPAQuery = this.getJPAQuery(fleaFunctionAttr)
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_FUNCTION_ID)
                .equal(FleaAuthEntityConstants.E_ATTR_CODE);
        if (isAttrValueEqual) { // 全值匹配
            fleaJPAQuery.equal(FleaAuthEntityConstants.E_ATTR_VALUE);
        } else { // 模糊匹配
            fleaJPAQuery.like(FleaAuthEntityConstants.E_ATTR_VALUE);
        }

        return fleaJPAQuery.getResultList();
    }

    /**
     * 新建Flea功能属性信息【用于查询】
     *
     * @param functionType 功能类型
     * @return Flea功能属性信息
     * @since 2.0.0
     */
    private FleaFunctionAttr newFleaFunctionAttr4Query(String functionType) {
        Date currentDate = DateUtils.getCurrentTime();
        FleaFunctionAttr fleaFunctionAttr = new FleaFunctionAttr();
        fleaFunctionAttr.setFunctionType(functionType);
        fleaFunctionAttr.setState(EntityStateEnum.IN_USE.getState());
        fleaFunctionAttr.setEffectiveDate(currentDate);
        fleaFunctionAttr.setExpiryDate(currentDate);
        return fleaFunctionAttr;
    }

    /**
     * 获取Flea JPA查询对象
     *
     * @param fleaFunctionAttr flea功能属性信息
     * @return Flea JPA查询对象
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    private FleaJPAQuery getJPAQuery(FleaFunctionAttr fleaFunctionAttr) throws CommonException {
        return this.getQuery(null).initQueryEntity(fleaFunctionAttr)
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_FUNCTION_TYPE)
                .equal(FleaAuthEntityConstants.E_STATE)
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE);
    }
}