package com.huazie.fleaframework.core.base.cfgdata.service.interfaces;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.core.base.cfgdata.entity.FleaConfigData;
import com.huazie.fleaframework.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;

/**
 * <p> Flea 配置数据SV层接口定义 </p>
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public interface IFleaConfigDataSV extends IAbstractFleaJPASV<FleaConfigData> {

    /**
     * <p> 获取配置数据集合 </p>
     *
     * @param configType 配置数据类型
     * @param configCode 配置数据编码
     * @return 配置数据集合
     * @throws CommonException 通用异常
     * @since 1.1.0
     */
    List<FleaConfigData> getConfigDatas(String configType, String configCode) throws CommonException;

    /**
     * <p> 获取单个配置数据集合 </p>
     *
     * @param configType 配置数据类型
     * @param configCode 配置数据编码
     * @return 配置数据
     * @throws CommonException 通用异常
     * @since 1.1.0
     */
    FleaConfigData getConfigData(String configType, String configCode) throws CommonException;
}