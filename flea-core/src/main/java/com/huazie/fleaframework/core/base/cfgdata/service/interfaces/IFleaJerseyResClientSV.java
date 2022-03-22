package com.huazie.fleaframework.core.base.cfgdata.service.interfaces;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResClient;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * Flea Jersey 资源客户端服务层接口
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyResClientSV extends IAbstractFleaJPASV<FleaJerseyResClient> {

    /**
     * 根据客户断编码，获取唯一的资源客户端配置数据。
     *
     * @param clientCode 客户端编码
     * @return 资源客户端配置数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaJerseyResClient getResClient(String clientCode) throws CommonException;

}
