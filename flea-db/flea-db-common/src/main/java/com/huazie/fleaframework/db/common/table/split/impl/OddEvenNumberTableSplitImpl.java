package com.huazie.fleaframework.db.common.table.split.impl;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.StringUtils;

/**
 * 数字奇偶分表实现类，即分表转换值为数字。
 * 分表转换列值为偶数，则返回1；反之，则返回2。
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class OddEvenNumberTableSplitImpl extends AbstractTableSplitImpl {

    @Override
    public String convert(Object tableSplitColumn) throws CommonException {
        return StringUtils.valueOf(convertCommon(tableSplitColumn, 10) + 1);
    }
}
