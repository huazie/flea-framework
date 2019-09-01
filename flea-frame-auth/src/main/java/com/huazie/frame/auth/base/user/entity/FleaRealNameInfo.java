package com.huazie.frame.auth.base.user.entity;

import com.huazie.frame.common.FleaEntity;
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
 * <p> Flea用户实名信息表对应的实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_real_name_info")
public class FleaRealNameInfo implements FleaEntity {

    private static final long serialVersionUID = 9217026322231505398L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "FLEA_REAL_NAME_INFO_SEQ")
    @SequenceGenerator(name = "FLEA_REAL_NAME_INFO_SEQ")
    @Column( name = "real_name_id", unique = true, nullable = false)
    private Long realNameId; // 实名编号

    @Column( name = "user_id", nullable = false)
    private Long userId; // 用户编号

    @Column( name = "cert_type", nullable = false)
    private Integer certType; // 证件类型（1：身份证）

    @Column( name = "cert_code", nullable = false)
    private String certCode; // 证件号码

    @Column( name = "cert_name", nullable = false)
    private String certName; // 证件名称

    @Column( name = "cert_address")
    private String certAddress; // 证件地址

    @Column( name = "real_name_state", nullable = false)
    private Integer realNameState; // 实名信息状态（0：删除 1：在用）

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

    @Column( name = "remarks")
    private String remarks; // 备注

    public Long getRealNameId() {
        return realNameId;
    }

    public void setRealNameId(Long realNameId) {
        this.realNameId = realNameId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getCertType() {
        return certType;
    }

    public void setCertType(Integer certType) {
        this.certType = certType;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertAddress() {
        return certAddress;
    }

    public void setCertAddress(String certAddress) {
        this.certAddress = certAddress;
    }

    public Integer getRealNameState() {
        return realNameState;
    }

    public void setRealNameState(Integer realNameState) {
        this.realNameState = realNameState;
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