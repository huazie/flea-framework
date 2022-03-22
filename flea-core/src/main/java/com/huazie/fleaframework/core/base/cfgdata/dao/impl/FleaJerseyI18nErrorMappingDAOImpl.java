package com.huazie.fleaframework.core.base.cfgdata.dao.impl;

import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.core.base.cfgdata.dao.interfaces.IFleaJerseyI18nErrorMappingDAO;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyI18nErrorMapping;
import com.huazie.fleaframework.core.common.FleaConfigEntityConstants;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 国际码和错误码映射DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("i18nErrorMappingDAO")
public class FleaJerseyI18nErrorMappingDAOImpl extends FleaConfigDAOImpl<FleaJerseyI18nErrorMapping> implements IFleaJerseyI18nErrorMappingDAO {

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaJerseyI18nErrorMapping> getMappings(String resourceCode, String serviceCode) throws CommonException {
        return getQuery(null)
                .equal(FleaConfigEntityConstants.E_RESOURCE_CODE, resourceCode)
                .equal(FleaConfigEntityConstants.E_SERVICE_CODE, serviceCode)
                .equal(FleaConfigEntityConstants.E_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public FleaJerseyI18nErrorMapping getMapping(String resourceCode, String serviceCode, String i18nCode) throws CommonException {

        List<FleaJerseyI18nErrorMapping> mappingList = getQuery(null)
                .equal(FleaConfigEntityConstants.E_RESOURCE_CODE, resourceCode)
                .equal(FleaConfigEntityConstants.E_SERVICE_CODE, serviceCode)
                .equal(FleaConfigEntityConstants.E_I18N_CODE, i18nCode)
                .equal(FleaConfigEntityConstants.E_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        return CollectionUtils.getFirstElement(mappingList, FleaJerseyI18nErrorMapping.class);
    }

}
