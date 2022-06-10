package com.huazie.fleaframework.core.base.cfgdata.service.interfaces;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResource;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * Flea Jersey 资源SV层接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyResourceSV extends IAbstractFleaJPASV<FleaJerseyResource> {

    /**
     * 根据资源编码，获取唯一在用的资源；
     *
     * @param resourceCode 资源编码
     * @return 唯一在用的资源
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaJerseyResource getResource(String resourceCode) throws CommonException;

    /**
     * 查询所有在用的资源，并从中获取全部的资源包名。
     *
     * <p> 注：资源对应的资源包存在多个，以逗号分隔存储。
     *
     * @return 所有资源包名列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<String> getResourcePackages() throws CommonException;

}
