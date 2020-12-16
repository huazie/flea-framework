package com.huazie.frame.core.base.cfgdata.dao.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.core.base.cfgdata.dao.interfaces.IFleaJerseyResClientDAO;
import com.huazie.frame.core.base.cfgdata.entity.FleaJerseyResClient;
import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.core.common.FleaConfigEntityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> Flea Jersey 资源客户端DAO层实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("resClientDAO")
public class FleaJerseyResClientDAOImpl extends FleaConfigDAOImpl<FleaJerseyResClient> implements IFleaJerseyResClientDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(FleaJerseyResClientDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public FleaJerseyResClient getResClient(String clientCode) throws CommonException {

        List<FleaJerseyResClient> resClientList = getQuery(null)
                .equal(FleaConfigEntityConstants.E_CLIENT_CODE, clientCode)
                .equal(FleaConfigEntityConstants.E_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        FleaJerseyResClient resClient = null;

        if (CollectionUtils.isNotEmpty(resClientList)) {
            resClient = resClientList.get(0);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ResClient = {}", resClient);
        }

        return resClient;
    }
}
