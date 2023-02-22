package com.huazie.fleaframework.db.common.table.split.impl;

import com.huazie.fleaframework.common.DateFormatEnum;
import com.huazie.fleaframework.common.exceptions.CommonException;

/**
 * 按年月日分表实现
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
