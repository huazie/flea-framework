package com.huazie.fleaframework.common;

/**
 * <p>拼音相关枚举</p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum PinyinEnum {

    /**
     * <p>大写</p>
     */
    UPPER_CASE(0, "大写"),
    /**
     * <p>小写</p>
     */
    LOWER_CASE(1, "小写"),
    /**
     * <p>简拼</p>
     */
    JIAN_PIN(2, "简拼"),
    /**
     * <p>全拼</p>
     */
    QUAN_PIN(3, "全拼");

    private int type;

    private String desc;

    PinyinEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

}
