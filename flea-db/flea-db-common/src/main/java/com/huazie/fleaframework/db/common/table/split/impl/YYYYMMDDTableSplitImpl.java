package com.huazie.fleaframework.db.common.table.split.impl;

import com.huazie.fleaframework.common.DateFormatEnum;
import com.huazie.fleaframework.common.exception.CommonException;

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
