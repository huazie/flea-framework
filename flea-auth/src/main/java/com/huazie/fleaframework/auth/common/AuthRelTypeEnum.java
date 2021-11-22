package com.huazie.fleaframework.auth.common;

/**
 * <p> 授权关联类型枚举 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum AuthRelTypeEnum {

    // 权限关联类型
    PRIVILEGE_REL_MENU("PRIVILEGE_REL_MENU", "权限关联菜单"),
    PRIVILEGE_REL_OPERATION("PRIVILEGE_REL_OPERATION", "权限关联操作"),
    PRIVILEGE_REL_ELEMENT("PRIVILEGE_REL_ELEMENT", "权限关联元素"),

    // 权限组关联类型
    PRIVILEGE_GROUP_REL_PRIVILEGE("PRIVILEGE_GROUP_REL_PRIVILEGE", "权限组关联权限"),

    // 角色关联类型
    ROLE_REL_ROLE("ROLE_REL_ROLE", "角色关联角色"),
    ROLE_REL_PRIVILEGE("ROLE_REL_PRIVILEGE", "角色关联权限"),
    ROLE_REL_PRIVILEGE_GROUP("ROLE_REL_PRIVILEGE_GROUP", "角色关联权限组"),

    // 角色组关联类型
    ROLE_GROUP_REL_ROLE("ROLE_GROUP_REL_ROLE", "角色组关联角色"),

    // 用户关联类型
    USER_REL_ROLE("USER_REL_ROLE", "用户关联角色"),
    USER_REL_ROLE_GROUP("USER_REL_ROLE_GROUP", "用户关联角色组"),

    // 用户组关联类型
    USER_GROUP_REL_ROLE("USER_GROUP_REL_ROLE", "用户组关联角色"),
    USER_GROUP_REL_ROLE_GROUP("USER_GROUP_REL_ROLE_GROUP", "用户组关联角色组");

    private String relType; // 关联类型

    private String desc; // 关联类型描述

    AuthRelTypeEnum(String relType, String desc) {
        this.relType = relType;
        this.desc = desc;
    }

    public String getRelType() {
        return relType;
    }

    public String getDesc() {
        return desc;
    }
}
