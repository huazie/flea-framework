package com.huazie.fleaframework.auth.common.pojo.privilege;

import com.huazie.fleaframework.auth.common.pojo.FleaGroupIdPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea权限POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class FleaPrivilegePOJO extends FleaGroupIdPOJO {

    private static final long serialVersionUID = -6114666591531013387L;

    private String privilegeName; // 权限名称

    private String privilegeDesc; // 权限描述

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public String getPrivilegeDesc() {
        return privilegeDesc;
    }

    public void setPrivilegeDesc(String privilegeDesc) {
        this.privilegeDesc = privilegeDesc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
