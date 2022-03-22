package com.huazie.fleaframework.core.base.cfgdata.dao.interfaces;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResource;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * Flea Jersey 资源DAO层接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyResourceDAO extends IAbstractFleaJPADAO<FleaJerseyResource> {

    /**
     * 根据资源编码，获取在用的资源列表；当资源编码为空，则查询所有在用的资源列表。
     *
     * @param resourceCode 资源编码
     * @return 资源列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaJerseyResource> getResource(String resourceCode) throws CommonException;

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
