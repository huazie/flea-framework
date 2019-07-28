package com.huazie.frame.core.base.cfgdata.dao.interfaces;

import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResource;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * <p> Flea Jersey 资源DAO层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyResourceDAO extends IAbstractFleaJPADAO<FleaJerseyResource> {

    /**
     * <p> 获取资源定义，当资源编码为空，则查询所有在用的资源 </p>
     *
     * @param resourceCode 资源编码
     * @return 资源
     * @throws Exception
     * @since 1.0.0
     */
    List<FleaJerseyResource> getResource(String resourceCode) throws Exception;

    /**
     * <p> 获取全部资源包名 </p>
     *
     * @return 所有资源包名列表
     * @throws Exception
     * @since 1.0.0
     */
    List<String> getResourcePackages() throws Exception;

}
