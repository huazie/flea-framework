package com.huazie.fleaframework.auth.base.function.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaElementDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaElement;
import com.huazie.fleaframework.auth.common.FleaAuthEntityConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.db.jpa.common.FleaJPAQuery;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Flea元素DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("fleaElementDAO")
public class FleaElementDAOImpl extends FleaAuthDAOImpl<FleaElement> implements IFleaElementDAO {

    @Override
    public FleaElement queryValidElement(Long elementId) throws CommonException {
        List<FleaElement> fleaElementList = this.getJPAQuery()
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_ELEMENT_ID, elementId)
                .getResultList();

        return CollectionUtils.getFirstElement(fleaElementList, FleaElement.class);
    }

    @Override
    public List<FleaElement> queryValidElements(String elementCode, String elementName, Integer elementType, String elementContent) throws CommonException {
        return this.getJPAQuery()
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_ELEMENT_CODE, elementCode)
                .like(FleaAuthEntityConstants.FunctionEntityConstants.E_ELEMENT_NAME, elementName)
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_ELEMENT_TYPE, elementType)
                .like(FleaAuthEntityConstants.FunctionEntityConstants.E_ELEMENT_CONTENT, elementContent)
                .getResultList();
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
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_ELEMENT_STATE, EntityStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate);
    }
}