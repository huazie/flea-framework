package com.huazie.frame.core.base.cfgdata.entity;

import com.huazie.frame.common.FleaEntity;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * <p> Flea Jersey 资源客户端实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_jersey_res_client")
public class FleaJerseyResClient extends FleaEntity {

    private static final long serialVersionUID = 3734700474530351711L;

    @Id
    @Column(name = "client_code", unique = true, nullable = false)
    private String clientCode;

    @Column(name = "resource_url", nullable = false)
    private String resourceUrl;

    @Column(name = "resource_code", nullable = false)
    private String resourceCode;

    @Column(name = "service_code", nullable = false)
    private String serviceCode;

    @Column(name = "request_mode", nullable = false)
    private String requestMode;

    @Column(name = "media_type", nullable = false)
    private String mediaType;

    @Column(name = "client_input", nullable = false)
    private String clientInput;

    @Column(name = "client_output", nullable = false)
    private String clientOutput;

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

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
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

    public String getRequestMode() {
        return requestMode;
    }

    public void setRequestMode(String requestMode) {
        this.requestMode = requestMode;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getClientInput() {
        return clientInput;
    }

    public void setClientInput(String clientInput) {
        this.clientInput = clientInput;
    }

    public String getClientOutput() {
        return clientOutput;
    }

    public void setClientOutput(String clientOutput) {
        this.clientOutput = clientOutput;
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
