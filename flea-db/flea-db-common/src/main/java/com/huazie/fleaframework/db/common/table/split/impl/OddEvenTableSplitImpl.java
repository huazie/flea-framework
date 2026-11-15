package com.huazie.fleaframework.db.common.table.split.impl;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.common.SplitFormEnum;

/**
 * 奇偶分表实现类，通过序列形态参数化，替代此前
 * 数字/小写字母/大写字母三个独立实现类。
 * 分表转换列值为偶数，则返回 1 / a / A；反之，则返回 2 / b / B
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class OddEvenTableSplitImpl extends AbstractTableSplitImpl {

    private static final int INT_DEC = 10; // 十进制的基数值

    private final SplitFormEnum form; // 分表序列键形态

    /**
     * 构造方法
     *
     * @param form 分表序列键形态
     * @since 2.0.0
     */
    public OddEvenTableSplitImpl(SplitFormEnum form) {
        this.form = form;
    }

    @Override
    public String convert(Object tableSplitColumn) throws CommonException {
        int remainder = convertCommon(tableSplitColumn, INT_DEC);
        switch (form) {
            case NUMBER:
                // 数字形态：偶数返回 1，奇数返回 2
                return StringUtils.valueOf(remainder + 1);
            case LOWERCASE:
                // 小写字母形态：偶数返回 a，奇数返回 b
                return StringUtils.valueOf((char) (remainder + 'a'));
            case UPPERCASE:
                // 大写字母形态：偶数返回 A，奇数返回 B
                return StringUtils.valueOf((char) (remainder + 'A'));
            default:
                throw new IllegalStateException("Unexpected split form: " + form);
        }
    }
}
