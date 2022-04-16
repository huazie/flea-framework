package com.huazie.fleaframework.db.common.lib.split;

/**
 * 分库转换类型枚举，定义了分库转换实现
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum LibSplitEnum {

    DEC_NUM("DEC_NUM", "com.huazie.fleaframework.db.common.lib.split.impl.DecNumberLibSplitImpl", "(十进制数)数字分库转换实现类"),
    HEX_NUM("HEX_NUM", "com.huazie.fleaframework.db.common.lib.split.impl.HexNumberLibSplitImpl", "(十六进制)数字分库转换实现类"),
    DEC_ABC("DEC_ABC", "com.huazie.fleaframework.db.common.lib.split.impl.DecLowercaseLibSplitImpl", "(十进制)小写字母分库转换实现类"),
    HEX_ABC("HEX_ABC", "com.huazie.fleaframework.db.common.lib.split.impl.HexLowercaseLibSplitImpl", "(十六进制)小写字母分库转换实现类"),
    DEC_UABC("DEC_UABC", "com.huazie.fleaframework.db.common.lib.split.impl.DecUppercaseLibSplitImpl", "(十进制)大写字母分库转换实现类"),
    HEX_UABC("HEX_UABC", "com.huazie.fleaframework.db.common.lib.split.impl.HexUppercaseLibSplitImpl", "(十六进制)大写字母分库转换实现类");

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
