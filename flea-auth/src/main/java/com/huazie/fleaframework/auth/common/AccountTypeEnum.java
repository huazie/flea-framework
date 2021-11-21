package com.huazie.fleaframework.auth.common;

/**
 * <p> 账户类型枚举 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum AccountTypeEnum {

    SYSTEM_ACCOUNT("SYSTEM", "系统账户"),
    OPERATOR_ACCOUNT("OPERATOR", "操作账户");

    private String type;

    private String desc;

    AccountTypeEnum(String type, String desc) {
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
