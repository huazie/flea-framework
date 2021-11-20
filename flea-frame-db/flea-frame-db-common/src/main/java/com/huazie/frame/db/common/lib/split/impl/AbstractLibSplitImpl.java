package com.huazie.frame.db.common.lib.split.impl;

import com.huazie.frame.common.CommonConstants;
import com.huazie.frame.common.exception.CommonException;
import com.huazie.frame.common.util.ExceptionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import com.huazie.frame.db.common.exception.LibSplitException;
import com.huazie.frame.db.common.lib.split.ILibSplit;

/**
 * 抽象分库转换实现，实现分库转换接口，封装公共的分库转换处理实现逻辑
 *
 * @author huazie
 * @version 1.1.0
 * @since 1.1.0
 */
public abstract class AbstractLibSplitImpl implements ILibSplit {

    protected static final int INT_DEC = 10; // 十进制的基数值
    protected static final int INT_HEX = 16; // 十六进制的基数值

    protected static final char LOWER_A = 'a'; // 分库名起始小写字母
    protected static final char UPPER_A = 'A'; // 分库名起始大写字母

    /**
     * 分库转换的通用实现逻辑
     *
     * @param splitLibObj 分库对象
     * @param radix       基数
     * @param count       分库总数
     * @return 分库转换值
     * @throws CommonException 通用异常
     */
    protected int convertCommon(Object splitLibObj, int radix, int count) throws CommonException {
        // 【{0}】不能为空
        ObjectUtils.checkEmpty(splitLibObj, LibSplitException.class, "ERROR-DB-DAO0000000002", "splitLibObj");

        if (count < CommonConstants.NumeralConstants.INT_TWO) {
            // 至少需要2个分库，请检查！
            ExceptionUtils.throwCommonException(LibSplitException.class, "ERROR-DB-LSP0000000001");
        }

        String splitLibStr = splitLibObj.toString();
        String lastChar = splitLibStr.substring(splitLibStr.length() - CommonConstants.NumeralConstants.INT_ONE);
        return Integer.parseInt(lastChar, radix) % count;
    }
}
