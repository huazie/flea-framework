package com.huazie.fleaframework.auth.common.pojo.user.register;

import com.huazie.fleaframework.auth.common.pojo.user.FleaAccountPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.FleaUserPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaAccountAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.attr.FleaUserAttrPOJO;
import com.huazie.fleaframework.auth.common.pojo.user.login.FleaUserLoginPOJO;
import com.huazie.fleaframework.common.RegExpEnum;
import com.huazie.fleaframework.common.util.CollectionUtils;
import com.huazie.fleaframework.common.util.NumberUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
import com.huazie.fleaframework.common.util.PatternMatcherUtils;
import com.huazie.fleaframework.common.util.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Flea用户注册信息POJO类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaUserRegisterPOJO extends FleaUserLoginPOJO {

    private static final long serialVersionUID = -2040583042633897645L;

    private Long systemId; // 系统编号（用于系统账户和用户的生成）

    private String userName; // 用户名称

    private Long groupId; // 用户组编号

    private Integer state; // 状态（0：删除，1：正常 ，2：禁用，3：待审核）

    private Date effectiveDate; // 生效时间

    private Date expiryDate; // 失效时间

    private String remarks; // 备注

    private List<FleaUserAttrPOJO> userAttrList; // Flea用户扩展属性POJO类List集合

    private List<FleaAccountAttrPOJO> accountAttrList; // Flea账户扩展属性POJO类List集合

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 根据账号，生成用户名
     *
     * @return 用户名
     * @since 2.0.0
     */
    public String getUserNameByAccountCode() {
        String accountCode = this.getAccountCode();
        // 如果账号是手机号码
        if (PatternMatcherUtils.matches(RegExpEnum.PHONE.getExp(), accountCode, Pattern.CASE_INSENSITIVE)) {
            return "P" + StringUtils.subStrLast(accountCode, 4);
        }
        // 如果账号是邮箱
        if (PatternMatcherUtils.matches(RegExpEnum.EMAIL.getExp(), accountCode, Pattern.CASE_INSENSITIVE)) {
            return "E" + StringUtils.subStrBefore(accountCode, accountCode.indexOf('@'));
        }
        // 其他场景依然使用账号作为用户名
        return accountCode;
    }

    /**
     * 如果没有设置用户名，则根据账号来设置
     *
     * @since 2.0.0
     */
    public void setUserNameByAccountCode() {
        if (ObjectUtils.isEmpty(this.userName))
            this.userName = getUserNameByAccountCode();
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
        // Flea用户扩展属性POJO添加备注
        setRemarks4UserAttr(this.userAttrList, remarks);
        // Flea账户扩展属性POJO添加备注
        setRemarks4AccountAttr(this.accountAttrList, remarks);
    }

    private void setRemarks4UserAttr(List<FleaUserAttrPOJO> userAttrList, String remarks) {
        if (CollectionUtils.isNotEmpty(userAttrList)) {
            for (FleaUserAttrPOJO fleaUserAttrInfo : userAttrList) {
                if (ObjectUtils.isNotEmpty(fleaUserAttrInfo)) {
                    fleaUserAttrInfo.setRemarks(remarks);
                }
            }
        }
    }

    private void setRemarks4AccountAttr(List<FleaAccountAttrPOJO> accountAttrList, String remarks) {
        if (CollectionUtils.isNotEmpty(accountAttrList)) {
            for (FleaAccountAttrPOJO fleaAccountAttrInfo : accountAttrList) {
                if (ObjectUtils.isNotEmpty(fleaAccountAttrInfo)) {
                    fleaAccountAttrInfo.setRemarks(remarks);
                }
            }
        }
    }

    public List<FleaUserAttrPOJO> getUserAttrList() {
        return userAttrList;
    }

    public void setUserAttrList(List<FleaUserAttrPOJO> userAttrList) {
        this.userAttrList = userAttrList;
    }

    /**
     * 添加其他用户属性信息
     *
     * @param otherUserAttrList 其他用户属性信息集合
     * @since 2.0.0
     */
    public void addUserAttrList(List<FleaUserAttrPOJO> otherUserAttrList) {
        if (ObjectUtils.isEmpty(this.userAttrList))
            this.userAttrList = new ArrayList<>();
        this.userAttrList.addAll(otherUserAttrList);
        if (StringUtils.isNotBlank(this.remarks))
            // Flea用户扩展属性POJO添加备注
            setRemarks4UserAttr(this.userAttrList, this.remarks);
    }

    /**
     * Flea用户扩展属性POJO类设置用户编号
     *
     * @param userId 用户编号
     * @since 1.0.0
     */
    public void setUserId(Long userId) {
        if (CollectionUtils.isNotEmpty(userAttrList)) {
            for (FleaUserAttrPOJO fleaUserAttrInfo : userAttrList) {
                if (ObjectUtils.isNotEmpty(fleaUserAttrInfo)) {
                    fleaUserAttrInfo.setUserId(userId);
                }
            }
        }
    }

    public List<FleaAccountAttrPOJO> getAccountAttrList() {
        return accountAttrList;
    }

    public void setAccountAttrList(List<FleaAccountAttrPOJO> accountAttrList) {
        this.accountAttrList = accountAttrList;
    }

    /**
     * 添加其他的账户属性信息
     *
     * @param otherAccountAttrList 其他账户属性信息集合
     * @since 2.0.0
     */
    public void addAccountAttrList(List<FleaAccountAttrPOJO> otherAccountAttrList) {
        if (ObjectUtils.isEmpty(this.accountAttrList))
            this.accountAttrList = new ArrayList<>();
        this.accountAttrList.addAll(otherAccountAttrList);
        if (StringUtils.isNotBlank(this.remarks))
            // Flea账户扩展属性POJO添加备注
            setRemarks4AccountAttr(this.accountAttrList, this.remarks);
    }

    /**
     * Flea用户扩展属性POJO类设置用户编号
     *
     * @param accountId 用户编号
     * @since 1.0.0
     */
    public void setAccountId(Long accountId) {
        if (CollectionUtils.isNotEmpty(accountAttrList)) {
            for (FleaAccountAttrPOJO fleaAccountAttrInfo : accountAttrList) {
                if (ObjectUtils.isNotEmpty(fleaAccountAttrInfo)) {
                    fleaAccountAttrInfo.setAccountId(accountId);
                }
            }
        }
    }

    /**
     * 获取Flea用户POJO类
     *
     * @return Flea用户POJO类实例
     * @since 1.0.0
     */
    public FleaUserPOJO newFleaUserPOJO() {
        FleaUserPOJO fleaUserPOJO = new FleaUserPOJO();
        fleaUserPOJO.setUserId(systemId);
        fleaUserPOJO.setGroupId(groupId);
        if (StringUtils.isBlank(userName)) {
            // 用户昵称为空，使用账号代替
            userName = getAccountCode();
        }
        fleaUserPOJO.setUserName(userName);
        fleaUserPOJO.setUserState(state);
        fleaUserPOJO.setEffectiveDate(effectiveDate);
        fleaUserPOJO.setExpiryDate(expiryDate);
        fleaUserPOJO.setRemarks(remarks);
        return fleaUserPOJO;
    }

    /**
     * 获取Flea账户POJO类
     *
     * @param userId 用户编号
     * @return Flea账户POJO类实例
     * @since 1.0.0
     */
    public FleaAccountPOJO newFleaAccountPOJO(Long userId) {
        FleaAccountPOJO fleaAccountPOJO = new FleaAccountPOJO();
        fleaAccountPOJO.setAccountId(systemId);
        fleaAccountPOJO.setUserId(userId);
        fleaAccountPOJO.setAccountCode(getAccountCode());
        fleaAccountPOJO.setAccountPwd(getAccountPwd());
        fleaAccountPOJO.setAccountState(state);
        fleaAccountPOJO.setEffectiveDate(effectiveDate);
        fleaAccountPOJO.setExpiryDate(expiryDate);
        fleaAccountPOJO.setRemarks(remarks);
        return fleaAccountPOJO;
    }

    /**
     * 判断是否是系统注册
     *
     * @return systemId 不空且大于0，返回 true；否则返回 false。
     * @since 2.0.0
     */
    public boolean isSystemRegister() {
        return NumberUtils.isPositiveNumber(this.systemId);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
