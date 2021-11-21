package com.huazie.fleaframework.core.base.cfgdata.service.interfaces;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResource;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * <p> Flea Jersey 资源SV层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyResourceSV extends IAbstractFleaJPASV<FleaJerseyResource> {

    /**
     * <p> 获取指定资源定义 </p>
     *
     * @param resourceCode 资源编码
     * @return 资源
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaJerseyResource getResource(String resourceCode) throws CommonException;

    /**
     * <p> 获取全部资源包名 </p>
     *
     * @return 所有资源包名列表
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<String> getResourcePackages() throws CommonException;

}
