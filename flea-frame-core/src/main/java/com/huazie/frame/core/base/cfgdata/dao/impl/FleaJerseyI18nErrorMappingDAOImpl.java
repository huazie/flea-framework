package com.huazie.frame.core.base.cfgdata.dao.impl;

import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.slf4j.FleaLogger;
import com.huazie.frame.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaJerseyI18nErrorMappingDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyI18nErrorMapping;
import com.huazie.frame.core.common.FleaConfigEntityConstants;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> 国际码和错误码映射DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("i18nErrorMappingDAO")
public class FleaJerseyI18nErrorMappingDAOImpl extends FleaConfigDAOImpl<FleaJerseyI18nErrorMapping> implements IFleaJerseyI18nErrorMappingDAO {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaJerseyI18nErrorMappingDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaJerseyI18nErrorMapping> getMappings(String resourceCode, String serviceCode) throws CommonException {

        List<FleaJerseyI18nErrorMapping> mappingList = getQuery(null)
                .equal(FleaConfigEntityConstants.E_RESOURCE_CODE, resourceCode)
                .equal(FleaConfigEntityConstants.E_SERVICE_CODE, serviceCode)
                .equal(FleaConfigEntityConstants.E_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        if (LOGGER.isDebugEnabled()) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "FleaJerseyI18NErrorMappingDAOImpl##getMappings(String, String) List={}", mappingList);
            LOGGER.debug1(obj, "FleaJerseyI18NErrorMappingDAOImpl##getMappings(String, String) Count={}", mappingList.size());
        }

        return mappingList;
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

        FleaJerseyI18nErrorMapping mapping = null;

        if (CollectionUtils.isNotEmpty(mappingList)) {
            mapping = mappingList.get(0);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug1(new Object() {}, "FleaJerseyI18NErrorMappingDAOImpl##getMapping(String, String, String) Mapping={}", mapping);
        }

        return mapping;
    }

}
