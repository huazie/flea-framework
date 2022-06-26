package com.huazie.fleaframework.auth.base.function.service.interfaces;

import com.huazie.fleaframework.auth.base.function.entity.FleaResource;
import com.huazie.fleaframework.auth.common.pojo.function.resource.FleaResourcePOJO;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea资源SV层接口定义
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public interface IFleaResourceSV extends IAbstractFleaJPASV<FleaResource> {

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

    /**
     * 查询所有有效的Flea资源数据集
     *
     * @return Flea资源数据集
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    List<FleaResource> queryAllValidResources() throws CommonException;

    /**
     * 保存Flea资源
     *
     * @param fleaResourcePOJO Flea资源POJO对象
     * @return Flea资源数据
     * @throws CommonException 通用异常
     * @since 2.0.0
     */
    FleaResource saveResource(FleaResourcePOJO fleaResourcePOJO) throws CommonException;
}