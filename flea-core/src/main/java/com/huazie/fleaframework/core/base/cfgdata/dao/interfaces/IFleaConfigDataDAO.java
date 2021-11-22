package com.huazie.fleaframework.core.base.cfgdata.dao.interfaces;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaConfigData;
import com.huazie.fleaframework.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * <p> Flea 配置数据DAO层接口 </p>
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public interface IFleaConfigDataDAO extends IAbstractFleaJPADAO<FleaConfigData> {

    /**
     * <p> 获取配置数据集合 </p>
     *
     * @param configType 配置数据类型
     * @param configCode 配置数据编码
     * @return 配置数据集合
     * @throws CommonException 通用异常
     * @since 1.1.0
     */
    List<FleaConfigData> getConfigDataList(String configType, String configCode) throws CommonException;
}