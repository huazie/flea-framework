package com.huazie.fleaframework.auth.base.function.service.interfaces;

import com.huazie.fleaframework.auth.base.function.entity.FleaOperation;
import com.huazie.fleaframework.auth.common.pojo.function.operation.FleaOperationPOJO;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea操作SV层接口定义
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IFleaOperationSV extends IAbstractFleaJPASV<FleaOperation> {

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

    /**
     * 查询所有有效的Flea操作数据集
     *
     * @return Flea操作数据集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaOperation> queryAllValidOperations() throws CommonException;

    /**
     * 保存Flea操作
     *
     * @param fleaOperationPOJO Flea操作POJO对象
     * @return Flea操作数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaOperation saveOperation(FleaOperationPOJO fleaOperationPOJO) throws CommonException;
}