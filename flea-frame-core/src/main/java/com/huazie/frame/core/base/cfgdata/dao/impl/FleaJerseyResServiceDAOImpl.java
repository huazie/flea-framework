package com.huazie.frame.core.base.cfgdata.dao.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaJerseyResServiceDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.core.common.FleaConfigEntityConstants;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaJerseyResServiceDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public FleaJerseyResService getResService(String serviceCode, String resourceCode) throws CommonException {

        List<FleaJerseyResService> resServiceList = getQuery(null)
                .equal(FleaConfigEntityConstants.S_SERVICE_CODE, serviceCode)
                .equal(FleaConfigEntityConstants.S_RESOURCE_CODE, resourceCode)
                .equal(FleaConfigEntityConstants.S_STATE, EntityStateEnum.IN_USE.getValue()) // 查询在用状态的资源服务
                .getResultList();

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
