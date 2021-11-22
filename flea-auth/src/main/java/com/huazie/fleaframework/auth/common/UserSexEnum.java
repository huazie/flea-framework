package com.huazie.fleaframework.auth.common;

/**
 * <p> 用户性别枚举 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum UserSexEnum {

    MALE(1, "男"),
    FEMALE(2, "女"),
    UNKNOWN(3, "未知");

    private int sex; // 性别枚举值

    private String desc; // 性别描述

    UserSexEnum(int sex, String desc) {
        this.sex = sex;
        this.desc = desc;
    }

    public int getSex() {
        return sex;
    }

    public String getDesc() {
        return desc;
    }
}
