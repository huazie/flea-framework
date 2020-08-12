package com.huazie.frame.db.common.table.split.impl;

import com.huazie.frame.common.DateFormatEnum;
import com.huazie.frame.common.exception.CommonException;

/**
 * <p> 按年月分表实现 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class YYYYMMTableSplitImpl extends AbstractTableSplitImpl {

    private static final long serialVersionUID = -5650362256544321307L;

    @Override
    public String convert(Object tableSplitColumn) throws CommonException{
        return convert(tableSplitColumn, DateFormatEnum.YYYYMM);
    }

}
