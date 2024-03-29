package com.huazie.fleaframework.db.common.lib.split.impl;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.StringUtils;

/**
 * 十六进制小写字母分库转换实现类，即转换后的分库序列键为小写字母，
 * 从a开始，共即 {@code count} 个 。
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public final class HexLowercaseLibSplitImpl extends AbstractLibSplitImpl {

    @Override
    public String convert(Object splitLibObj, int count) throws CommonException {
        return StringUtils.valueOf((char) (convertCommon(splitLibObj, INT_HEX, count) + LOWER_A));
    }
}
