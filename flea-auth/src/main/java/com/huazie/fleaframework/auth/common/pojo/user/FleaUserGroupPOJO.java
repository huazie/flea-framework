package com.huazie.fleaframework.auth.common.pojo.user;

import com.huazie.fleaframework.common.pojo.FleaRemarksPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea用户组POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaUserGroupPOJO extends FleaRemarksPOJO {

    private static final long serialVersionUID = 2431316905638887969L;

    private String userGroupName; // 用户组名

    private String userGroupDesc; // 用户组描述

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public String getUserGroupDesc() {
        return userGroupDesc;
    }

    public void setUserGroupDesc(String userGroupDesc) {
        this.userGroupDesc = userGroupDesc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
