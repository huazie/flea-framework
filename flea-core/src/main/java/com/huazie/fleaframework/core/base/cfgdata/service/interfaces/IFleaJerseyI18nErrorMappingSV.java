package com.huazie.fleaframework.core.base.cfgdata.service.interfaces;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyI18nErrorMapping;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * <p> Flea Jersey 国际码和错误码映射服务层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyI18nErrorMappingSV extends IAbstractFleaJPASV<FleaJerseyI18nErrorMapping> {

    /**
     * <p> 获取国际码和错误码映射数据集合 </p>
     *
     * @param resourceCode 资源编码
     * @param serviceCode  服务编码
     * @return 国际码和错误码映射数据集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaJerseyI18nErrorMapping> getMappings(String resourceCode, String serviceCode) throws CommonException;

    /**
     * <p> 获取国际码和错误码映射数据 </p>
     *
     * @param resourceCode 资源编码
     * @param serviceCode  服务编码
     * @return 国际码和错误码映射数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaJerseyI18nErrorMapping getMapping(String resourceCode, String serviceCode, String i18nCode) throws CommonException;
}
