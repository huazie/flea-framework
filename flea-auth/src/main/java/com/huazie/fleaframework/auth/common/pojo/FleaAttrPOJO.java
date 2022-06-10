package com.huazie.fleaframework.auth.common.pojo;

import com.huazie.fleaframework.common.pojo.FleaEffExpDatePOJO;

/**
 * Flea属性POJO类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaAttrPOJO extends FleaEffExpDatePOJO {

    private static final long serialVersionUID = 2531813932210760178L;

    private String attrCode; // 属性码

    private String attrValue; // 属性值

    private String attrDesc; // 属性描述

    public String getAttrCode() {
        return attrCode;
    }

    public void setAttrCode(String attrCode) {
        this.attrCode = attrCode;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getAttrDesc() {
        return attrDesc;
    }

    public void setAttrDesc(String attrDesc) {
        this.attrDesc = attrDesc;
    }
}
