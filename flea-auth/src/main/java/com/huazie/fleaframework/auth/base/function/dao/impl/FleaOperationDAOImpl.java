package com.huazie.fleaframework.auth.base.function.dao.impl;

import com.huazie.fleaframework.auth.base.FleaAuthDAOImpl;
import com.huazie.fleaframework.auth.base.function.dao.interfaces.IFleaOperationDAO;
import com.huazie.fleaframework.auth.base.function.entity.FleaOperation;
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
 * Flea操作DAO层实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Repository("fleaOperationDAO")
public class FleaOperationDAOImpl extends FleaAuthDAOImpl<FleaOperation> implements IFleaOperationDAO {

    @Override
    public FleaOperation queryValidOperation(Long operationId) throws CommonException {
        List<FleaOperation> fleaOperationList = getJPAQuery()
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_OPERATION_ID, operationId)
                .getResultList();

        return CollectionUtils.getFirstElement(fleaOperationList, FleaOperation.class);
    }

    @Override
    public List<FleaOperation> queryValidOperations(String operationCode, String operationName) throws CommonException {
        return getJPAQuery()
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_OPERATION_CODE, operationCode)
                .like(FleaAuthEntityConstants.FunctionEntityConstants.E_OPERATION_NAME, operationName) // 模糊匹配
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
                .equal(FleaAuthEntityConstants.FunctionEntityConstants.E_OPERATION_STATE, EntityStateEnum.IN_USE.getState())
                .lessThan(FleaAuthEntityConstants.E_EFFECTIVE_DATE, currentDate)
                .greaterThan(FleaAuthEntityConstants.E_EXPIRY_DATE, currentDate);
    }
}