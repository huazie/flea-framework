package com.huazie.fleaframework.auth.base.role.entity;

import com.huazie.fleaframework.common.CommonConstants;
import com.huazie.fleaframework.common.EntityStateEnum;
import com.huazie.fleaframework.common.FleaEntity;
import com.huazie.fleaframework.common.util.DateUtils;
import com.huazie.fleaframework.common.util.ObjectUtils;
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
 * Flea角色表对应的实体类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_role")
public class FleaRole extends FleaEntity {

    private static final long serialVersionUID = 7968583044895981990L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FLEA_ROLE_GENERATOR")
    @TableGenerator(
        // 唯一的生成器名称，可以由一个或多个类引用以作为id值的生成器。
        name = "FLEA_ROLE_GENERATOR",
        // 存储生成的ID值的表的名称
        table = "flea_id_generator",
        // 表中主键列的名称
        pkColumnName = "id_generator_key",
        // 存储最后生成的主键值的列的名称
        valueColumnName = "id_generator_value",
        // ID生成器表中的主键值模板，用于将该生成值集与其他可能存储在表中的值区分开
        pkColumnValue = "pk_flea_role",
        // 从ID生成器表中分配ID号时增加的数量
        allocationSize = 1
    )
    @Column(name = "role_id", unique = true, nullable = false)
    private Long roleId; // 角色编号

    @Column(name = "role_name", nullable = false)
    private String roleName; // 角色名称

    @Column(name = "role_desc")
    private String roleDesc; // 角色描述

    @Column(name = "group_id", nullable = false)
    private Long groupId; // 角色组编号

    @Column(name = "role_state", nullable = false)
    private Integer roleState; // 角色状态(0: 删除 1: 正常)

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate; // 创建日期

    @Column(name = "done_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date doneDate; // 修改日期

    @Column(name = "remarks")
    private String remarks; // 备注信息

    /**
     * 无参数构造方法
     *
     * @since 2.0.0
     */
    public FleaRole() {
    }

    /**
     * 带参数构造方法
     *
     * @param roleName 角色名称
     * @param roleDesc 角色描述
     * @param groupId  角色组编号
     * @param remarks  备注
     * @since 2.0.0
     */
    public FleaRole(String roleName, String roleDesc, Long groupId, String remarks) {
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        if (ObjectUtils.isEmpty(groupId)) {
            groupId = CommonConstants.NumeralConstants.MINUS_ONE;
        }
        this.groupId = groupId;
        this.roleState = EntityStateEnum.IN_USE.getState();
        this.createDate = DateUtils.getCurrentTime();
        this.remarks = remarks;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getRoleState() {
        return roleState;
    }

    public void setRoleState(Integer roleState) {
        this.roleState = roleState;
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