package com.huazie.frame.core.base.cfgdata.service.interfaces;

import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.base.cfgdata.ivalues.IFleaParaDetailValue;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;


/**
 * <p> 参数配置数据Service接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaParaDetailSV extends IAbstractFleaJPASV<FleaParaDetail> {

    /**
     * <p> 获取参数配置数据集合 </p>
     *
     * @param paraType 参数配置数据类型
     * @param paraCode 参数配置数据编码
     * @return 参数配置数据集合
     * @throws Exception
     * @since 1.0.0
     */
    List<IFleaParaDetailValue> getParaDetails(String paraType, String paraCode) throws Exception;

    /**
     * <p> 获取单个参数配置数据 </p>
     *
     * @param paraType 参数配置数据类型
     * @param paraCode 参数配置数据编码
     * @return 单个参数配置数据
     * @throws Exception
     * @since 1.0.0
     */
    IFleaParaDetailValue getParaDetail(String paraType, String paraCode) throws Exception;
}
