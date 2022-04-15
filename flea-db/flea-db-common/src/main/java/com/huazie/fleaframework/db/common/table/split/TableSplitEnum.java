package com.huazie.fleaframework.db.common.table.split;

/**
 * 分表转换类型枚举
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public enum TableSplitEnum {

    COLUMN("COLUMN", "com.huazie.fleaframework.db.common.table.split.impl.ColumnLowercaseTableSplitImpl", "表字段分表实现，以小写返回"),
    COLUMN_U("COLUMN_U", "com.huazie.fleaframework.db.common.table.split.impl.ColumnUppercaseTableSplitImpl", "表字段分表实现，以大写返回"),
    ODD_EVEN_N("ODD_EVEN_N", "com.huazie.fleaframework.db.common.table.split.impl.OddEvenNumberTableSplitImpl", "数字奇偶分表实现类；分表转换列值为偶数，则返回1；反之，则返回2。"),
    ODD_EVEN_L("ODD_EVEN_L", "com.huazie.fleaframework.db.common.table.split.impl.OddEvenLowercaseTableSplitImpl", "小写字母奇偶分表实现类；分表转换列值为偶数，则返回a；反之，则返回b。"),
    ODD_EVEN_U("ODD_EVEN_U", "com.huazie.fleaframework.db.common.table.split.impl.OddEvenUppercaseTableSplitImpl", "大写字母奇偶分表实现类；分表转换列值为偶数，则返回A；反之，则返回B。"),
    ONE("ONE", "com.huazie.fleaframework.db.common.table.split.impl.OneLowercaseTableSplitImpl", "按一位分表，截取分表字段后1位字符，并以小写返回"),
    ONE_B("ONE_B", "com.huazie.fleaframework.db.common.table.split.impl.OneBeforeLowercaseTableSplitImpl", "按一位分表，截取分表字段前1位字符，并以小写返回"),
    ONE_U("ONE_U", "com.huazie.fleaframework.db.common.table.split.impl.OneUppercaseTableSplitImpl", "按一位分表，截取分表字段后1位字符，并以大写返回"),
    ONE_BU("ONE_BU", "com.huazie.fleaframework.db.common.table.split.impl.OneBeforeUppercaseTableSplitImpl", "按一位分表，截取分表字段前1位字符，并以大写返回"),
    TWO("TWO", "com.huazie.fleaframework.db.common.table.split.impl.TwoLowercaseTableSplitImpl", "按两位分表，截取分表字段后2位字符，并以小写返回"),
    TWO_B("TWO_B", "com.huazie.fleaframework.db.common.table.split.impl.TwoBeforeLowercaseTableSplitImpl", "按两位分表，截取分表字段前2位字符，并以小写返回"),
    TWO_U("TWO_U", "com.huazie.fleaframework.db.common.table.split.impl.TwoUppercaseTableSplitImpl", "按两位分表，截取分表字段后2位字符，并以大写返回"),
    TWO_BU("TWO_BU", "com.huazie.fleaframework.db.common.table.split.impl.TwoBeforeUppercaseTableSplitImpl", "按两位分表，截取分表字段前2位字符，并以大写返回"),
    YYYY("YYYY", "com.huazie.fleaframework.db.common.table.split.impl.YYYYTableSplitImpl", "按年分表"),
    YYYY_MM("YYYY_MM", "com.huazie.fleaframework.db.common.table.split.impl.YYYYMMTableSplitImpl", "按年月分表"),
    YYYY_MM_DD("YYYY_MM_DD", "com.huazie.fleaframework.db.common.table.split.impl.YYYYMMDDTableSplitImpl", "按年月日分表");

    private String key;         // 分表转换类型关键字
    private String implClass;   // 分表转换实现类
    private String desc;        // 分表转换类型描述

    TableSplitEnum(String key, String implClass, String desc) {
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
