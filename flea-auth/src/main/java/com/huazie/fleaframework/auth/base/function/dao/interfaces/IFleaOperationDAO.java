package com.huazie.fleaframework.auth.base.function.dao.interfaces;

import com.huazie.fleaframework.auth.base.function.entity.FleaOperation;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea操作DAO层接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaOperationDAO extends IAbstractFleaJPADAO<FleaOperation> {

    /**
     * 根据操作编号，查询有效的Flea操作数据
     *
     * @param operationId 操作编号
     * @return Flea操作数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaOperation queryValidOperation(Long operationId) throws CommonException;

    /**
     * 根据操作编码和操作名称，查询有效的Flea操作数据集
     *
     * @param operationCode 操作编码
     * @param operationName 操作名称
     * @return Flea操作数据集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaOperation> queryValidOperations(String operationCode, String operationName) throws CommonException;
}