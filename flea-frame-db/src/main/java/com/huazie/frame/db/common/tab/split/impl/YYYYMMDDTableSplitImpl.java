package com.huazie.frame.db.common.tab.split.impl;

import com.huazie.frame.common.DateFormatEnum;
import com.huazie.frame.common.util.DateUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.TableSplitException;
import com.huazie.frame.db.common.tab.split.ITableSplit;

import java.util.Date;

/**
 * <p> 按年月日分表实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class YYYYMMDDTableSplitImpl implements ITableSplit {

    private static final long serialVersionUID = -5650362256544321307L;

    @Override
    public String convert(String tableName, Object tableSplitColumn) throws TableSplitException {
        String tSplitPrefix = null;
        if (ObjectUtils.isEmpty(tableSplitColumn)) {
            tSplitPrefix = DateUtils.date2String(null, DateFormatEnum.YYYYMMDD);
        }
        if (tableSplitColumn instanceof Date) {
            tSplitPrefix = DateUtils.date2String((Date) tableSplitColumn, DateFormatEnum.YYYYMMDD);
        }
        if (StringUtils.isBlank(tSplitPrefix)) {
            throw new TableSplitException("获取按年月日分表后缀异常");
        }
        return StringUtils.strCat(tableName, DBConstants.SQLConstants.SQL_UNDERLINE, tSplitPrefix);
    }

}
