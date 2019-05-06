package com.huazie.frame.db.common.table.split;

/**
 * <p> 分表规则枚举 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum TableSplitEnum {

    TWOHEX("twohex", "com.huazie.frame.db.common.table.split.impl.TwoHexTableSplitImpl", "按两位十六进制分表"),
    THREEHEX("threehex", "com.huazie.frame.db.common.table.split.impl.ThreeHexTableSplitImpl", "按三位十六进制分表"),
    YYYY("yyyy", "com.huazie.frame.db.common.table.split.impl.YYYYTableSplitImpl", "按年分表"),
    YYYYMM("yyyymm", "com.huazie.frame.db.common.table.split.impl.YYYYMMTableSplitImpl", "按年月分表"),
    YYYYMMDD("yyyymmdd", "com.huazie.frame.db.common.table.split.impl.YYYYMMDDTableSplitImpl", "按年月日分表");

    private String key;
    private String implClass;
    private String desc;

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
