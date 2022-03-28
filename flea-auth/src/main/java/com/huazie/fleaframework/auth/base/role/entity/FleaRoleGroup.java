package com.huazie.fleaframework.auth.base.role.entity;

import com.huazie.fleaframework.common.FleaEntity;
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
 * Flea角色组表对应的实体类
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
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FLEA_ROLE_GROUP_GENERATOR")
    @TableGenerator(
        // 唯一的生成器名称，可以由一个或多个类引用以作为id值的生成器。
        name = "FLEA_ROLE_GROUP_GENERATOR",
        // 存储生成的ID值的表的名称
        table = "flea_id_generator",
        // 表中主键列的名称
        pkColumnName = "id_generator_key",
        // 存储最后生成的主键值的列的名称
        valueColumnName = "id_generator_value",
        // ID生成器表中的主键值模板，用于将该生成值集与其他可能存储在表中的值区分开
        pkColumnValue = "pk_flea_role_group",
        // 从ID生成器表中分配ID号时增加的数量
        allocationSize = 1
    )
    @Column(name = "role_group_id", unique = true, nullable = false)
    private Long roleGroupId; // 角色组编号

    @Column(name = "role_group_name", nullable = false)
    private String roleGroupName; // 角色组名称

    @Column(name = "role_group_desc")
    private String roleGroupDesc; // 角色组描述

    @Column(name = "role_group_state", nullable = false)
    private Integer roleGroupState; // 角色组状态(0: 删除 1: 正常)

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