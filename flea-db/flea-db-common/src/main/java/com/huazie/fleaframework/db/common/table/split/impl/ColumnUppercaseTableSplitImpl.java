package com.huazie.fleaframework.db.common.table.split.impl;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.exception.CommonException;

/**
 * 表字段分表实现，以大写返回
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class ColumnUppercaseTableSplitImpl extends AbstractTableSplitImpl {

    @Override
    public String convert(Object tableSplitColumn) throws CommonException {
        return convert(tableSplitColumn, CommonConstants.NumeralConstants.INT_ZERO, true, true);
    }
}
