package com.huazie.frame.core.base.cfgdata.dao.interfaces;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResClient;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

/**
 * <p> Flea Jersey 资源客户端DAO层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyResClientDAO extends IAbstractFleaJPADAO<FleaJerseyResClient> {

    /**
     * <p> 获取资源客户端配置数据 </p>
     *
     * @param clientCode 客户端编码
     * @return 资源客户端配置数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaJerseyResClient getResClient(String clientCode) throws CommonException;

}
