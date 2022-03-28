package com.huazie.fleaframework.auth.common.pojo.privilege;

import com.huazie.fleaframework.common.pojo.FleaRemarksPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea权限POJO类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaPrivilegePOJO extends FleaRemarksPOJO {

    private static final long serialVersionUID = -1348702196946202298L;

    private String privilegeName; // 权限名称

    private String privilegeDesc; // 权限描述

    private Long groupId; // 权限组编号

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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
