package com.huazie.frame.core.base.cfgdata.dao.interfaces;

import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.base.cfgdata.ivalues.IFleaParaDetailValue;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

import java.util.List;

/**
 * <p> 参数配置数据DAO层接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaParaDetailDAO extends IAbstractFleaJPADAO<FleaParaDetail> {

    /**
     * <p> 获取参数配置数据集合 </p>
     *
     * @param paraType 参数配置数据类型
     * @param paraCode 参数配置数据编码
     * @return 参数配置数据集合
     * @throws Exception
     * @since 1.0.0
     */
    List<IFleaParaDetailValue> getParaDetail(String paraType, String paraCode) throws Exception;

}
