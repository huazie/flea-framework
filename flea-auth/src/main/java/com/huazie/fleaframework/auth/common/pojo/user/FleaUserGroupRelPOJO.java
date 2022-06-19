package com.huazie.fleaframework.auth.common.pojo.user;

import com.huazie.fleaframework.auth.common.pojo.FleaAuthRelPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea用户组关联POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaUserGroupRelPOJO extends FleaAuthRelPOJO {

    private static final long serialVersionUID = -8043534149143365570L;

    private Long userGroupId; // 用户组编号

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
