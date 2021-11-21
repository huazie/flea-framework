package com.huazie.frame.db.common.table.split.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.StringUtils;

/**
 * 小写字母奇偶分表实现类，即分表转换值为小写字母。
 * 分表转换列值为偶数，则返回a；反之，则返回b。
 *
 * @author huazie
 * @version 1.2.0
 * @since 1.2.0
 */
public class OddEvenAlphabetTableSplitImpl extends AbstractTableSplitImpl {

    @Override
    public String convert(Object tableSplitColumn) throws CommonException {
        return StringUtils.valueOf((char)(convertCommon(tableSplitColumn, 10) + 'a'));
    }
}