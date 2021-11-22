package com.huazie.fleaframework.db.common.table.split.impl;

import com.huazie.fleaframework.common.DateFormatEnum;
import com.huazie.fleaframework.common.exception.CommonException;

/**
 * <p> 按年分表实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public final class YYYYTableSplitImpl extends AbstractTableSplitImpl {

    @Override
    public String convert(Object tableSplitColumn) throws CommonException {
        return convert(tableSplitColumn, DateFormatEnum.YYYY);
    }

}
