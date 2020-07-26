package com.huazie.frame.core.base.cfgdata.service.interfaces;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * <p> Flea Jersey资源服务 服务层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyResServiceSV extends IAbstractFleaJPASV<FleaJerseyResService> {

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
