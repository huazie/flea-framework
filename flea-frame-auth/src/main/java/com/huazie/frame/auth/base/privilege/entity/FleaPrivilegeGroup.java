package com.huazie.frame.auth.base.privilege.entity;

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
 * <p> Flea权限组表对应的实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_privilege_group")
public class FleaPrivilegeGroup extends FleaEntity {

    private static final long serialVersionUID = 1321707330112250874L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "FLEA_PRIVILEGE_GROUP_SEQ")
    @SequenceGenerator(name = "FLEA_PRIVILEGE_GROUP_SEQ")
    @Column(name = "privilege_group_id", unique = true, nullable = false)
    private Long privilegeGroupId; // 权限组编号

    @Column(name = "privilege_group_name", nullable = false)
    private String privilegeGroupName; // 权限组名称

    @Column(name = "privilege_group_desc")
    private String privilegeGroupDesc; // 权限组描述

    @Column(name = "privilege_group_state", nullable = false)
    private Integer privilegeGroupState; // 权限组状态

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 创建日期

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate; // 修改日期

    @Column(name = "remarks")
    private String remarks; // 备注信息

    public Long getPrivilegeGroupId() {
        return privilegeGroupId;
    }

    public void setPrivilegeGroupId(Long privilegeGroupId) {
        this.privilegeGroupId = privilegeGroupId;
    }

    public String getPrivilegeGroupName() {
        return privilegeGroupName;
    }

    public void setPrivilegeGroupName(String privilegeGroupName) {
        this.privilegeGroupName = privilegeGroupName;
    }

    public String getPrivilegeGroupDesc() {
        return privilegeGroupDesc;
    }

    public void setPrivilegeGroupDesc(String privilegeGroupDesc) {
        this.privilegeGroupDesc = privilegeGroupDesc;
    }

    public Integer getPrivilegeGroupState() {
        return privilegeGroupState;
    }

    public void setPrivilegeGroupState(Integer privilegeGroupState) {
        this.privilegeGroupState = privilegeGroupState;
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