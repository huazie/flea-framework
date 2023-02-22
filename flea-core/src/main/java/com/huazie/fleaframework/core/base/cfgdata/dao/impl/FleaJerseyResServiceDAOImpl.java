package com.huazie.fleaframework.core.base.cfgdata.dao.impl;

import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.core.base.cfgdata.dao.interfaces.IFleaJerseyResServiceDAO;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaJerseyResService;
import com.huazie.fleaframework.core.common.FleaConfigEntityConstants;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea Jersey 资源服务DAO层实现类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository("resServiceDAO")
public class FleaJerseyResServiceDAOImpl extends FleaConfigDAOImpl<FleaJerseyResService> implements IFleaJerseyResServiceDAO {

    @Override
    public FleaJerseyResService getResService(String resourceCode, String serviceCode) throws CommonException {

        List<FleaJerseyResService> resServiceList = getQuery(null)
                .equal(FleaConfigEntityConstants.E_RESOURCE_CODE, resourceCode)
                .equal(FleaConfigEntityConstants.E_SERVICE_CODE, serviceCode)
                .equal(FleaConfigEntityConstants.E_STATE, EntityStateEnum.IN_USE.getState()) // 查询在用状态的资源服务
                .getResultList();

        return CollectionUtils.getFirstElement(resServiceList, FleaJerseyResService.class);
    }
}
