package com.huazie.frame.db.common.tab.split.impl;

import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.common.util.StringUtils;
import com.huazie.frame.db.common.DBConstants;
import com.huazie.frame.db.common.exception.TableSplitException;
import com.huazie.frame.db.common.tab.split.ITableSplit;

public abstract class AbstractHexTableSplitImpl implements ITableSplit {

    private static final long serialVersionUID = 142194326684219859L;

    protected String convert(String tableName, Object tableSplitColumn, int type) throws TableSplitException {
        if(StringUtils.isBlank(tableName)){
            throw new TableSplitException("分表对应主表名不能为空");
        }
        if(ObjectUtils.isEmpty(tableSplitColumn)){
            throw new TableSplitException("分表字段不能为空");
        }
        String tSplitCol = tableSplitColumn.toString();
        if(StringUtils.isBlank(tSplitCol)){
            throw new TableSplitException("分表字段不能为空");
        }
        String tSplitPrefix = StringUtils.subStrLast(tSplitCol, type);
        return StringUtils.strCat(tableName, DBConstants.SQLConstants.SQL_UNDERLINE, tSplitPrefix.toLowerCase());
    }
}
