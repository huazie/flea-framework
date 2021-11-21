package com.huazie.fleaframework.core.base.cfgdata.entity;

import com.huazie.fleaframework.common.FleaEntity;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * <p> Flea Jersey 国际码和错误码映射表对应实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_jersey_i18n_error_mapping")
public class FleaJerseyI18nErrorMapping extends FleaEntity {

    private static final long serialVersionUID = -3499450136118740002L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_id", unique = true, nullable = false)
    private Long mappingId;         // 国际码和错误码映射编号

    @Column(name = "resource_code", nullable = false)
    private String resourceCode;    // 资源编码

    @Column(name = "service_code", nullable = false)
    private String serviceCode;     // 服务编码

    @Column(name = "i18n_code", nullable = false)
    private String i18nCode;        // 国际码

    @Column(name = "error_code", nullable = false)
    private String errorCode;       // 错误码

    @Column(name = "return_mess")
    private String returnMess;      // 返回信息

    @Column(name = "state", nullable = false)
    private Integer state;     // 状态(0：删除 1：正常 ）

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;   // 创建日期

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate;     // 修改日期

    @Column(name = "remarks")
    private String remarks;    // 备注

    public Long getMappingId() {
        return mappingId;
    }

    public void setMappingId(Long mappingId) {
        this.mappingId = mappingId;
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

    public String getI18nCode() {
        return i18nCode;
    }

    public void setI18nCode(String i18nCode) {
        this.i18nCode = i18nCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getReturnMess() {
        return returnMess;
    }

    public void setReturnMess(String returnMess) {
        this.returnMess = returnMess;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
