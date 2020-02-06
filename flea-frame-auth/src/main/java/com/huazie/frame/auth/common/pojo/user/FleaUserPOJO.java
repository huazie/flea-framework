package com.huazie.frame.auth.common.pojo.user;

import com.huazie.frame.auth.common.pojo.FleaEffExpDatePOJO;

/**
 * <p> Flea用户POJO类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaUserPOJO extends FleaEffExpDatePOJO {

    private static final long serialVersionUID = -7408352214171013504L;

    private String userName; // 昵称

    private Long groupId; // 用户组编号

    private Integer userState; // 用户状态

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }
}
