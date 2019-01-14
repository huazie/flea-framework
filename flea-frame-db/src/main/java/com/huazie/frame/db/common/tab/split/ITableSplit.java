package com.huazie.frame.db.common.tab.split;

import java.io.Serializable;

/**
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月25日
 *
 */
public interface ITableSplit extends Serializable{
	
	public String convert(String tableName, Object tableSplitColumn) throws Exception;
}
