package com.huazie.fleaframework.auth.common.pojo.role;

import com.huazie.fleaframework.common.pojo.FleaRemarksPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea角色组POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaRoleGroupPOJO extends FleaRemarksPOJO {

    private static final long serialVersionUID = -5892535581419492115L;

    private String roleGroupName; // 角色组名称

    private String roleGroupDesc; // 角色组描述

    public String getRoleGroupName() {
        return roleGroupName;
    }

    public void setRoleGroupName(String roleGroupName) {
        this.roleGroupName = roleGroupName;
    }

    public String getRoleGroupDesc() {
        return roleGroupDesc;
    }

    public void setRoleGroupDesc(String roleGroupDesc) {
        this.roleGroupDesc = roleGroupDesc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
