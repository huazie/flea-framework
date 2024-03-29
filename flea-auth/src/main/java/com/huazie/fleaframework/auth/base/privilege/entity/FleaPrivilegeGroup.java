package com.huazie.fleaframework.auth.base.privilege.entity;

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
 * Flea权限组表对应的实体类
 *
 * @author huazie
 * @version 2.0.0
 * @since 1.0.0
 */
@Entity
@Table(name = "flea_privilege_group")
public class FleaPrivilegeGroup extends FleaEntity {

    private static final long serialVersionUID = -3456074248744277322L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FLEA_PRIVILEGE_GROUP_GENERATOR")
    @TableGenerator(
        // 唯一的生成器名称，可以由一个或多个类引用以作为id值的生成器。
        name = "FLEA_PRIVILEGE_GROUP_GENERATOR",
        // 存储生成的ID值的表的名称
        table = "flea_id_generator",
        // 表中主键列的名称
        pkColumnName = "id_generator_key",
        // 存储最后生成的主键值的列的名称
        valueColumnName = "id_generator_value",
        // ID生成器表中的主键值模板，用于将该生成值集与其他可能存储在表中的值区分开
        pkColumnValue = "pk_flea_privilege_group",
        // 从ID生成器表中分配ID号时增加的数量
        allocationSize = 1
    )
    @Column(name = "privilege_group_id", unique = true, nullable = false)
    private Long privilegeGroupId; // 权限组编号

    @Column(name = "privilege_group_name", nullable = false)
    private String privilegeGroupName; // 权限组名称

    @Column(name = "privilege_group_desc")
    private String privilegeGroupDesc; // 权限组描述

    @Column(name = "privilege_group_state", nullable = false)
    private Integer privilegeGroupState; // 权限组状态(0: 删除 1: 正常)

    @Column(name = "is_main", nullable = false)
    private Integer isMain; // 是否为主权限组（0：不是 1：是）

    @Column(name = "function_type")
    private String functionType; // 功能类型(菜单、操作、元素、资源)

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
     * @since 1.0.0
     */
    public FleaPrivilegeGroup() {
    }

    /**
     * 带参数构造方法
     *
     * @param privilegeGroupName 权限组名称
     * @param privilegeGroupDesc 权限组描述
     * @param remarks            备注信息
     * @since 1.0.0
     */
    public FleaPrivilegeGroup(String privilegeGroupName, String privilegeGroupDesc, Integer isMain, String functionType, String remarks) {
        this.privilegeGroupName = privilegeGroupName;
        this.privilegeGroupDesc = privilegeGroupDesc;
        this.privilegeGroupState = EntityStateEnum.IN_USE.getState();
        if (ObjectUtils.isEmpty(isMain)) {
            isMain = 0; // 默认不是主角色组
        }
        this.isMain = isMain;
        this.functionType = functionType;
        this.createDate = DateUtils.getCurrentTime();
        this.remarks = remarks;
    }

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

    public Integer getIsMain() {
        return isMain;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    public String getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
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