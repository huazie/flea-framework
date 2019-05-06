package com.huazie.frame.db.common.table.split.impl;

import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.TableSplitException;

/**
 * <p> 三位十六进制分表实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public final class ThreeHexTableSplitImpl extends AbstractTableSplitImpl {

    private static final long serialVersionUID = -3748910333438155964L;

    @Override
    public String convert(String tableName, Object tableSplitColumn) throws TableSplitException {
        return convert(tableName, tableSplitColumn, DBConstants.TableSplitConstants.THREE);
    }

}
