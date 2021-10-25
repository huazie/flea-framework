package com.huazie.frame.db.common.lib.split;

/**
 * 分库转换类型枚举，定义了分库转换实现
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum LibSplitEnum {

    NUM("NUM", "com.huazie.frame.db.common.lib.split.impl.NumberLibSplitImpl", "数字分库转换实现类"),
    ABC("ABC", "com.huazie.frame.db.common.lib.split.impl.AlphabetLibSplitImpl", "小写字母分库转换实现类"),
    UABC("UABC", "com.huazie.frame.db.common.lib.split.impl.UpperAlphabetLibSplitImpl", "大写字母分库转换实现类");

    private String key;         // 分库转换类型关键字
    private String implClass;   // 分库转换实现类
    private String desc;        // 分库转换类型描述

    LibSplitEnum(String key, String implClass, String desc) {
        this.key = key;
        this.implClass = implClass;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getImplClass() {
        return implClass;
    }

    public String getDesc() {
        return desc;
    }

}
