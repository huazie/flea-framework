package com.huazie.fleaframework.auth.common;

/**
 * <p> 用户类型枚举 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum UserTypeEnum {

    SYSTEM_USER("SYSTEM", "系统用户"),
    OPERATOR_USER("OPERATOR", "操作用户");

    private String type;

    private String desc;

    UserTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
