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
 * <p> Flea Jersey 资源实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_jersey_resource")
public class FleaJerseyResource extends FleaEntity {

    private static final long serialVersionUID = -5994367366402674215L;

    @Id
    @Column(name = "resource_code", unique = true, nullable = false)
    private String resourceCode; // 资源编码

    @Column(name = "resource_name", nullable = false)
    private String resourceName; // 资源名称

    @Column(name = "resource_packages", nullable = false)
    private String resourcePackages; // 资源包名(如果存在多个，以逗号分隔)

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

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourcePackages() {
        return resourcePackages;
    }

    public void setResourcePackages(String resourcePackages) {
        this.resourcePackages = resourcePackages;
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
