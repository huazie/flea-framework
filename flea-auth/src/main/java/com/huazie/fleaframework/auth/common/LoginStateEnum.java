package com.huazie.fleaframework.auth.common;

/**
 * 登录状态枚举
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public enum LoginStateEnum {

    LOGINING(1, "登录中"),
    QUITTED(2, "已退出");

    private int state;

    private String desc;

    LoginStateEnum(int state, String desc) {
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
