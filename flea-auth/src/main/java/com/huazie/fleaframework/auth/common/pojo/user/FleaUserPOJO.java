package com.huazie.fleaframework.auth.common.pojo.user;

import com.huazie.fleaframework.common.pojo.FleaEffExpDatePOJO;

/**
 * Flea用户POJO类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaUserPOJO extends FleaEffExpDatePOJO {

    private static final long serialVersionUID = -7408352214171013504L;

    private Long userId; // 用户编号

    private String userName; // 昵称

    private Integer userSex; // 性别（1：男 2：女 3：其他）

    private String userEmail; // 用户邮箱

    private String userPhone; // 用户手机号

    private String userAddress; // 用户住址

    private Long groupId; // 用户组编号

    private Integer userState; // 用户状态

    private String remarks; // 备注信息

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
