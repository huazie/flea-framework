package com.huazie.fleaframework.db.common.lib.split;

import com.huazie.fleaframework.db.common.SplitFormEnum;
import com.huazie.fleaframework.db.common.lib.split.impl.CommonLibSplitImpl;

/**
 * 分库转换类型枚举，定义了分库转换实现
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public enum LibSplitEnum {

    DEC_NUM("DEC_NUM", "(十进制数)数字分库转换实现类"),
    HEX_NUM("HEX_NUM", "(十六进制)数字分库转换实现类"),
    DEC_ABC("DEC_ABC", "(十进制)小写字母分库转换实现类"),
    HEX_ABC("HEX_ABC", "(十六进制)小写字母分库转换实现类"),
    DEC_UABC("DEC_UABC", "(十进制)大写字母分库转换实现类"),
    HEX_UABC("HEX_UABC", "(十六进制)大写字母分库转换实现类");

    private static final int INT_DEC = 10; // 十进制的基数值

    private static final int INT_HEX = 16; // 十六进制的基数值

    private String key;         // 分库转换类型关键字

    private String desc;        // 分库转换类型描述

    LibSplitEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 创建该分库转换类型对应的参数化实现实例。
     * 各 {@code key} 的转换逻辑已收敛至参数化实现类
     * {@link CommonLibSplitImpl}，差异参数内聚在本方法中。
     *
     * @return 分库转换实现实例
     * @since 2.0.0
     */
    public ILibSplit newInstance() {
        switch (this) {
            case DEC_NUM:
                return new CommonLibSplitImpl(INT_DEC, SplitFormEnum.NUMBER);
            case HEX_NUM:
                return new CommonLibSplitImpl(INT_HEX, SplitFormEnum.NUMBER);
            case DEC_ABC:
                return new CommonLibSplitImpl(INT_DEC, SplitFormEnum.LOWERCASE);
            case HEX_ABC:
                return new CommonLibSplitImpl(INT_HEX, SplitFormEnum.LOWERCASE);
            case DEC_UABC:
                return new CommonLibSplitImpl(INT_DEC, SplitFormEnum.UPPERCASE);
            case HEX_UABC:
                return new CommonLibSplitImpl(INT_HEX, SplitFormEnum.UPPERCASE);
            default:
                throw new IllegalStateException("Unexpected lib split type: " + this);
        }
    }
}
