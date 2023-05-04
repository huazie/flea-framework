package com.huazie.fleaframework.common;

/**
 * 正则匹配表达式枚举
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public enum RegExpEnum {

    URL("^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", "Http地址"),
    PHONE("^(13[0-9]|14[5-9]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[89])\\d{8}$", "11位手机号码"),
    EMAIL("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", "邮箱");

    private String exp;

    private String desc;

    RegExpEnum(String exp, String desc) {
        this.exp = exp;
        this.desc = desc;
    }

    public String getExp() {
        return exp;
    }

    public String getDesc() {
        return desc;
    }
}
