package com.huazie.frame.core.base.cfgdata.dao.interfaces;

import java.util.List;

import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.base.cfgdata.ivalues.IFleaParaDetailValue;
import com.huazie.frame.db.jpa.dao.interfaces.IAbstractFleaJPADAO;

/**
 * @author huazie
 * @version v1.0.0
 * @Description 静态参数配置表接口
 * @date 2017年1月30日
 */
public interface IFleaParaDetailDAO extends IAbstractFleaJPADAO<FleaParaDetail> {

    /**
     * @param paraType
     * @param paraCode
     * @return
     * @throws Exception
     * @Description 获取静态数据
     * @version v1.0.0
     * @date 2017年3月28日
     */
    List<IFleaParaDetailValue> getParaDetail(String paraType, String paraCode) throws Exception;

}
