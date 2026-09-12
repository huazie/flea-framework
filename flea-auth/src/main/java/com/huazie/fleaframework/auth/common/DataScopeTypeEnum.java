package com.huazie.fleaframework.auth.common;

/**
 * 数据范围类型枚举
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public enum DataScopeTypeEnum {

    ALL(1, "全部数据"),
    DEPT(2, "仅本部门"),
    DEPT_AND_CHILD(3, "本部门及子部门"),
    SELF(4, "仅本人"),
    CUSTOM(5, "自定义组织");

    private Integer type; // 数据范围类型

    private String desc; // 数据范围类型描述

    DataScopeTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据数据范围类型获取对应的数据范围类型枚举
     *
     * @param type 数据范围类型
     * @return 数据范围类型枚举，未匹配返回{@code null}
     * @since 2.0.0
     */
    public static DataScopeTypeEnum getTypeEnum(Integer type) {
        for (DataScopeTypeEnum dataScopeTypeEnum : DataScopeTypeEnum.values()) {
            if (dataScopeTypeEnum.getType().equals(type)) {
                return dataScopeTypeEnum;
            }
        }
        return null;
    }
}
