package com.huazie.frame.auth.base.role.entity;

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
 * <p> Flea角色组表对应的实体类 </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_role_group")
public class FleaRoleGroup extends FleaEntity {

    private static final long serialVersionUID = -1979997474032842209L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "FLEA_ROLE_GROUP_SEQ")
    @SequenceGenerator(name = "FLEA_ROLE_GROUP_SEQ")
    @Column(name = "role_group_id", unique = true, nullable = false)
    private Long roleGroupId; // 角色组编号

    @Column(name = "role_group_name", nullable = false)
    private String roleGroupName; // 角色组名称

    @Column(name = "role_group_desc")
    private String roleGroupDesc; // 角色组描述

    @Column(name = "role_group_state", nullable = false)
    private Integer roleGroupState; // 角色组状态

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 创建日期

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate; // 修改日期

    @Column(name = "remarks")
    private String remarks; // 备注信息

    public Long getRoleGroupId() {
        return roleGroupId;
    }

    public void setRoleGroupId(Long roleGroupId) {
        this.roleGroupId = roleGroupId;
    }

    public String getRoleGroupName() {
        return roleGroupName;
    }

    public void setRoleGroupName(String roleGroupName) {
        this.roleGroupName = roleGroupName;
    }

    public String getRoleGroupDesc() {
        return roleGroupDesc;
    }

    public void setRoleGroupDesc(String roleGroupDesc) {
        this.roleGroupDesc = roleGroupDesc;
    }

    public Integer getRoleGroupState() {
        return roleGroupState;
    }

    public void setRoleGroupState(Integer roleGroupState) {
        this.roleGroupState = roleGroupState;
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