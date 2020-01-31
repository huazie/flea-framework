package com.huazie.frame.auth.common.pojo.user.register;

import com.huazie.frame.auth.common.pojo.user.login.FleaUserLoginInfo;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Map;

/**
 * <p> Flea用户注册信息 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaUserRegisterInfo extends FleaUserLoginInfo {

    private static final long serialVersionUID = -2040583042633897645L;

    private Map<String, Object> accountAttrMap; // 账户属性

    private Map<String, Object> userAttrMap; // 用户属性

    public Map<String, Object> getAccountAttrMap() {
        return accountAttrMap;
    }

    public void setAccountAttrMap(Map<String, Object> accountAttrMap) {
        this.accountAttrMap = accountAttrMap;
    }

    public Map<String, Object> getUserAttrMap() {
        return userAttrMap;
    }

    public void setUserAttrMap(Map<String, Object> userAttrMap) {
        this.userAttrMap = userAttrMap;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
