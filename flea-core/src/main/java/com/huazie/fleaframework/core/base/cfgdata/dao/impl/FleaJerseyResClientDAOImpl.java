package com.huazie.fleaframework.core.base.cfgdata.dao.impl;

import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.core.base.cfgdata.dao.interfaces.IFleaJerseyResClientDAO;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResClient;
import com.huazie.fleaframework.core.common.FleaConfigEntityConstants;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea Jersey 资源客户端DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("resClientDAO")
public class FleaJerseyResClientDAOImpl extends FleaConfigDAOImpl<FleaJerseyResClient> implements IFleaJerseyResClientDAO {

    @Override
    public FleaJerseyResClient getResClient(String clientCode) throws CommonException {

        List<FleaJerseyResClient> resClientList = getQuery(null)
                .equal(FleaConfigEntityConstants.E_CLIENT_CODE, clientCode)
                .equal(FleaConfigEntityConstants.E_STATE, EntityStateEnum.IN_USE.getState())
                .getResultList();

        return CollectionUtils.getFirstElement(resClientList, FleaJerseyResClient.class);
    }
}
