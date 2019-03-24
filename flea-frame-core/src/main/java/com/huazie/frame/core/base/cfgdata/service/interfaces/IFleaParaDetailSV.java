package com.huazie.frame.core.base.cfgdata.service.interfaces;

import java.util.List;

import com.huazie.frame.common.base.cfgdata.entity.FleaParaDetail;
import com.huazie.frame.core.base.cfgdata.ivalues.IFleaParaDetailValue;
import com.huazie.frame.db.jpa.service.interfaces.IAbstractFleaJPASV;


/**
 *
 * @Description 静态配置Service操作接口
 * 
 * @author huazie
 * @version v1.0.0
 * @date 2016年9月30日
 * 
 */
public interface IFleaParaDetailSV extends IAbstractFleaJPASV<FleaParaDetail> {
	
	/**
	 * 
	 * @Description 获取多条数据
	 * 
	 * @version v1.0.0
	 * @date 2017年4月1日
	 *
	 * @param paraType
	 * @param paraCode
	 * @return
	 * @throws Exception
	 */
	public List<IFleaParaDetailValue> getParaDetails(String paraType, String paraCode)throws Exception;

	/**
	 * 
	 * @Description 获取一条数据
	 * 
	 * @version v1.0.0
	 * @date 2017年4月1日
	 *
	 * @param paraType
	 * @param paraCode
	 * @return
	 * @throws Exception
	 */
	public IFleaParaDetailValue getParaDetail(String paraType, String paraCode)throws Exception;
}
