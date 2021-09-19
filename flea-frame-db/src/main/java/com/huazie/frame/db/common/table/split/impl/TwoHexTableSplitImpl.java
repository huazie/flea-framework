package com.huazie.frame.db.common.table.split.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.db.common.DBConstants;

/**
 * <p> 两位十六进制分表实现，截取分表字段后2位字符，并以小写返回 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public final class TwoHexTableSplitImpl extends AbstractTableSplitImpl {

    @Override
    public String convert(Object tableSplitColumn) throws CommonException {
        return convert(tableSplitColumn, DBConstants.TableSplitConstants.TWO, false, false);
    }

}
