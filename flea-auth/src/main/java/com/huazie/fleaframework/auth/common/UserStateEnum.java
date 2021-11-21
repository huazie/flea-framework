package com.huazie.fleaframework.auth.common;

/**
 * <p> 用户状态枚举 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum UserStateEnum {

    BE_DELETED(0, "删除状态"),
    IN_USE(1, "正常状态"),
    BE_FORBIDDEN(2, "禁用状态"),
    IN_AUDITING(3, "待审核状态");

    private int state;

    private String desc;

    UserStateEnum(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    public int getState() {
        return state;
    }

    public String getDesc() {
        return desc;
    }

}
