package com.huazie.fleaframework.auth.common;

/**
 * 功能类型枚举
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum FunctionTypeEnum {

    MENU("MENU", "菜单"),
    OPERATION("OPERATION", "操作"),
    ELEMENT("ELEMENT", "元素");

    private String type;

    private String desc;

    FunctionTypeEnum(String type, String desc) {
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
