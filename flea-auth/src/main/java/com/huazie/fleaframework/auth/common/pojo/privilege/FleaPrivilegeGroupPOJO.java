package com.huazie.fleaframework.auth.common.pojo.privilege;

import com.huazie.fleaframework.common.pojo.FleaRemarksPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> Flea权限组POJO类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaPrivilegeGroupPOJO extends FleaRemarksPOJO {

    private static final long serialVersionUID = -1348702196946202298L;

    private String privilegeGroupName; // 权限组名称

    private String privilegeGroupDesc; // 权限描述

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
