package com.huazie.frame.auth.common.pojo.user;

import com.huazie.frame.auth.base.user.entity.FleaAccount;
import com.huazie.frame.auth.base.user.entity.FleaAccountAttr;
import com.huazie.frame.auth.base.user.entity.FleaRealNameInfo;
import com.huazie.frame.auth.base.user.entity.FleaUser;
import com.huazie.frame.auth.base.user.entity.FleaUserAttr;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

/**
 * <p> Flea用户模块数据 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaUserModuleData {

    private FleaUser fleaUser; // Flea用户

    private FleaAccount fleaAccount; // Flea账户

    private List<FleaUserAttr> fleaUserAttrs; // Flea用户扩展属性

    private List<FleaAccountAttr> fleaAccountAttrs; // Flea账户扩展属性

    private FleaRealNameInfo fleaRealNameInfo; // Flea实名信息

    public FleaUser getFleaUser() {
        return fleaUser;
    }

    public void setFleaUser(FleaUser fleaUser) {
        this.fleaUser = fleaUser;
    }

    public FleaAccount getFleaAccount() {
        return fleaAccount;
    }

    public void setFleaAccount(FleaAccount fleaAccount) {
        this.fleaAccount = fleaAccount;
        this.fleaAccount.setAccountPwd(null); // 虽然已经加密过了，密码不透露出去
    }

    public List<FleaUserAttr> getFleaUserAttrs() {
        return fleaUserAttrs;
    }

    public void setFleaUserAttrs(List<FleaUserAttr> fleaUserAttrs) {
        this.fleaUserAttrs = fleaUserAttrs;
    }

    public List<FleaAccountAttr> getFleaAccountAttrs() {
        return fleaAccountAttrs;
    }

    public void setFleaAccountAttrs(List<FleaAccountAttr> fleaAccountAttrs) {
        this.fleaAccountAttrs = fleaAccountAttrs;
    }

    public FleaRealNameInfo getFleaRealNameInfo() {
        return fleaRealNameInfo;
    }

    public void setFleaRealNameInfo(FleaRealNameInfo fleaRealNameInfo) {
        this.fleaRealNameInfo = fleaRealNameInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
