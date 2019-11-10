package com.huazie.frame.core.base.cfgdata.entity;

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
 * <p> Flea Jersey资源服务调用日志表对应的实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_jersey_res_service_log")
public class FleaJerseyResServiceLog extends FleaEntity {

    private static final long serialVersionUID = -1036443958452636871L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "FLEA_JERSEY_RES_SERVICE_LOG_SEQ")
    @SequenceGenerator(name = "FLEA_JERSEY_RES_SERVICE_LOG_SEQ")
    @Column(name = "log_id", unique = true, nullable = false)
    private Long logId; // 资源服务日志编号

    @Column(name = "resource_code", nullable = false)
    private String resourceCode; // 资源编码

    @Column(name = "service_code", nullable = false)
    private String serviceCode; // 服务编码

    @Column(name = "input")
    private String input; // 业务入参

    @Column(name = "output")
    private String output; // 业务出参

    @Column(name = "result_code")
    private String resultCode; // 操作结果码

    @Column(name = "result_mess")
    private String resultMess; // 操作结果信息

    @Column(name = "acct_id", nullable = false)
    private Long acctId; // 操作用户账号编号

    @Column(name = "sys_acct_id", nullable = false)
    private Long sysAcctId; // 接入系统账号编号

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 创建日期

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate; // 修改日期

    @Column(name = "remarks")
    private String remarks; // 备注

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMess() {
        return resultMess;
    }

    public void setResultMess(String resultMess) {
        this.resultMess = resultMess;
    }

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public Long getSysAcctId() {
        return sysAcctId;
    }

    public void setSysAcctId(Long sysAcctId) {
        this.sysAcctId = sysAcctId;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}