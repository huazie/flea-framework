package com.huazie.fleaframework.auth.common;

/**
 * <p> 菜单层级枚举 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum MenuLevelEnum {

    LEVEL_ONE(1, "一级菜单"),
    LEVEL_TWO(2, "二级菜单"),
    LEVEL_THREE(3, "三级菜单"),
    LEVEL_FOUR(4, "四级菜单");

    private int level; // 菜单层级

    private String desc; // 层级描述

    MenuLevelEnum(int level, String desc) {
        this.level = level;
        this.desc = desc;
    }

    public int getLevel() {
        return level;
    }

    public String getDesc() {
        return desc;
    }
}
