package com.huazie.frame.db.common.tab.split.impl;

import java.util.Date;

import com.huazie.frame.common.DateFormatEnum;
import com.huazie.frame.common.util.DateUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.tab.split.ITableSplit;

/**
 *  <p>
 *  	按年月日分表实现
 *  </p>
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月25日
 *
 */
public class YYYYMMDDTableSplitImpl implements ITableSplit {

	private static final long serialVersionUID = -5650362256544321307L;

	@Override
	public String convert(String tableName, Object tableSplitColumn) throws Exception {
		String tSplitPrefix = null;
		if(StringUtils.isEmpty(tableSplitColumn)){
			tSplitPrefix = DateUtils.date2String(null, DateFormatEnum.YYYYMMDD);
		}
		if(tableSplitColumn instanceof Date){
			tSplitPrefix = DateUtils.date2String((Date)tableSplitColumn, DateFormatEnum.YYYYMMDD);
		}
		if(tSplitPrefix == null){
			throw new Exception("获取按年月日分表后缀异常");
		}
		return StringUtils.strCat(tableName, DBConstants.SQLConstants.SQL_UNDERLINE, tSplitPrefix);
	}

}
