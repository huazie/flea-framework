package com.huazie.frame.db.common.tab.split.impl;

import com.huazie.frame.db.common.exception.TableSplitException;

public class ThreeHexTableSplitImpl extends AbstractHexTableSplitImpl {

    private final static int THREE = 3;

    @Override
    public String convert(String tableName, Object tableSplitColumn) throws TableSplitException {
        return convert(tableName, tableSplitColumn, THREE);
    }

}
