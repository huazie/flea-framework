package com.huazie.frame.db.common.tab.split.impl;

import com.huazie.frame.common.DateFormatEnum;
import com.huazie.frame.db.common.exception.TableSplitException;

/**
 * <p> 按年月日分表实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class YYYYMMDDTableSplitImpl extends AbstractTableSplitImpl {

    private static final long serialVersionUID = -5650362256544321307L;

    @Override
    public String convert(String tableName, Object tableSplitColumn) throws TableSplitException {
        return convert(tableName, tableSplitColumn, DateFormatEnum.YYYYMMDD);
    }

}
