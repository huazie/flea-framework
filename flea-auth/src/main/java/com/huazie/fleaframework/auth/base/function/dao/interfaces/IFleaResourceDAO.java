package com.huazie.fleaframework.auth.base.function.dao.interfaces;

import com.huazie.fleaframework.auth.base.function.entity.FleaResource;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea资源DAO层接口
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public interface IFleaResourceDAO extends IAbstractFleaJPADAO<FleaResource> {

    /**
     * 根据资源编号，查询有效的Flea资源数据
     *
     * @param resourceId 编号
     * @return Flea资源数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaResource queryValidResource(Long resourceId) throws CommonException;

    /**
     * 根据资源编码和资源名称，查询有效的Flea资源数据集
     *
     * @param resourceCode 资源编码
     * @param resourceName 资源名称
     * @return Flea资源数据集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaResource> queryValidResources(String resourceCode, String resourceName) throws CommonException;
}