package com.huazie.fleaframework.core.base.cfgdata.dao.impl;

import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.dao.interfaces.IFleaConfigDataDAO;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaConfigData;
import com.huazie.fleaframework.core.common.FleaConfigEntityConstants;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Flea 配置数据DAO层实现类
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
@Repository("fleaConfigDataDAO")
public class FleaConfigDataDAOImpl extends FleaConfigDAOImpl<FleaConfigData> implements IFleaConfigDataDAO {

    @Override
    public List<FleaConfigData> getConfigDataList(String configType, String configCode) throws CommonException {
        return getQuery(null)
                .equal(FleaConfigEntityConstants.E_CONFIG_TYPE, configType)
                .equal(FleaConfigEntityConstants.E_CONFIG_CODE, configCode)
                .equal(FleaConfigEntityConstants.E_CONFIG_STATE, EntityStateEnum.IN_USE.getState()) // 查在用状态的配置数据
                .getResultList();
    }
}