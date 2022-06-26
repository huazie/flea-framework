package com.huazie.fleaframework.auth.common.pojo.role;

import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea角色组关联POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaRoleGroupRelPOJO extends FleaAuthRelPOJO {

    private static final long serialVersionUID = -7112959530684903688L;

    private Long roleGroupId; // 角色组编号

    public Long getRoleGroupId() {
        return roleGroupId;
    }

    public void setRoleGroupId(Long roleGroupId) {
        this.roleGroupId = roleGroupId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
