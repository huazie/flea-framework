package com.huazie.frame.core.base.cfgdata.dao.impl;

import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaJerseyResServiceDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyI18nErrorMapping;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.frame.core.common.EntityStateEnum;
import com.huazie.frame.core.common.FleaEntityConstants;
import com.huazie.frame.db.jpa.common.FleaJPAQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> Flea Jersey 资源服务DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("resServiceDAO")
public class FleaJerseyResServiceDAOImpl extends FleaConfigDAOImpl<FleaJerseyResService> implements IFleaJerseyResServiceDAO {

    private final static Logger LOGGER = LoggerFactory.getLogger(FleaJerseyResServiceDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public FleaJerseyResService getResService(String serviceCode, String resourceCode) throws Exception {
        FleaJPAQuery query = getQuery(null);

        if (StringUtils.isNotBlank(serviceCode)) {
            query.equal(FleaEntityConstants.ResServiceConstants.S_SERVICE_CODE, serviceCode);
        }

        if (StringUtils.isNotBlank(resourceCode)) {
            query.equal(FleaEntityConstants.ResServiceConstants.S_RESOURCE_CODE, resourceCode);
        }

        query.equal(FleaEntityConstants.ResServiceConstants.S_STATE, EntityStateEnum.IN_USE.getValue());

        List<FleaJerseyResService> resServiceList = query.getResultList();

        FleaJerseyResService resService = null;

        if (CollectionUtils.isNotEmpty(resServiceList)) {
            resService = resServiceList.get(0);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("FleaJerseyResServiceDAOImpl##getResService(String, String) ResService = {}", resService);
        }

        return resService;
    }
}
