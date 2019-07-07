package com.huazie.frame.core.base.cfgdata.service.interfaces;

import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResClient;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

/**
 * <p> Flea Jersey 资源客户端服务层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyResClientSV extends IAbstractFleaJPASV<FleaJerseyResClient> {

    /**
     * <p> 获取资源客户端配置数据 </p>
     *
     * @param clientCode 客户端编码
     * @return 资源客户端配置数据
     * @throws Exception
     * @since 1.0.0
     */
    FleaJerseyResClient getResClient(String clientCode) throws Exception;

}
