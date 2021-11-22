package com.huazie.fleaframework.core.base.cfgdata.dao.interfaces;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

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
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaJerseyResService getResService(String serviceCode, String resourceCode) throws CommonException;

}
