package com.huazie.fleaframework.auth.common.pojo.privilege;

import com.huazie.fleaframework.common.pojo.FleaRemarksPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea权限组POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class FleaPrivilegeGroupPOJO extends FleaRemarksPOJO {

    private static final long serialVersionUID = 1289507232052910718L;

    private String privilegeGroupName; // 权限组名称

    private String privilegeGroupDesc; // 权限描述

    private Integer isMain; // 是否为主权限组（0：不是 1：是）

    private String functionType; // 功能类型(菜单、操作、元素、资源)

    public String getPrivilegeGroupName() {
        return privilegeGroupName;
    }

    public void setPrivilegeGroupName(String privilegeGroupName) {
        this.privilegeGroupName = privilegeGroupName;
    }

    public String getPrivilegeGroupDesc() {
        return privilegeGroupDesc;
    }

    public void setPrivilegeGroupDesc(String privilegeGroupDesc) {
        this.privilegeGroupDesc = privilegeGroupDesc;
    }

    public Integer getIsMain() {
        return isMain;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    public String getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
