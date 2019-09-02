package com.huazie.frame.core.base.cfgdata.dao.impl;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaJerseyI18nErrorMappingDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyI18nErrorMapping;
import com.huazie.frame.core.common.EntityStateEnum;
import com.huazie.frame.core.common.FleaEntityConstants;
import com.huazie.frame.db.jpa.common.FleaJPAQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaJerseyI18nErrorMappingDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaJerseyI18nErrorMapping> getMappings(String resourceCode, String serviceCode) throws Exception {
        FleaJPAQuery query = initQuery(resourceCode, serviceCode);

        query.equal(FleaEntityConstants.S_STATE, EntityStateEnum.IN_USE.getValue());

        List<FleaJerseyI18nErrorMapping> mappingList = query.getResultList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaJerseyI18NErrorMappingDAOImpl##getMappings(String, String) List={}", mappingList);
            LOGGER.debug("FleaJerseyI18NErrorMappingDAOImpl##getMappings(String, String) Count={}", mappingList.size());
        }

        return mappingList;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public FleaJerseyI18nErrorMapping getMapping(String resourceCode, String serviceCode, String i18nCode) throws Exception {

        FleaJPAQuery query = initQuery(resourceCode, serviceCode);

        if (StringUtils.isNotBlank(i18nCode)) {
            query.equal(FleaEntityConstants.S_I18N_CODE, i18nCode);
        }

        query.equal(FleaEntityConstants.S_STATE, EntityStateEnum.IN_USE.getValue());

        List<FleaJerseyI18nErrorMapping> mappingList = query.getResultList();

        FleaJerseyI18nErrorMapping mapping = null;

        if (CollectionUtils.isNotEmpty(mappingList)) {
            mapping = mappingList.get(0);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaJerseyI18NErrorMappingDAOImpl##getMapping(String, String, String) Mapping={}", mapping);
        }

        return mapping;
    }

    /**
     * <p> 初始化 FleaJPAQuery </p>
     *
     * @param resourceCode 资源编码
     * @param serviceCode  服务编码
     * @return FleaJPAQuery查询对象
     * @throws Exception
     * @since 1.0.0
     */
    private FleaJPAQuery initQuery(String resourceCode, String serviceCode) throws Exception {
        FleaJPAQuery query = getQuery(null);

        if (StringUtils.isNotBlank(resourceCode)) {
            query.equal(FleaEntityConstants.S_RESOURCE_CODE, resourceCode);
        }

        if (StringUtils.isNotBlank(serviceCode)) {
            query.equal(FleaEntityConstants.S_SERVICE_CODE, serviceCode);
        }

        return query;
    }
}
