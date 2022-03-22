package com.huazie.fleaframework.core.base.cfgdata.dao.interfaces;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyI18nErrorMapping;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * 国际码和错误码映射DAO层接口类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaJerseyI18nErrorMappingDAO extends IAbstractFleaJPADAO<FleaJerseyI18nErrorMapping> {

    /**
     * 根据资源编码和服务编码，获取国际码和错误码映射数据集合。
     *
     * @param resourceCode 资源编码
     * @param serviceCode  服务编码
     * @return 国际码和错误码映射数据集合
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    List<FleaJerseyI18nErrorMapping> getMappings(String resourceCode, String serviceCode) throws CommonException;

    /**
     * 根据资源编码、服务编码和国际码，匹配唯一的国际码和错误码映射数据。
     *
     * @param resourceCode 资源编码
     * @param serviceCode  服务编码
     * @param i18nCode     国际码
     * @return 国际码和错误码映射数据
     * @throws CommonException 通用异常
     * @since 1.0.0
     */
    FleaJerseyI18nErrorMapping getMapping(String resourceCode, String serviceCode, String i18nCode) throws CommonException;

}
