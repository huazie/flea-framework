package com.huazie.frame.db.common.table.split.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.common.DBConstants;

/**
 * <p> 两位十六进制分表实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public final class TwoHexTableSplitImpl extends AbstractTableSplitImpl {

    private static final long serialVersionUID = 142194326684219859L;

    @Override
    public String convert(Object tableSplitColumn) throws CommonException {
        return convert(tableSplitColumn, DBConstants.TableSplitConstants.TWO);
    }

}
