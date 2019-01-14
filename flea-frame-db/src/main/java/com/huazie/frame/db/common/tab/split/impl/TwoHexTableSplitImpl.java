package com.huazie.frame.db.common.tab.split.impl;

import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.tab.split.ITableSplit;

/**
 *  <p>
 *  	两位十六进制分表实现
 *  </p>
 *  
 * @author huazie
 * @version v1.0.0
 * @date 2018年1月25日
 *
 */
public class TwoHexTableSplitImpl implements ITableSplit{

	private static final long serialVersionUID = 142194326684219859L;
	
	private final static int TWO = 2;

	@Override
	public String convert(String tableName, Object tableSplitColumn) throws Exception{
		String tSplitCol = tableSplitColumn.toString();
		String tSplitPrefix = StringUtils.subStrLast(tSplitCol, TwoHexTableSplitImpl.TWO);
		return StringUtils.strCat(tableName, DBConstants.SQLConstants.SQL_UNDERLINE, tSplitPrefix.toLowerCase());
	}

}
