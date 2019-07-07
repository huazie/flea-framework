package com.huazie.frame.core.base.cfgdata.dao.interfaces;

import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

/**
 * <p> Flea Jersey 资源服务DAO层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyResServiceDAO extends IAbstractFleaJPADAO<FleaJerseyResService> {

    /**
     * <p> 获取资源服务 </p>
     *
     * @param serviceCode  服务编码
     * @param resourceCode 资源编码
     * @return 资源服务
     * @throws Exception
     * @since 1.0.0
     */
    FleaJerseyResService getResService(String serviceCode, String resourceCode) throws Exception;

}
