package com.huazie.fleaframework.db.common.lib.split.impl;

import com.huazie.fleaframework.common.exception.CommonException;
import com.huazie.fleaframework.common.util.StringUtils;

/**
 * 十六进制数字分库转换实现类，即转换后的分库序列键为整数数字，
 * 从1开始，到分库数 {@code count} 结束。
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public final class HexNumberLibSplitImpl extends AbstractLibSplitImpl {

    @Override
    public String convert(Object splitLibObj, int count) throws CommonException {
        return StringUtils.valueOf(convertCommon(splitLibObj, INT_HEX, count) + 1);
    }
}
