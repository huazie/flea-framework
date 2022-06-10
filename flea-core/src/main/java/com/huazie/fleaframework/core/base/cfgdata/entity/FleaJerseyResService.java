package com.huazie.fleaframework.core.base.cfgdata.entity;

import com.huazie.fleaframework.common.FleaEntity;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Flea Jersey 资源服务实体类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_jersey_res_service")
public class FleaJerseyResService extends FleaEntity {

    private static final long serialVersionUID = -7397724960762187081L;

    @Id
    @Column(name = "service_code", unique = true, nullable = false)
    private String serviceCode; // 服务编码

    @Column(name = "resource_code", nullable = false)
    private String resourceCode; // 资源编码

    @Column(name = "service_name", nullable = false)
    private String serviceName; // 服务名称

    @Column(name = "service_interfaces", nullable = false)
    private String serviceInterfaces; // 服务接口类

    @Column(name = "service_method", nullable = false)
    private String serviceMethod; // 服务方法

    @Column(name = "service_input", nullable = false)
    private String serviceInput; // 服务入参类

    @Column(name = "service_output", nullable = false)
    private String serviceOutput; // 服务出参类

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

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceInterfaces() {
        return serviceInterfaces;
    }

    public void setServiceInterfaces(String serviceInterfaces) {
        this.serviceInterfaces = serviceInterfaces;
    }

    public String getServiceMethod() {
        return serviceMethod;
    }

    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    public String getServiceInput() {
        return serviceInput;
    }

    public void setServiceInput(String serviceInput) {
        this.serviceInput = serviceInput;
    }

    public String getServiceOutput() {
        return serviceOutput;
    }

    public void setServiceOutput(String serviceOutput) {
        this.serviceOutput = serviceOutput;
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
