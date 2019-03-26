package com.huazie.frame.db.common.tab.split.impl;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.TableSplitException;
import com.huazie.frame.db.common.tab.split.ITableSplit;

/**
 * <p> 两位十六进制分表实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class TwoHexTableSplitImpl extends AbstractHexTableSplitImpl {

    private static final long serialVersionUID = 142194326684219859L;

    private final static int TWO = 2;

    @Override
    public String convert(String tableName, Object tableSplitColumn) throws TableSplitException {
        return convert(tableName, tableSplitColumn, TWO);
    }

}
