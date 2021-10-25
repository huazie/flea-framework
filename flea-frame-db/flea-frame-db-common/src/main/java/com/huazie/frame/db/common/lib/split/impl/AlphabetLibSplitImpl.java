package com.huazie.frame.db.common.lib.split.impl;

import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.StringUtils;

/**
 * 小写字母分库转换实现类，即转换后的分库序列键为小写字母，
 * 从a开始，共即 {@code count} 个 。
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public final class AlphabetLibSplitImpl extends AbstractLibSplitImpl {

    private final static char LOWER_A = 'a';

    @Override
    public String convert(Object splitLibObj, int count) throws CommonException {
        return StringUtils.valueOf((char)(convertCommon(splitLibObj, count) + LOWER_A));
    }
}
