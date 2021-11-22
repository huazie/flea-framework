package com.huazie.fleaframework.auth.common.pojo.privilege;

import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p> Flea权限关联POJO类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaPrivilegeRelPOJO extends FleaAuthRelPOJO {

    private Long privilegeId; // 权限编号

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
