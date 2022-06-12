package com.huazie.fleaframework.common;

/**
 * 拼音相关枚举
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum PinyinEnum {

    /**
     * 大写
     */
    UPPER_CASE(0, "大写"),
    /**
     * 小写
     */
    LOWER_CASE(1, "小写"),
    /**
     * 简拼
     */
    JIAN_PIN(2, "简拼"),
    /**
     * 全拼
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
