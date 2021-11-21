package com.huazie.fleaframework.db.common.table.split.impl;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.exception.CommonException;

/**
 * <p> 一位分表实现，截取分表字段后1位字符，并以大写返回 </p>
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public final class OneUpperTableSplitImpl extends AbstractTableSplitImpl {

    @Override
    public String convert(Object tableSplitColumn) throws CommonException {
        return convert(tableSplitColumn, CommonConstants.NumeralConstants.INT_ONE, false, true);
    }

}
