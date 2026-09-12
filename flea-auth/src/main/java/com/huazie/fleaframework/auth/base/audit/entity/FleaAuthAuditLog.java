package com.huazie.fleaframework.auth.base.audit.entity;

import com.huazie.fleaframework.common.FleaEntity;
import com.huazie.fleaframework.common.util.DateUtils;
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
 * Flea授权操作审计日志表对应的实体类
 *
 * @author huazie
 * @version 2.0.0
 * @since 2.0.0
 */
@Entity
@Table(name = "flea_auth_audit_log")
public class FleaAuthAuditLog extends FleaEntity {

    private static final long serialVersionUID = 6918372645029183746L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FLEA_AUTH_AUDIT_LOG_GENERATOR")
    @TableGenerator(
        // 唯一的生成器名称，可以由一个或多个类引用以作为id值的生成器。
        name = "FLEA_AUTH_AUDIT_LOG_GENERATOR",
        // 存储生成的ID值的表的名称
        table = "flea_id_generator",
        // 表中主键列的名称
        pkColumnName = "id_generator_key",
        // 存储最后生成的主键值的列的名称
        valueColumnName = "id_generator_value",
        // ID生成器表中的主键值模板，用于将该生成值集与其他可能存储在表中的值区分开
        pkColumnValue = "pk_flea_auth_audit_log",
        // 从ID生成器表中分配ID号时增加的数量
        allocationSize = 1
    )
    @Column(name = "audit_id", unique = true, nullable = false)
    private Long auditId; // 审计编号

    @Column(name = "user_id")
    private Long userId; // 用户编号

    @Column(name = "account_id")
    private Long accountId; // 账户编号

    @Column(name = "op_type", nullable = false)
    private String opType; // 操作类型(LOGIN/AUTH/LOGOUT/CHG_PWD/GRANT...)

    @Column(name = "op_target")
    private String opTarget; // 操作对象

    @Column(name = "op_desc")
    private String opDesc; // 操作描述

    @Column(name = "op_result", nullable = false)
    private Integer opResult; // 操作结果(0:失败 1:成功)

    @Column(name = "ip_addr")
    private String ipAddr; // 客户端IP(支持IPv6)

    @Column(name = "request_id")
    private String requestId; // 请求追踪编号

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 创建日期

    @Column(name = "remarks")
    private String remarks; // 备注信息

    /**
     * 无参数构造方法
     *
     * @since 2.0.0
     */
    public FleaAuthAuditLog() {
    }

    /**
     * 带参数构造方法
     *
     * @param userId    用户编号
     * @param accountId 账户编号
     * @param opType    操作类型
     * @param opTarget  操作对象
     * @param opDesc    操作描述
     * @param opResult  操作结果(0:失败 1:成功)
     * @param ipAddr    客户端IP(支持IPv6)
     * @param requestId 请求追踪编号
     * @param remarks   备注信息
     * @since 2.0.0
     */
    public FleaAuthAuditLog(Long userId, Long accountId, String opType, String opTarget, String opDesc,
                            Integer opResult, String ipAddr, String requestId, String remarks) {
        this.userId = userId;
        this.accountId = accountId;
        this.opType = opType;
        this.opTarget = opTarget;
        this.opDesc = opDesc;
        this.opResult = opResult;
        this.ipAddr = ipAddr;
        this.requestId = requestId;
        this.createDate = DateUtils.getCurrentTime();
        this.remarks = remarks;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getOpTarget() {
        return opTarget;
    }

    public void setOpTarget(String opTarget) {
        this.opTarget = opTarget;
    }

    public String getOpDesc() {
        return opDesc;
    }

    public void setOpDesc(String opDesc) {
        this.opDesc = opDesc;
    }

    public Integer getOpResult() {
        return opResult;
    }

    public void setOpResult(Integer opResult) {
        this.opResult = opResult;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
