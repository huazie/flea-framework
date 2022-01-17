package com.huazie.fleaframework.db.common.table.split.impl;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.exception.CommonException;

/**
 * 两位分表实现，截取分表字段前2位字符，并以大写返回
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public final class TwoBeforeUpperTableSplitImpl extends AbstractTableSplitImpl {

    @Override
    public String convert(Object tableSplitColumn) throws CommonException {
        return convert(tableSplitColumn, CommonConstants.NumeralConstants.INT_TWO, true, true);
    }

}
