package com.huazie.frame.auth.base.user.entity;

import com.huazie.frame.auth.common.UserStateEnum;
import com.huazie.frame.common.FleaEntity;
import com.huazie.frame.common.util.DateUtils;
import com.huazie.frame.common.util.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * <p> Flea账户信息表对应的实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_account")
public class FleaAccount extends FleaEntity {

    private static final long serialVersionUID = 684330960958004566L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "FLEA_ACCOUNT_SEQ")
    @SequenceGenerator(name = "FLEA_ACCOUNT_SEQ")
    @Column(name = "account_id", unique = true, nullable = false)
    private Long accountId; // 账户编号

    @Column(name = "user_id", nullable = false)
    private Long userId; // 用户编号

    @Column(name = "account_code", nullable = false)
    private String accountCode; // 账号

    @Column(name = "account_pwd", nullable = false)
    private String accountPwd; // 密码

    @Column(name = "account_state", nullable = false)
    private Integer accountState; // 账户状态（0：删除，1：正常 ，2：禁用，3：待审核）

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 创建日期

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate; // 修改日期

    @Column(name = "effective_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate; // 生效日期

    @Column(name = "expiry_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate; // 失效日期

    @Column(name = "remarks")
    private String remarks; // 备注信息

    /**
     * <p> 无参数构造方法 </p>
     *
     * @since 1.0.0
     */
    public FleaAccount() {
    }

    /**
     * <p> 带参数构造方法 </p>
     *
     * @param userId       用户编号
     * @param accountCode  账号
     * @param accountPwd   密码
     * @param accountState 账户状态
     * @param remarks      备注
     * @since 1.0.0
     */
    public FleaAccount(Long userId, String accountCode, String accountPwd, Integer accountState, String remarks) {
        this.userId = userId;
        this.accountCode = accountCode;
        this.accountPwd = accountPwd;
        if (ObjectUtils.isEmpty(accountState)) {
            // 默认账户状态为 3 : 待审核
            accountState = UserStateEnum.IN_AUDITING.getValue();
        }
        this.accountState = accountState;
        createDate = DateUtils.getCurrentTime();
        effectiveDate = DateUtils.getCurrentTime();
        expiryDate = DateUtils.getExpiryTimeForever();
        this.remarks = remarks;
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
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
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}