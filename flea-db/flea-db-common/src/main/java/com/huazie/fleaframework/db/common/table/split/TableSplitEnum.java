package com.huazie.fleaframework.db.common.table.split;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.DateFormatEnum;
import com.huazie.fleaframework.db.common.SplitFormEnum;
import com.huazie.fleaframework.db.common.table.split.impl.DateTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.OddEvenTableSplitImpl;
import com.huazie.fleaframework.db.common.table.split.impl.SubstrTableSplitImpl;

/**
 * 分表转换类型枚举，定义了分表转换实现
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public enum TableSplitEnum {

    COLUMN("COLUMN", "表字段分表实现，以小写返回"),
    COLUMN_U("COLUMN_U", "表字段分表实现，以大写返回"),
    ODD_EVEN_N("ODD_EVEN_N", "数字奇偶分表实现类；分表转换列值为偶数，则返回1；反之，则返回2。"),
    ODD_EVEN_L("ODD_EVEN_L", "小写字母奇偶分表实现类；分表转换列值为偶数，则返回a；反之，则返回b。"),
    ODD_EVEN_U("ODD_EVEN_U", "大写字母奇偶分表实现类；分表转换列值为偶数，则返回A；反之，则返回B。"),
    ONE("ONE", "按一位分表，截取分表字段后1位字符，并以小写返回"),
    ONE_B("ONE_B", "按一位分表，截取分表字段前1位字符，并以小写返回"),
    ONE_U("ONE_U", "按一位分表，截取分表字段后1位字符，并以大写返回"),
    ONE_BU("ONE_BU", "按一位分表，截取分表字段前1位字符，并以大写返回"),
    TWO("TWO", "按两位分表，截取分表字段后2位字符，并以小写返回"),
    TWO_B("TWO_B", "按两位分表，截取分表字段前2位字符，并以小写返回"),
    TWO_U("TWO_U", "按两位分表，截取分表字段后2位字符，并以大写返回"),
    TWO_BU("TWO_BU", "按两位分表，截取分表字段前2位字符，并以大写返回"),
    YYYY("YYYY", "按年分表"),
    YYYY_MM("YYYY_MM", "按年月分表"),
    YYYY_MM_DD("YYYY_MM_DD", "按年月日分表");

    private String key;         // 分表转换类型关键字

    private String desc;        // 分表转换类型描述

    TableSplitEnum(String key, String desc) {
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
     * 创建该分表转换类型对应的参数化实现实例。
     * 各 {@code key} 的转换逻辑已收敛至参数化实现类
     * {@link SubstrTableSplitImpl}、{@link OddEvenTableSplitImpl}、
     * {@link DateTableSplitImpl}，差异参数内聚在本方法中。
     *
     * @return 分表转换实现实例
     * @since 2.0.0
     */
    public ITableSplit newInstance() {
        switch (this) {
            case COLUMN:
                // 全列截取（len <= 0 取全列），以小写返回
                return new SubstrTableSplitImpl(CommonConstants.NumeralConstants.INT_ZERO, true, false);
            case COLUMN_U:
                // 全列截取（len <= 0 取全列），以大写返回
                return new SubstrTableSplitImpl(CommonConstants.NumeralConstants.INT_ZERO, true, true);
            case ODD_EVEN_N:
                return new OddEvenTableSplitImpl(SplitFormEnum.NUMBER);
            case ODD_EVEN_L:
                return new OddEvenTableSplitImpl(SplitFormEnum.LOWERCASE);
            case ODD_EVEN_U:
                return new OddEvenTableSplitImpl(SplitFormEnum.UPPERCASE);
            case ONE:
                // 截取分表字段后 1 位字符，以小写返回
                return new SubstrTableSplitImpl(CommonConstants.NumeralConstants.INT_ONE, false, false);
            case ONE_B:
                // 截取分表字段前 1 位字符，以小写返回
                return new SubstrTableSplitImpl(CommonConstants.NumeralConstants.INT_ONE, true, false);
            case ONE_U:
                // 截取分表字段后 1 位字符，以大写返回
                return new SubstrTableSplitImpl(CommonConstants.NumeralConstants.INT_ONE, false, true);
            case ONE_BU:
                // 截取分表字段前 1 位字符，以大写返回
                return new SubstrTableSplitImpl(CommonConstants.NumeralConstants.INT_ONE, true, true);
            case TWO:
                // 截取分表字段后 2 位字符，以小写返回
                return new SubstrTableSplitImpl(CommonConstants.NumeralConstants.INT_TWO, false, false);
            case TWO_B:
                // 截取分表字段前 2 位字符，以小写返回
                return new SubstrTableSplitImpl(CommonConstants.NumeralConstants.INT_TWO, true, false);
            case TWO_U:
                // 截取分表字段后 2 位字符，以大写返回
                return new SubstrTableSplitImpl(CommonConstants.NumeralConstants.INT_TWO, false, true);
            case TWO_BU:
                // 截取分表字段前 2 位字符，以大写返回
                return new SubstrTableSplitImpl(CommonConstants.NumeralConstants.INT_TWO, true, true);
            case YYYY:
                return new DateTableSplitImpl(DateFormatEnum.YYYY);
            case YYYY_MM:
                return new DateTableSplitImpl(DateFormatEnum.YYYYMM);
            case YYYY_MM_DD:
                return new DateTableSplitImpl(DateFormatEnum.YYYYMMDD);
            default:
                throw new IllegalStateException("Unexpected table split type: " + this);
        }
    }
}
