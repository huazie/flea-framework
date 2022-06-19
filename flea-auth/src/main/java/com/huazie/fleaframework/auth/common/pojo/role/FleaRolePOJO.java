package com.huazie.fleaframework.auth.common.pojo.role;

import com.huazie.fleaframework.auth.common.pojo.FleaGroupIdPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea角色POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaRolePOJO extends FleaGroupIdPOJO {

    private static final long serialVersionUID = 1617936809086587767L;

    private String roleName; // 角色名称

    private String roleDesc; // 角色描述

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
