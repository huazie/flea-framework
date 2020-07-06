package com.huazie.frame.auth.base.user.entity;

import com.huazie.frame.common.EntityStateEnum;
import com.huazie.frame.common.FleaEntity;
import com.huazie.frame.common.FleaSessionManager;
import com.huazie.frame.common.util.DateUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * <p> Flea登录日志表对应的实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_login_log")
public class FleaLoginLog extends FleaEntity {

    private static final long serialVersionUID = -3509005972594017615L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FLEA_LOGIN_LOG_GENERATOR")
    @TableGenerator(
            name = "FLEA_LOGIN_LOG_GENERATOR",      // 唯一的生成器名称，可以由一个或多个类引用以作为id值的生成器。
            table = "flea_id_generator",            // 存储生成的ID值的表的名称
            pkColumnName = "id_generator_key",      // 表中主键列的名称
            valueColumnName = "id_generator_value", // 存储最后生成的主键值的列的名称
            pkColumnValue = "pk_flea_login_log",    // 生成器表中的主键值，用于将该生成值集与其他可能存储在表中的值区分开
            allocationSize = 1                      // 从生成器分配ID号时增加的数量
    )
    @Column(name = "login_log_id", unique = true, nullable = false)
    private Long loginLogId; // 登录日志编号

    @Column(name = "account_id", nullable = false)
    private Long accountId; // 账户编号

    @Column(name = "system_account_id", nullable = false)
    private Long systemAccountId; // 系统账户编号

    @Column(name = "login_ip4", nullable = false)
    private String loginIp4; // ip4地址

    @Column(name = "login_ip6")
    private String loginIp6; // ip6地址

    @Column(name = "login_area")
    private String loginArea; // 登录地区

    @Column(name = "login_state", nullable = false)
    private Integer loginState; // 登录状态（1：登录中，2：已退出）

    @Column(name = "login_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime; // 登录时间

    @Column(name = "logout_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutTime; // 退出时间

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 创建日期

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate; // 修改日期

    @Column(name = "remarks")
    private String remarks; // 描述信息

    @Column(name = "ext1")
    private String ext1; // 扩展字段1

    @Column(name = "ext2")
    private String ext2; // 扩展字段2

    /**
     * <p> 无参数构造方法 </p>
     *
     * @since 1.0.0
     */
    public FleaLoginLog() {
    }

    /**
     * <p> 带参数构造方法 </p>
     *
     * @since 1.0.0
     */
    public FleaLoginLog(Long accountId, String loginIp4, String loginIp6, String loginArea, String remarks) {
        this.accountId = accountId;
        this.systemAccountId = FleaSessionManager.getSystemAcctId();
        this.loginIp4 = loginIp4;
        this.loginIp6 = loginIp6;
        this.loginArea = loginArea;
        this.loginState = EntityStateEnum.IN_USE.getState();
        this.loginTime = DateUtils.getCurrentTime();
        this.createDate = DateUtils.getCurrentTime();
        this.remarks = remarks;
    }

    public Long getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(Long loginLogId) {
        this.loginLogId = loginLogId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getSystemAccountId() {
        return systemAccountId;
    }

    public void setSystemAccountId(Long systemAccountId) {
        this.systemAccountId = systemAccountId;
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

    public Integer getLoginState() {
        return loginState;
    }

    public void setLoginState(Integer loginState) {
        this.loginState = loginState;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}