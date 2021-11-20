package com.huazie.frame.db.common.table.split.impl;

import com.huazie.frame.common.DateFormatEnum;
import com.huazie.frame.common.exception.CommonException;

/**
 * <p> 按年月日分表实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public final class YYYYMMDDTableSplitImpl extends AbstractTableSplitImpl {

    @Override
    public String convert(Object tableSplitColumn) throws CommonException {
        return convert(tableSplitColumn, DateFormatEnum.YYYYMMDD);
    }

}
