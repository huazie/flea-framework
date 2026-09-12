package com.huazie.fleaframework.auth.common;

/**
 * 组织类型枚举
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public enum OrgTypeEnum {

    COMPANY(1, "公司"),
    DEPARTMENT(2, "部门"),
    GROUP(3, "小组");

    private Integer type; // 组织类型

    private String desc; // 组织类型描述

    OrgTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
