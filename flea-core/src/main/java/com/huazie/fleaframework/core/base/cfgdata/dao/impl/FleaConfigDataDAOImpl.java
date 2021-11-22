package com.huazie.fleaframework.core.base.cfgdata.dao.impl;

import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.slf4j.FleaLogger;
import com.huazie.fleaframework.common.slf4j.impl.FleaLoggerProxy;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.core.base.cfgdata.dao.interfaces.IFleaConfigDataDAO;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaConfigData;
import com.huazie.fleaframework.core.common.FleaConfigEntityConstants;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> Flea 配置数据DAO层实现类 </p>
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
@Repository("fleaConfigDataDAO")
public class FleaConfigDataDAOImpl extends FleaConfigDAOImpl<FleaConfigData> implements IFleaConfigDataDAO {

    private static final FleaLogger LOGGER = FleaLoggerProxy.getProxyInstance(FleaConfigDataDAOImpl.class);

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<FleaConfigData> getConfigDataList(String configType, String configCode) throws CommonException {

        List<FleaConfigData> fleaConfigDataList = getQuery(null)
                .equal(FleaConfigEntityConstants.E_CONFIG_TYPE, configType)
                .equal(FleaConfigEntityConstants.E_CONFIG_CODE, configCode)
                .equal(FleaConfigEntityConstants.E_CONFIG_STATE, EntityStateEnum.IN_USE.getState()) // 查在用状态的配置数据
                .getResultList();

        if (LOGGER.isDebugEnabled() && ObjectUtils.isNotEmpty(fleaConfigDataList)) {
            Object obj = new Object() {};
            LOGGER.debug1(obj, "List = {}", fleaConfigDataList);
            LOGGER.debug1(obj, "Count = {}", fleaConfigDataList.size());
        }

        return fleaConfigDataList;
    }
}