package com.huazie.frame.core.base.cfgdata.service.interfaces;

import com.huazie.frame.core.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.base.cfgdata.ivalues.IFleaParaDetailValue;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;

import java.util.List;


/**
 * <p> 静态配置Service操作接口 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IFleaParaDetailSV extends IAbstractFleaJPASV<FleaParaDetail> {

    /**
     * @param paraType
     * @param paraCode
     * @return
     * @throws Exception
     * @Description 获取多条数据
     * @date 2017年4月1日
     */
    public List<IFleaParaDetailValue> getParaDetails(String paraType, String paraCode) throws Exception;

    /**
     * @param paraType
     * @param paraCode
     * @return
     * @throws Exception
     * @Description 获取一条数据
     * @version v1.0.0
     * @date 2017年4月1日
     */
    public IFleaParaDetailValue getParaDetail(String paraType, String paraCode) throws Exception;
}
