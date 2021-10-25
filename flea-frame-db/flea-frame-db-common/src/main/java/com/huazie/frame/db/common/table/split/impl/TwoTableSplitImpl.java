package com.huazie.frame.db.common.table.split.impl;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.exception.CommonException;

/**
 * <p> 两位分表实现，截取分表字段后2位字符，并以小写返回 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public final class TwoTableSplitImpl extends AbstractTableSplitImpl {

    @Override
    public String convert(Object tableSplitColumn) throws CommonException {
        return convert(tableSplitColumn, CommonConstants.NumeralConstants.INT_TWO, false, false);
    }

}
