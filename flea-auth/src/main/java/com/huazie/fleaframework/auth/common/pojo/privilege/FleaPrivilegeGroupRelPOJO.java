package com.huazie.fleaframework.auth.common.pojo.privilege;

import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea权限组关联POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
public class FleaPrivilegeGroupRelPOJO extends FleaAuthRelPOJO {

    private static final long serialVersionUID = -5654032497074738310L;

    private Long privilegeGroupId; // 权限组编号

    public Long getPrivilegeGroupId() {
        return privilegeGroupId;
    }

    public void setPrivilegeGroupId(Long privilegeGroupId) {
        this.privilegeGroupId = privilegeGroupId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
