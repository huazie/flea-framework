package com.huazie.fleaframework.auth.common.pojo.role;

import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea角色关联POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaRoleRelPOJO extends FleaAuthRelPOJO {

    private static final long serialVersionUID = -8018816119502863707L;

    private Long roleId; // 角色编号

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
