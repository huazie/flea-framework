package com.huazie.frame.auth.common.pojo.user.register;

import com.huazie.frame.auth.common.pojo.account.attr.FleaAccountAttrInfo;
import com.huazie.frame.auth.common.pojo.user.attr.FleaUserAttrInfo;
import com.huazie.frame.auth.common.pojo.user.login.FleaUserLoginInfo;
import com.huazie.frame.common.util.CollectionUtils;
import com.huazie.frame.common.util.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

/**
 * <p> Flea用户注册信息 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaUserRegisterInfo extends FleaUserLoginInfo {

    private static final long serialVersionUID = -2040583042633897645L;

    private List<FleaUserAttrInfo> userAttrList; // Flea用户属性POJO类List集合

    private List<FleaAccountAttrInfo> accountAttrList; // Flea账户属性POJO类List集合

    private String remarks; // 备注

    public List<FleaUserAttrInfo> getUserAttrList() {
        return userAttrList;
    }

    public void setUserAttrList(List<FleaUserAttrInfo> userAttrList) {
        this.userAttrList = userAttrList;
    }

    /**
     * <p> Flea用户属性POJO类设置用户编号 </p>
     *
     * @param userId 用户编号
     * @since 1.0.0
     */
    public void setUserId(Long userId) {
        if (CollectionUtils.isNotEmpty(userAttrList)) {
            for (FleaUserAttrInfo fleaUserAttrInfo : userAttrList) {
                if (ObjectUtils.isNotEmpty(fleaUserAttrInfo)) {
                    fleaUserAttrInfo.setUserId(userId);
                }
            }
        }
    }

    public List<FleaAccountAttrInfo> getAccountAttrList() {
        return accountAttrList;
    }

    public void setAccountAttrList(List<FleaAccountAttrInfo> accountAttrList) {
        this.accountAttrList = accountAttrList;
    }

    /**
     * <p> Flea用户属性POJO类设置用户编号 </p>
     *
     * @param accountId 用户编号
     * @since 1.0.0
     */
    public void setAccountId(Long accountId) {
        if (CollectionUtils.isNotEmpty(accountAttrList)) {
            for (FleaAccountAttrInfo fleaAccountAttrInfo : accountAttrList) {
                if (ObjectUtils.isNotEmpty(fleaAccountAttrInfo)) {
                    fleaAccountAttrInfo.setAccountId(accountId);
                }
            }
        }
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
        // Flea用户扩展属性POJO添加备注
        if (CollectionUtils.isNotEmpty(userAttrList)) {
            for (FleaUserAttrInfo fleaUserAttrInfo : userAttrList) {
                if (ObjectUtils.isNotEmpty(fleaUserAttrInfo)) {
                    fleaUserAttrInfo.setRemarks(remarks);
                }
            }
        }
        // Flea账户扩展属性POJO添加备注
        if (CollectionUtils.isNotEmpty(accountAttrList)) {
            for (FleaAccountAttrInfo fleaAccountAttrInfo : accountAttrList) {
                if (ObjectUtils.isNotEmpty(fleaAccountAttrInfo)) {
                    fleaAccountAttrInfo.setRemarks(remarks);
                }
            }
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
