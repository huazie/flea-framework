package com.huazie.fleaframework.db.common.lib.split.impl;

import com.huazie.fleaframework.common.exceptions.CommonException;
import com.huazie.fleaframework.common.util.StringUtils;
import com.huazie.fleaframework.db.common.SplitFormEnum;

/**
 * 通用分库转换实现类，通过进制与序列形态参数化，替代此前
 * 进制（十进制/十六进制）× 形态（数字/小写字母/大写字母）组合出的
 * 各个独立实现类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class CommonLibSplitImpl extends AbstractLibSplitImpl {

    private final int radix; // 分库转换使用的基数

    private final SplitFormEnum form; // 分库序列键形态

    /**
     * 构造方法
     *
     * @param radix 分库转换使用的基数（10 或 16）
     * @param form  分库序列键形态
     * @since 2.0.0
     */
    public CommonLibSplitImpl(int radix, SplitFormEnum form) {
        this.radix = radix;
        this.form = form;
    }

    @Override
    public String convert(Object splitLibObj, int count) throws CommonException {
        int index = convertCommon(splitLibObj, radix, count);
        switch (form) {
            case NUMBER:
                // 数字形态：返回十进制数字串，从 1 开始
                return StringUtils.valueOf(index + 1);
            case LOWERCASE:
                // 小写字母形态：从小写字母 a 开始
                return StringUtils.valueOf((char) (index + LOWER_A));
            case UPPERCASE:
                // 大写字母形态：从大写字母 A 开始
                return StringUtils.valueOf((char) (index + UPPER_A));
            default:
                throw new IllegalStateException("Unexpected split form: " + form);
        }
    }
}
