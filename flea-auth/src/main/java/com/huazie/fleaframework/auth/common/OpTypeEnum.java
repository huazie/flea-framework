package com.huazie.fleaframework.auth.common;

/**
 * 审计操作类型枚举
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public enum OpTypeEnum {

    LOGIN("LOGIN", "登录"),
    LOGOUT("LOGOUT", "登出"),
    AUTH("AUTH", "授权校验"),
    CHG_PWD("CHG_PWD", "修改密码"),
    GRANT("GRANT", "授权"),
    REVOKE("REVOKE", "撤销授权"),
    CREATE("CREATE", "新增"),
    UPDATE("UPDATE", "修改"),
    DELETE("DELETE", "删除");

    private String type; // 操作类型

    private String desc; // 操作类型描述

    OpTypeEnum(String type, String desc) {
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
