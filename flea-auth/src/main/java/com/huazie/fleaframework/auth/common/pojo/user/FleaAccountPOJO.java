package com.huazie.fleaframework.auth.common.pojo.user;

import com.huazie.fleaframework.common.pojo.FleaEffExpDatePOJO;

/**
 * Flea账户POJO类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaAccountPOJO extends FleaEffExpDatePOJO {

    private static final long serialVersionUID = 2231150805208668200L;

    private Long accountId; // 账户编号
    
    private Long userId; // 用户编号

    private String accountCode; // 账号

    private String accountPwd; // 密码

    private Integer accountState; // 账户状态

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    public Integer getAccountState() {
        return accountState;
    }

    public void setAccountState(Integer accountState) {
        this.accountState = accountState;
    }
}
