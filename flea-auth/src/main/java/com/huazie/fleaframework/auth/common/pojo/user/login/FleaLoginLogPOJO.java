package com.huazie.fleaframework.auth.common.pojo.user.login;

import com.huazie.fleaframework.common.pojo.FleaRemarksPOJO;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Flea登录日志POJO类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
public class FleaLoginLogPOJO extends FleaRemarksPOJO {

    private static final long serialVersionUID = 3314741404613673766L;

    private Long accountId; // 账户编号

    private String loginIp4; // ip4地址

    private String loginIp6; // ip6地址

    private String loginArea; // 登录地区

    public FleaLoginLogPOJO() {
    }

    public FleaLoginLogPOJO(Long accountId, String loginIp4, String loginIp6, String loginArea) {
        this.accountId = accountId;
        this.loginIp4 = loginIp4;
        this.loginIp6 = loginIp6;
        this.loginArea = loginArea;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getLoginIp4() {
        return loginIp4;
    }

    public void setLoginIp4(String loginIp4) {
        this.loginIp4 = loginIp4;
    }

    public String getLoginIp6() {
        return loginIp6;
    }

    public void setLoginIp6(String loginIp6) {
        this.loginIp6 = loginIp6;
    }

    public String getLoginArea() {
        return loginArea;
    }

    public void setLoginArea(String loginArea) {
        this.loginArea = loginArea;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
