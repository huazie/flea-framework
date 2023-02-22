package com.huazie.fleaframework.core.base.cfgdata.dao.interfaces;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

/**
 * Flea Jersey 资源服务DAO层接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyResServiceDAO extends IAbstractFleaJPADAO<FleaJerseyResService> {

    /**
     * 根据资源编码和服务编码，获取唯一在用的资源服务信息
     *
     * @param resourceCode 资源编码
     * @param serviceCode  服务编码
     * @return 在用的资源服务
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaJerseyResService getResService(String resourceCode, String serviceCode) throws CommonException;

}
