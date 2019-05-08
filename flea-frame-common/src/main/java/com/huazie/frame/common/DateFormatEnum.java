package com.huazie.frame.common;

/**
 * <p>日期格式化类型枚举</p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum DateFormatEnum {

    YYYY("yyyy"),
    YYYYMM("yyyyMM"),
    YYMM("yyMM"),
    YYYYMMDD("yyyyMMdd"),
    YYMMDD("yyMMdd"),
    YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
    YYMMDDHHMMSS("yyMMddHHmmss"),
    HHMMSS("HHmmss"),
    YYYY_MM("yyyy-MM"),
    YYYY_MM_DD("yyyy-MM-dd"),
    YYYY_MM_DDHH_MM_SS("yyyy-MM-dd HH:mm:ss"),
    HH_MM_SS("HH:mm:ss");

    private String format;

    DateFormatEnum(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public static DateFormatEnum getDefaultEnum() {
        return YYYY_MM_DDHH_MM_SS;
    }

    public static String DATE_FORMAT_DEFAULT = getDefaultEnum().format;
}
